package com.home.sabir.java.springboot.aspect;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

	/*
	 * @Pointcut("within(@org.springframework.stereotype.Repository *)") public void
	 * repositoryClassMethods() {};
	 * 
	 * @Around("repositoryClassMethods()") public Object
	 * measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable { long
	 * start = System.nanoTime(); Object retval = pjp.proceed(); long end =
	 * System.nanoTime(); String methodName = pjp.getSignature().getName();
	 * logger.info("Execution of " + methodName + " took " +
	 * TimeUnit.NANOSECONDS.toMillis(end - start) + " ms"); return retval; }
	 */
    
	    
    @Pointcut("execution(* com.home.sabir.java.springboot.controller.*.*(..)) || execution(* com.home.sabir.java.springboot.service.*.*(..)) || execution(* com.home.sabir.java.springboot.dao.*.*(..)) ")
	public void beforeExecControllerAdvice(){}
	
	@Around("beforeExecControllerAdvice()")
	public Object beforeExecControllerAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Object retval = pjp.proceed();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String endTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        logger.info(className + " : " + methodName + " started at "+ endTimeStamp);
        return retval;
	}
	
	
	@Pointcut("execution(* com.home.sabir.java.springboot.controller.*.*(..)) || execution(* com.home.sabir.java.springboot.service.*.*(..)) || execution(* com.home.sabir.java.springboot.dao.*.*(..)) ")
	public void afterExecControllerAdvice(){}
	
	@Around("afterExecControllerAdvice()")
	public Object afterExecControllerAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String endTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        logger.info(className + " : " + methodName + " took " + 
          TimeUnit.NANOSECONDS.toMillis(end - start) + " ms and ended at "+ endTimeStamp);
        return retval;
	}
	


   
}