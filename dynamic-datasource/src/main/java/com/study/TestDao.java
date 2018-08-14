package com.study;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by liyang on 2018/8/10.
 */
public interface TestDao {
    public String getValue();
}
