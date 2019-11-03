package cn.itsource._05_xmlaop.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class TXManager {
    public static void begin(){
        System.out.println("开启事务");
    }

    public static void commit(){
        System.out.println("提交事务");
    }

    public static void rollback(Throwable e){
        System.out.println("回滚事务,"+"异常原因:"+e.getMessage());
    }

    public static void close(){
        System.out.println("关闭事务");
    }

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
