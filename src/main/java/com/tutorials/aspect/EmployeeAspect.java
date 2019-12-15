package com.tutorials.aspect;

import com.tutorials.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Order(1) // ordering aspects, lower numbers have higher precedence
@Component
public class EmployeeAspect {

    /**
     * execution(
     * modifiers-pattern?
     * return-type-pattern
     * declaring-type-pattern?
     * method-name-pattern(param-pattern)
     * throws-pattern?
     * )
     */

    @Pointcut("execution(* com.tutorials.service.EmployeeService.*(..)) && args(name, id)")
    public void isEmployeeServiceMethod(final String name, final String id) {
    }

    @Pointcut("execution(public Integer com.tutorials.controller.*.get*())")
    public void isMethodStartsWthGet() {
    }

    /**
     * combining expressions
     */
    @Pointcut("isEmployeeServiceMethod(name, id) && isMethodStartsWthGet()")
    public void isMethodEmployeeServiceAndGet(final String name, final String id) {
    }


    /**
     * Using before advice, you can perform custom processing
     * before a joinpoint executes. Because a joinpoint in Spring
     * is always a method invocation, this essentially allows you
     * to perform preprocessing before the method executes.
     * Before advice has full access to the target of the method
     * invocation as well as the arguments passed to the method,
     * but it has no control over the execution of the method itself.
     * If the before advice throws an exception, further execution
     * of the interceptor chain (as well as the target method) will
     * be aborted, and the exception will propagate back up the
     * interceptor chain.
     */

    @Before("isEmployeeServiceMethod(name, id)")
    public void beforeAdvice(final JoinPoint joinPoint, final String name, String id) {
        System.out.println("Before advice:" + joinPoint.getSignature());
        System.out.println("Creating Employee with name: " + name + " and id: " + id);
    }

    /**
     * After-returning advice is executed only when the advised
     * method completes normally. However, the after (finally)
     * advice will be executed no matter the result of the advised
     * method. The advice is executed even when the advised
     * method fails and an exception is thrown.
     */

    @After("isEmployeeServiceMethod(name, id)")
    public void afterAdvice(final JoinPoint joinPoint, final String name, final String id) {
        System.out.println("After advice:" + joinPoint.getSignature());
        System.out.println("Successfully created Employee with name: " + name + " and id: " + id);
    }

    /**
     * In Spring, around advice is modeled using the AOP Alliance
     * standard of a method interceptor. Your advice is allowed to
     * execute before and after the method invocation, and you can
     * control the point at which the method invocation is allowed
     * to proceed. You can choose to bypass the method altogether
     */
    @Around("isMethodStartsWthGet()")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around advice: " + joinPoint.getSignature());

        final Object result = joinPoint.proceed();

        // modify the return value
        return (Integer) result + 100;
    }

    /**
     * After-returning advice is executed after the method
     * invocation at the joinpoint has finished executing and has
     * returned a value. The after- returning advice has access to
     * the target of the method invocation, the arguments passed
     * to the method, and the return value. Because the method
     * has already executed when the after-returning advice is
     * invoked, it has no control over the method invocation at
     * all. If the target method throws an exception, the afterreturning
     * advice will not be run, and the exception will be
     * propagated up to the call stack as usual.
     */
    @AfterReturning(pointcut = "execution(* com.tutorials.repository.EmployeeRepository.getEmployees())",
            returning = "employees")
    public void afterReturningAdvice(final JoinPoint joinPoint, final List<Employee> employees) {
        final String method = joinPoint.getSignature().toShortString();

        System.out.println("After returning advice on method: " + method + " and result: " + employees);
    }

    @AfterThrowing(pointcut = "execution(* com.tutorials.service.*.*())", throwing = "throwable")
    public void afterThrowingAdvice(final JoinPoint joinPoint, Throwable throwable) {
        System.out.println("After throwing advice called with the following exception: " + throwable.getMessage());
    }
}
