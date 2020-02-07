package com.qr.common.mapper;

import com.qr.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserEntity set(int id);
}
