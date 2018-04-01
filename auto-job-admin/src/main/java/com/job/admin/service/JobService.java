package com.job.admin.service;


import com.job.admin.core.model.JobInfo;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.Date;
import java.util.Map;

/**
 * core job action for auto-job
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface JobService {

	/**
	 * page list
	 *
	 * @param start
	 * @param length
	 * @param jobGroup
	 * @param jobDesc
	 * @param executorHandler
	 * @param filterTime
	 * @return
	 */
	public Map<String, Object> pageList(int start, int length, int jobGroup, String jobDesc, String executorHandler, String filterTime);

	/**
	 * add job
	 *
	 * @param jobInfo
	 * @return
	 */
	public ReturnT<String> add(JobInfo jobInfo);

	/**
	 * update job
	 *
	 * @param jobInfo
	 * @return
	 */
	public ReturnT<String> update(JobInfo jobInfo);

	/**
	 * remove job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> remove(int id);

	/**
	 * pause job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> pause(int id);

	/**
	 * resume job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> resume(int id);

	/**
	 * trigger job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> triggerJob(int id);

	/**
	 * dashboard info
	 *
	 * @return
	 */
	public Map<String,Object> dashboardInfo();

	/**
	 * chart info
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ReturnT<Map<String,Object>> chartInfo(Date startDate, Date endDate);

}
