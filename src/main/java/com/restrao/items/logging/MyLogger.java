package com.restrao.items.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class MyLogger {
	
	Logger log=LoggerFactory.getLogger(MyLogger.class);
	
	@Pointcut(value = "execution(* com.restrao.items.controller.*.*(..))")
	public void controllerLayerPointCut() {
		
	}

	@Pointcut(value = "execution(* com.restrao.items.services.*.*(..))")
	public void serviceLayerPointCut() {
		
	}

	@Pointcut(value = "execution(* com.restrao.items.repos.*.*(..))")
	public void repositoryLayerPointCut() {
		
	}
	
	@Around("controllerLayerPointCut()")
	public Object controllerLayerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		return LoggerOnLayer(joinPoint, "CONTROLLER");
	}

	@Around("serviceLayerPointCut()")
	public Object serviceLayerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		return LoggerOnLayer(joinPoint, "SERVICE");
	}

	@Around("repositoryLayerPointCut()")
	public Object repositoryLayerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		return LoggerOnLayer(joinPoint, "REPOSITORY");
	}
	
	public Object LoggerOnLayer(ProceedingJoinPoint joinPoint, String layer) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().toString();
		Object[] arguments = joinPoint.getArgs();
		log.info("##### (" + layer + "-INV) Class: " + className + ", Method: " + methodName + ", Arguments: " + mapper.writeValueAsString(arguments));
		Object proceeded = joinPoint.proceed();
		log.info("##### (" + layer + "-RET) Class: " + className + ", Response: " + mapper.writeValueAsString(proceeded));
		return proceeded;
	}
}
