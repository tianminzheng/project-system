
package com.tianyalan.usercenter.domain.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.tianyalan.usercenter.domain.model.user.EncryptionService;
import com.tianyalan.usercenter.domain.model.user.PasswordService;

//使用ApplicationContext加载领域服务
public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static EncryptionService encryptionService() {
        return (EncryptionService) applicationContext.getBean("encryptionService");
    }
    
    public static PasswordService passwordService() {
        return (PasswordService) applicationContext.getBean("passwordService");
    }

    @Override
    public synchronized void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = anApplicationContext;
        }
    }
}
