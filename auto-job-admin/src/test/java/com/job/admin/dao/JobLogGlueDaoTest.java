package com.job.admin.dao;

import com.job.admin.core.model.JobLogGlue;
import com.job.admin.dao.JobLogGlueDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobLogGlueDaoTest {

    @Resource
    private JobLogGlueDao JobLogGlueDao;

    @Test
    public void test(){
        JobLogGlue logGlue = new JobLogGlue();
        logGlue.setJobId(1);
        logGlue.setGlueType("1");
        logGlue.setGlueSource("1");
        logGlue.setGlueRemark("1");
        int ret = JobLogGlueDao.save(logGlue);

        List<JobLogGlue> list = JobLogGlueDao.findByJobId(1);

        int ret2 = JobLogGlueDao.removeOld(1, 1);

        int ret3 =JobLogGlueDao.deleteByJobId(1);
    }

}
