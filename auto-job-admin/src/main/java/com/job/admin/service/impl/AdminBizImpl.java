package com.job.admin.service.impl;

import com.job.admin.core.model.JobInfo;
import com.job.admin.core.model.JobLog;
import com.job.admin.core.util.I18nUtil;
import com.job.admin.dao.JobInfoDao;
import com.job.admin.dao.JobLogDao;
import com.job.admin.dao.JobRegistryDao;
import com.job.admin.service.JobService;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xuxueli 2017-07-27 21:54:20
 */
@Service
public class AdminBizImpl implements AdminBiz {
    private static Logger logger = LoggerFactory.getLogger(AdminBizImpl.class);

    @Resource
    public JobLogDao jobLogDao;
    @Resource
    private JobInfoDao jobInfoDao;
    @Resource
    private JobRegistryDao jobRegistryDao;
    @Resource
    private JobService jobService;


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        for (HandleCallbackParam handleCallbackParam: callbackParamList) {
            ReturnT<String> callbackResult = callback(handleCallbackParam);
            logger.info(">>>>>>>>> JobApiController.callback {}, handleCallbackParam={}, callbackResult={}",
                    (callbackResult.getCode()==IJobHandler.SUCCESS.getCode()?"success":"fail"), handleCallbackParam, callbackResult);
        }

        return ReturnT.SUCCESS;
    }

    private ReturnT<String> callback(HandleCallbackParam handleCallbackParam) {
        // valid log item
        JobLog log = jobLogDao.load(handleCallbackParam.getLogId());
        if (log == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "log item not found.");
        }
        if (log.getHandleCode() > 0) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "log repeate callback.");     // avoid repeat callback, trigger child job etc
        }

        // trigger success, to trigger child job
        String callbackMsg = null;
        if (IJobHandler.SUCCESS.getCode() == handleCallbackParam.getExecuteResult().getCode()) {
            JobInfo jobInfo = jobInfoDao.loadById(log.getJobId());
            if (jobInfo !=null && StringUtils.isNotBlank(jobInfo.getChildJobId())) {
                callbackMsg = "<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>"+ I18nUtil.getString("jobconf_trigger_child_run") +"<<<<<<<<<<< </span><br>";

                String[] childJobIds = jobInfo.getChildJobId().split(",");
                for (int i = 0; i < childJobIds.length; i++) {
                    int childJobId = (StringUtils.isNotBlank(childJobIds[i]) && StringUtils.isNumeric(childJobIds[i]))?Integer.valueOf(childJobIds[i]):-1;
                    if (childJobId > 0) {
                        ReturnT<String> triggerChildResult = jobService.triggerJob(childJobId);

                        // add msg
                        callbackMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_child_msg1"),
                                (i+1),
                                childJobIds.length,
                                childJobIds[i],
                                (triggerChildResult.getCode()==ReturnT.SUCCESS_CODE?I18nUtil.getString("system_success"):I18nUtil.getString("system_fail")),
                                triggerChildResult.getMsg());
                    } else {
                        callbackMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_child_msg2"),
                                (i+1),
                                childJobIds.length,
                                childJobIds[i]);
                    }
                }

            }
        } else if (IJobHandler.FAIL_RETRY.getCode() == handleCallbackParam.getExecuteResult().getCode()){
            ReturnT<String> retryTriggerResult = jobService.triggerJob(log.getJobId());
            callbackMsg = "<br><br><span style=\"color:#F39C12;\" > >>>>>>>>>>>"+ I18nUtil.getString("jobconf_exe_fail_retry") +"<<<<<<<<<<< </span><br>";

            callbackMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_msg1"),
                   (retryTriggerResult.getCode()==ReturnT.SUCCESS_CODE?I18nUtil.getString("system_success"):I18nUtil.getString("system_fail")), retryTriggerResult.getMsg());
        }

        // handle msg
        StringBuffer handleMsg = new StringBuffer();
        if (log.getHandleMsg()!=null) {
            handleMsg.append(log.getHandleMsg()).append("<br>");
        }
        if (handleCallbackParam.getExecuteResult().getMsg() != null) {
            handleMsg.append(handleCallbackParam.getExecuteResult().getMsg());
        }
        if (callbackMsg != null) {
            handleMsg.append(callbackMsg);
        }

        // success, save log
        log.setHandleTime(new Date());
        log.setHandleCode(handleCallbackParam.getExecuteResult().getCode());
        log.setHandleMsg(handleMsg.toString());
        jobLogDao.updateHandleInfo(log);

        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        int ret = jobRegistryDao.registryUpdate(registryParam.getRegistGroup(), registryParam.getRegistryKey(), registryParam.getRegistryValue());
        if (ret < 1) {
            jobRegistryDao.registrySave(registryParam.getRegistGroup(), registryParam.getRegistryKey(), registryParam.getRegistryValue());
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        jobRegistryDao.registryDelete(registryParam.getRegistGroup(), registryParam.getRegistryKey(), registryParam.getRegistryValue());
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> triggerJob(int jobId) {
        return jobService.triggerJob(jobId);
    }

}
