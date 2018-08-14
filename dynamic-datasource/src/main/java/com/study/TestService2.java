package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/8/14.
 */
@Service
public class TestService2 {
    @Autowired
    private TestDao testDao;

    @TargetDataSource(DatabaseType.datasource1)
    public String getValue(){
        String value = testDao.getValue();
        return value;
    }
}
