package com.sk.service.test.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sk.entity.test.Test;
import com.sk.mapper.test.TestMapper;
import com.sk.service.test.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Shocker
 * @since 2022-09-22
 */
@Service
@Slf4j
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    @DS("")
    @Override
    public List<Test> listTestForDB1() {
        return baseMapper.selectList(Wrappers.lambdaQuery());
    }

    @DS("slave")
    @Override
    public List<Test> listTestForDB2() {
        return baseMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public List<Test> listTestTotal() {
        List<Test> list = this.lambdaQuery().groupBy(Test::getPhone).select(Test::getPhone, Test::getCount).list();
        log.info("根据手机号分组，同样手机号的数量有多少：{}", JSONUtil.toJsonStr(list));
        return list;
    }
}
