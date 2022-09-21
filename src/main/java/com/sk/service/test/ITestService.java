package com.sk.service.test;

import com.sk.entity.test.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Shocker
 * @since 2022-09-22
 */
public interface ITestService extends IService<Test> {

    List<Test> listTestForDB1();

    List<Test> listTestForDB2();


    List<Test> listTestTotal();
}
