package com.qr.admin.mapper;

import com.qr.common.entity.ChannelEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 18:49 2020-01-20
 */
@Repository
public interface ChannelMapper {
    int addChannel(ChannelEntity channelEntity);//插入User中
    int addChannelUser(ChannelEntity channelEntity);//插入Channel_user中
    int addUserRole(ChannelEntity channelEntity);//插入user_role中
    int delChannel(List<ChannelEntity> channelEntityList);
    int editChannel(ChannelEntity channelEntity);
    int editUserRole(ChannelEntity channelEntity);
    List<ChannelEntity> getAllChannel();
    List<ChannelEntity> queryChannel(ChannelEntity channelEntity);
    ChannelEntity getChannelByAccountName(ChannelEntity channelEntity);
    ChannelEntity getChannelByUsername(ChannelEntity channelEntity);
}
