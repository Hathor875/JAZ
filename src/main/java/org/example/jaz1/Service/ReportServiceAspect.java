package org.example.jaz1.Service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReportServiceAspect {
    @Pointcut("execution(* org.example.jaz1.Service.ReportService.*(..))")
    public void reportServiceMethods() {}
    @AfterThrowing(pointcut = "reportServiceMethods()", throwing = "ex")
    public void handleReportServiceException(RuntimeException ex) {
        System.err.println("Exception in ReportService: " + ex.getMessage());
        if (ex instanceof ReportNotFoundException) {
            throw ex;
        }
    }
}
