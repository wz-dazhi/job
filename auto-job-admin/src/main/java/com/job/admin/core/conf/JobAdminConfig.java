package com.job.admin.core.conf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * auto-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class JobAdminConfig implements InitializingBean{
    private static JobAdminConfig adminConfig = null;
    public static JobAdminConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;
    }

    @Value("${job.mail.host}")
    private String mailHost;

    @Value("${job.mail.port}")
    private String mailPort;

    @Value("${job.mail.username}")
    private String mailUsername;

    @Value("${job.mail.password}")
    private String mailPassword;

    @Value("${job.mail.sendNick}")
    private String mailSendNick;

    @Value("${job.login.username}")
    private String loginUsername;

    @Value("${job.login.password}")
    private String loginPassword;

    @Value("${job.i18n}")
    private String i18n;


    public String getMailHost() {
        return mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getMailSendNick() {
        return mailSendNick;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getI18n() {
        return i18n;
    }

}
