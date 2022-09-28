package com.sk.service.user.impl;

import com.sk.entity.user.User;
import com.sk.mapper.user.UserMapper;
import com.sk.service.user.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shocker
 * @since 2022-09-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
