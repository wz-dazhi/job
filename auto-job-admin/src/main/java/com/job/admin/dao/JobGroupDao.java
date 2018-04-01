package com.job.admin.dao;

import com.job.admin.core.model.JobGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
public interface JobGroupDao {

    public List<JobGroup> findAll();

    public List<JobGroup> findByAddressType(@Param("addressType") int addressType);

    public int save(JobGroup jobGroup);

    public int update(JobGroup jobGroup);

    public int remove(@Param("id") int id);

    public JobGroup load(@Param("id") int id);
}
