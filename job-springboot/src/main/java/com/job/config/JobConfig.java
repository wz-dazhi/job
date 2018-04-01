package com.job.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: job
 * @package: com.job.config
 * @className: JobConfig
 * @description:
 * @author: wangzhi
 * @createDate: 2018/3/31 22:01
 **/
@Slf4j
@Configuration
@ComponentScan(basePackages = "com.job.jobhandler")
public class JobConfig {
    
    @Value("${job.admin.addresses}")
    private String adminAddresses;

    @Value("${job.executor.appname}")
    private String appName;

    @Value("${job.executor.ip}")
    private String ip;

    @Value("${job.executor.port}")
    private int port;

    @Value("${job.accessToken}")
    private String accessToken;

    @Value("${job.executor.logpath}")
    private String logPath;

    @Value("${job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {

        if (log.isInfoEnabled()){
            log.info("---------->>>>>>>>>>> auto-job config init......");
        }

        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses(adminAddresses);
        xxlJobExecutor.setAppName(appName);
        xxlJobExecutor.setIp(ip);
        xxlJobExecutor.setPort(port);
        xxlJobExecutor.setAccessToken(accessToken);
        xxlJobExecutor.setLogPath(logPath);
        xxlJobExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobExecutor;
    }
}
