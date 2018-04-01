package com.job.admin.controller;

import com.job.admin.core.enums.ExecutorFailStrategyEnum;
import com.job.admin.core.model.JobGroup;
import com.job.admin.core.model.JobInfo;
import com.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.job.admin.dao.JobGroupDao;
import com.job.admin.service.JobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

	@Resource
	private JobGroupDao jobGroupDao;
	@Resource
	private JobService jobService;
	
	@RequestMapping
	public String index(Model model, @RequestParam(required = false, defaultValue = "-1") int jobGroup) {

		// 枚举-字典
		model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());	// 路由策略-列表
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());								// Glue类型-字典
		model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());	// 阻塞处理策略-字典
		model.addAttribute("ExecutorFailStrategyEnum", ExecutorFailStrategyEnum.values());		// 失败处理策略-字典

		// 任务组
		List<JobGroup> jobGroupList =  jobGroupDao.findAll();
		model.addAttribute("JobGroupList", jobGroupList);
		model.addAttribute("jobGroup", jobGroup);

		return "jobinfo/jobinfo.index";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,  
			@RequestParam(required = false, defaultValue = "10") int length,
			int jobGroup, String jobDesc, String executorHandler, String filterTime) {
		
		return jobService.pageList(start, length, jobGroup, jobDesc, executorHandler, filterTime);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(JobInfo jobInfo) {
		return jobService.add(jobInfo);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(JobInfo jobInfo) {
		return jobService.update(jobInfo);
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public ReturnT<String> remove(int id) {
		return jobService.remove(id);
	}
	
	@RequestMapping("/pause")
	@ResponseBody
	public ReturnT<String> pause(int id) {
		return jobService.pause(id);
	}
	
	@RequestMapping("/resume")
	@ResponseBody
	public ReturnT<String> resume(int id) {
		return jobService.resume(id);
	}
	
	@RequestMapping("/trigger")
	@ResponseBody
	public ReturnT<String> triggerJob(int id) {
		return jobService.triggerJob(id);
	}
	
}
