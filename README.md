# spring-aop
AOP tutorial with Spring
SpringBoot AOP demonstration.




Using @Pointcut expressions.


Advices used:


@Before:


       Using before advice, you can perform custom processing before a joinpoint executes. Because a joinpoint in Spring
       is always a method invocation, this essentially allows you to perform preprocessing before the method executes.
       Before advice has full access to the target of the method invocation as well as the arguments passed to the method, 
       but it has no control over the execution of the method itself.
       If the before advice throws an exception, further execution of the interceptor chain (as well as the target method) will
       be aborted, and the exception will propagate back up the interceptor chain.


@After:

       After-returning advice is executed only when the advised method completes normally. However, the after (finally)
       advice will be executed no matter the result of the advised method. 
       The advice is executed even when the advised method fails and an exception is thrown.


@Around:

       In Spring, around advice is modeled using the AOP Alliance standard of a method interceptor. Your advice is allowed to
       execute before and after the method invocation, and you can control the point at which the method invocation is allowed
       to proceed. 
       You can choose to bypass the method altogether.
     


@AfterReturning:

       After-returning advice is executed after the method invocation at the joinpoint has finished executing and has
       returned a value. 
       The after- returning advice has access to the target of the method invocation, the arguments passed to the method, 
       and the return value. 
       Because the method has already executed when the after-returning advice is invoked, it has no control over the method
       invocation at all. 
       If the target method throws an exception, the afterreturning advice will not be run, and the exception will be propagated
       up to    the call stack as usual.


@AfterThrowing:


       Throws advice is executed after a method invocation returns, but only if that invocation threw an exception. 
       It is possible for throws advice to catch only specific exceptions, and if you choose to do so, you can access the method
       that threw the exception, the arguments passed into the invocation, and the target of the invocation.



