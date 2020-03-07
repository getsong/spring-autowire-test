package com.getsong.mockito.annotation.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * TODO: Purpose
 *
 * @author getsong
 * @since 29/1/2020 12:07 AM
 */
@Aspect
@Log4j2
@Component
public class LogAspect {

  //  @Pointcut("execution(* com.getsong.mockito.annotation.GinService.pourGin())")
  //  public void pourGin() {}

  @Before("execution(* com.getsong.mockito.annotation.GinService.pourGin())")
  public void log(JoinPoint joinPoint) {
    log.info("aspect: log before {}", joinPoint.getSignature());
  }
}
