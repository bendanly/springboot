package com.study;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by liyang on 2018/8/10.
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
    /*
     * @Before("@annotation(ds)")
     * 的意思是：
     *
     * @Before：在方法执行之前进行执行：
     * @annotation(targetDataSource)：
     * 会拦截注解targetDataSource的方法，否则不拦截;
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {
        //获取当前的指定的数据源;
        DatabaseType databaseType = targetDataSource.value();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        System.out.println("切换到指定数据源 : {"+targetDataSource.value()+"} > {"+point.getSignature()+"}");
        //找到的话，那么设置到动态数据源上下文中。
        DatabaseContextHolder.setDatabaseType(databaseType);
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        System.out.println("清除指定数据源 : {"+targetDataSource.value()+"} > {"+point.getSignature()+"}");
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DatabaseContextHolder.clearDatabaseType();
    }

    //////////////////////////注解方式2，这里效果和上面等同，为了演示用。和上面的效果是重复的//////////////////////////
    @Around("@annotation(targetDataSource)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint,TargetDataSource targetDataSource) throws Throwable {
        Object result = null;
        try {
            System.out.println("=========begin=========");
            DatabaseType databaseType = targetDataSource.value();
            DatabaseContextHolder.setDatabaseType(databaseType);
            result = proceedingJoinPoint.proceed();
            System.out.println("=========end=========");
        } finally {
            DatabaseContextHolder.clearDatabaseType();
        }
        return result;
    }
}
