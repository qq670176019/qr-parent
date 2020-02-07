package com.qr.common.service;

import com.qr.common.entity.UserEntity;
import com.qr.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserEntity set(int id) {
        return userMapper.set(id);
    }
}
