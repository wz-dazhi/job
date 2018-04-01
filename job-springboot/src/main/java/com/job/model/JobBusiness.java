package com.job.model;

import lombok.Data;

import java.util.Date;

@Data
public class JobBusiness {
    private String id;

    private String jobName;

    private String jobAction;

    private Date jobDate;

}