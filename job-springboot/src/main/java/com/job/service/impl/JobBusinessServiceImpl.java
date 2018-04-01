package com.job.service.impl;

import com.job.dao.JobBusinessMapper;
import com.job.model.JobBusiness;
import com.job.service.IJobBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName: job
 * @package: com.job.service.impl
 * @className: JobBusinessImpl
 * @description:
 * @author: wangzhi
 * @createDate: 2018/3/31 21:54
 **/
@Slf4j
@Service
public class JobBusinessServiceImpl implements IJobBusinessService {

    @Autowired
    private JobBusinessMapper jobBusinessMapper;

    @Override
    public int add(JobBusiness business) {
        return jobBusinessMapper.insertSelective(business);
    }
}
