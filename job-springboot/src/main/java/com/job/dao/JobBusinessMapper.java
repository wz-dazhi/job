package com.job.dao;

import com.job.model.JobBusiness;
import com.job.model.JobBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobBusinessMapper {
    int countByExample(JobBusinessExample example);

    int deleteByExample(JobBusinessExample example);

    int deleteByPrimaryKey(String id);

    int insert(JobBusiness record);

    int insertSelective(JobBusiness record);

    List<JobBusiness> selectByExample(JobBusinessExample example);

    JobBusiness selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JobBusiness record, @Param("example") JobBusinessExample example);

    int updateByExample(@Param("record") JobBusiness record, @Param("example") JobBusinessExample example);

    int updateByPrimaryKeySelective(JobBusiness record);

    int updateByPrimaryKey(JobBusiness record);
}