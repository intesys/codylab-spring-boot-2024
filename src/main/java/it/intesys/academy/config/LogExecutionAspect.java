package it.intesys.academy.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Logging aspect for all the components in {@link it.intesys.scb.depositaccounts.onboarding.input}
 */
@Aspect
@Component
public class LogExecutionAspect {


  private static final Logger log = LoggerFactory.getLogger(LogExecutionAspect.class);

  /**
   * Advice that logs when a method is entered and exited.
   *
   * @param joinPoint join point for advice
   * @return result
   * @throws Throwable throws IllegalArgumentException
   */
  @Around("@annotation(it.intesys.academy.config.LogExecution)")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Enter: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    var sw = new StopWatch();
    try {
      sw.start();
      Object result = joinPoint.proceed();
      sw.stop();
      log.info("Exit: {}.{}() - {}ms", joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), sw.getTotalTimeMillis());
      return result;
    } catch (Exception e) {
      sw.stop();
      log.info("Exception: {}.{}() with message {} - {}ms", joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(),
          e.getMessage(), sw.getTotalTimeMillis());
      throw e;
    }
  }

}
