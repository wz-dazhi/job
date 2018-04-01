package com.job.admin.dao;

import com.job.admin.core.model.JobInfo;
import com.job.admin.dao.JobInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class JobInfoDaoTest {
	
	@Resource
	private JobInfoDao JobInfoDao;
	
	@Test
	public void pageList(){
		List<JobInfo> list = JobInfoDao.pageList(0, 20, 0, null, null);
		int list_count = JobInfoDao.pageListCount(0, 20, 0, null, null);
		
		System.out.println(list);
		System.out.println(list_count);

		List<JobInfo> list2 = JobInfoDao.getJobsByGroup(1);
	}
	
	@Test
	public void save_load(){
		JobInfo info = new JobInfo();
		info.setJobGroup(1);
		info.setJobCron("jobCron");
		info.setJobDesc("desc");
		info.setAuthor("setAuthor");
		info.setAlarmEmail("setAlarmEmail");
		info.setExecutorRouteStrategy("setExecutorRouteStrategy");
		info.setExecutorHandler("setExecutorHandler");
		info.setExecutorParam("setExecutorParam");
		info.setExecutorBlockStrategy("setExecutorBlockStrategy");
		info.setExecutorFailStrategy("setExecutorFailStrategy");
		info.setGlueType("setGlueType");
		info.setGlueSource("setGlueSource");
		info.setGlueRemark("setGlueRemark");
		info.setChildJobId("1");

		int count = JobInfoDao.save(info);

		JobInfo info2 = JobInfoDao.loadById(info.getId());
		info2.setJobCron("jobCron2");
		info2.setJobDesc("desc2");
		info2.setAuthor("setAuthor2");
		info2.setAlarmEmail("setAlarmEmail2");
		info2.setExecutorRouteStrategy("setExecutorRouteStrategy2");
		info2.setExecutorHandler("setExecutorHandler2");
		info2.setExecutorParam("setExecutorParam2");
		info2.setExecutorBlockStrategy("setExecutorBlockStrategy2");
		info2.setExecutorFailStrategy("setExecutorFailStrategy2");
		info2.setGlueType("setGlueType2");
		info2.setGlueSource("setGlueSource2");
		info2.setGlueRemark("setGlueRemark2");
		info2.setGlueUpdatetime(new Date());
		info2.setChildJobId("1");

		int item2 = JobInfoDao.update(info2);

		JobInfoDao.delete(info2.getId());

		List<JobInfo> list2 = JobInfoDao.getJobsByGroup(1);

		int ret3 = JobInfoDao.findAllCount();

	}

}
