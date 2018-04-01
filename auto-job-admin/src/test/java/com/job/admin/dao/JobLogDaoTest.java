package com.job.admin.dao;

import com.job.admin.core.model.JobLog;
import com.job.admin.dao.JobLogDao;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobLogDaoTest {

    @Resource
    private JobLogDao JobLogDao;

    @Test
    public void test(){
        List<JobLog> list = JobLogDao.pageList(0, 10, 1, 1, null, null, 1);
        int list_count = JobLogDao.pageListCount(0, 10, 1, 1, null, null, 1);

        JobLog log = new JobLog();
        log.setJobGroup(1);
        log.setJobId(1);

        int ret1 = JobLogDao.save(log);
        JobLog dto = JobLogDao.load(log.getId());

        log.setGlueType("1");
        log.setTriggerTime(new Date());
        log.setTriggerCode(1);
        log.setTriggerMsg("1");
        log.setExecutorAddress("1");
        log.setExecutorHandler("1");
        log.setExecutorParam("1");
        ret1 = JobLogDao.updateTriggerInfo(log);
        dto = JobLogDao.load(log.getId());


        log.setHandleTime(new Date());
        log.setHandleCode(2);
        log.setHandleMsg("2");
        ret1 = JobLogDao.updateHandleInfo(log);
        dto = JobLogDao.load(log.getId());


        List<Map<String, Object>> list2 = JobLogDao.triggerCountByDay(DateUtils.addDays(new Date(), 30), new Date());

        int ret4 = JobLogDao.clearLog(1, 1, new Date(), 100);

        int ret2 = JobLogDao.delete(log.getJobId());

        int ret3 = JobLogDao.triggerCountByHandleCode(-1);
    }

}
