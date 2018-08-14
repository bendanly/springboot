package com.study;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liyang on 2018/8/10.
 */
@Service
@Transactional
public class TestService {
    @Autowired
    private TestDao testDao;

    @Autowired
    private TestService2 testService2;

    @TargetDataSource(DatabaseType.datasource2)
    public String getValue(){
        //DatabaseContextHolder.setDatabaseType(DatabaseType.datasource2);
        String value = testDao.getValue();
        return value;
    }
    @TargetDataSource(DatabaseType.datasource1)
    public String getValue2(){
        //DatabaseContextHolder.setDatabaseType(DatabaseType.datasource2);
        String value = testDao.getValue();
        return value;
    }

    public String getValue3(){
        DatabaseContextHolder.setDatabaseType(DatabaseType.datasource2);
        String t1 = testDao.getValue();
        DatabaseContextHolder.setDatabaseType(DatabaseType.datasource1);
        String t2 = testDao.getValue();
        DatabaseContextHolder.setDatabaseType(DatabaseType.datasource2);
        String t3 = testDao.getValue();
        return t1+t2+t3;
    }
    public String getValue4(){
        String value = testDao.getValue();
        return value;
    }

    @TargetDataSource(DatabaseType.datasource2)
    public String getValue5(){
        //暴露当前线程绑定的代理类，要求exposeProxy = true见启动类注解。这样类内部方法调用的时候，AOP切面才会起效。
        //getValue用的是自己注解切面数据源2
        String t1 = ((TestService) AopContext.currentProxy()).getValue();
        //getValue2用的是数据源1
        String t2 = ((TestService) AopContext.currentProxy()).getValue2();
        //getValue4没有注解切面，用的是默认数据源
        String t3 = ((TestService) AopContext.currentProxy()).getValue4();
        return t1 +t2 +t3;
    }

    @TargetDataSource(DatabaseType.datasource2)
    public String getValue6(){
        String t1 = testDao.getValue();
        String t2 = testService2.getValue();
        return t1+t2;
    }
}
