package cn.itsource._06_auto_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class TXManager {

    //设置切点
    @Pointcut("execution(* cn.itsource._06_auto_aop.service.I*Service.*(..))")
    public void pointcut(){}
    //前置通知
    //@Before("pointcut()")
    public static void begin(){
        System.out.println("开启事务");
    }
    //后置通知
    //@AfterReturning("pointcut()")
    public static void commit(){
        System.out.println("提交事务");
    }
    //异常通知
    //@AfterThrowing(pointcut = "pointcut()",throwing = "e")
    public static void rollback(Throwable e){
        System.out.println("回滚事务,"+"异常原因:"+e.getMessage());
    }
    //@After("pointcut()")
    public static void close(){
        System.out.println("关闭事务");
    }
    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint){
        try {
            begin();
            joinPoint.proceed();
            commit();
        } catch (Throwable e) {
            rollback(e);
        } finally {
            close();
        }
    }

}
