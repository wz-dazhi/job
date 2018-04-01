package com.job.jobhandler;

import com.job.model.JobBusiness;
import com.job.service.IJobBusinessService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @projectName: job
 * @package: com.job.dao
 * @className: JobBusinessHandler
 * @description:
 * @author: wangzhi
 * @createDate: 2018/3/31 22:51
 **/
@Slf4j
@JobHandler(value = "jobBusinessHandler")
@Component
public class JobBusinessHandler extends IJobHandler {

    @Autowired
    private IJobBusinessService jobBusinessService;

    private int count = 0;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        if (log.isInfoEnabled()){
            log.info("-----进入自动任务方法中-----------");
        }

        JobBusiness business = new JobBusiness();
        business.setId(UUID.randomUUID().toString());
        business.setJobName("我是" + (count++) + "号");
        business.setJobAction("--业务Action--");
        business.setJobDate(new Date());
        int addResult = jobBusinessService.add(business);
        if (addResult <= 0){
            return new ReturnT<>(500,"任务执行失败!!!");
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }
}
