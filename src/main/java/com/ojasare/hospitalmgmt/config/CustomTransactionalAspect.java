package com.ojasare.hospitalmgmt.config;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
@RequiredArgsConstructor
public class CustomTransactionalAspect {

    private final ApplicationContext applicationContext;

    @Around("@annotation(customTransactional)")
    public Object handleTransaction(ProceedingJoinPoint joinPoint, CustomTransactional customTransactional) throws Throwable {
        String transactionManagerName = customTransactional.transactionManager();

        if ("defaultTransactionManager".equals(transactionManagerName)) {
            transactionManagerName = "transactionManager";
        }

        PlatformTransactionManager transactionManager = applicationContext.getBean(transactionManagerName, PlatformTransactionManager.class);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

}
