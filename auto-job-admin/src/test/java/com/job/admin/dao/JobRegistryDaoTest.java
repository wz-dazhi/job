package com.job.admin.dao;

import com.job.admin.core.model.JobRegistry;
import com.job.admin.dao.JobRegistryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobRegistryDaoTest {

    @Resource
    private JobRegistryDao JobRegistryDao;

    @Test
    public void test(){
        int ret = JobRegistryDao.registryUpdate("g1", "k1", "v1");
        if (ret < 1) {
            ret = JobRegistryDao.registrySave("g1", "k1", "v1");
        }

        List<JobRegistry> list = JobRegistryDao.findAll(1);

        int ret2 = JobRegistryDao.removeDead(1);
    }

}
