package com.qr.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qr.admin.mapper.ChannelMapper;
import com.qr.admin.mapper.RoleMapper;
import com.qr.common.bean.Permissions;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.ChannelEntity;
import com.qr.common.entity.RoleEntity;
import com.qr.common.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 18:48 2020-01-20
 */
@Service
@Slf4j
public class ChannelService {
    @Autowired
    ChannelMapper channelMapper;
    @Autowired
    RoleMapper roleMapper;


    /**
     * 获取所有管理员列表
     *
     * @return
     */
    public ResultBean<List<ChannelEntity>> getAllChannel(ChannelEntity channelEntity) {
        log.info("getAllChannel--前端传过来的bean:" + channelEntity.toString());
        //使用PageHelper获取分页和分页count
        PageHelper.startPage(channelEntity.getPage(), channelEntity.getLimit());//页码，每页数量
        List<ChannelEntity> adList = channelMapper.getAllChannel();//通过mybatis获取所有结果
        //为每个Channel查询出其所属角色
        for (ChannelEntity ad:adList) {
            ad.setRoleEntity(roleMapper.getRoleById(channelEntity));
        }
        PageInfo<ChannelEntity> pageInfo = new PageInfo<>(adList);//将所有结果给pageinfo
        //将结果集封装到页面bean中
        ResultBean<List<ChannelEntity>> ChannelistratorEntityResultBeanList = new ResultBean<>(pageInfo.getList(), (int) pageInfo.getTotal());
        return ChannelistratorEntityResultBeanList;
    }

    /**
     * 查询某个管理员
     *
     * @return
     */
    public ResultBean<List<ChannelEntity>> queryChannel(ChannelEntity ChannelEntity) {
        log.info("queryChannel--前端传过来的bean:" + ChannelEntity.toString());
        //使用PageHelper获取分页和分页count
        PageHelper.startPage(ChannelEntity.getPage(), ChannelEntity.getLimit());//页码，每页数量
        List<ChannelEntity> adList = channelMapper.queryChannel(ChannelEntity);//通过mybatis获取所有结果
        PageInfo<ChannelEntity> pageInfo = new PageInfo<>(adList);//将所有结果给pageinfo
        List resultList = pageInfo.getList();
        System.out.println("后台查询到的数据" + resultList.toString());
        //将结果集封装到页面bean中
        ResultBean<List<ChannelEntity>> ChannelistratorEntityResultBeanList = new ResultBean<>(resultList, resultList.size());
        System.out.println("resultBean: " + ChannelistratorEntityResultBeanList);
        return ChannelistratorEntityResultBeanList;
    }

    /**
     * 新增管理员
     *
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 36000, rollbackFor = Exception.class)
    public ResultBean addChannel(ChannelEntity ChannelEntity) {
        log.info("addChannel--前端传过来的bean:" + ChannelEntity.toString());
        ChannelEntity.setSalt(PasswordUtils.getRandomSalt());//获取salt
        String MD5SaltPwd = PasswordUtils.getSaltMD5(PasswordUtils.md5Hex(ChannelEntity.getPassword()), ChannelEntity.getSalt());//获取加盐密码
        ChannelEntity.setPassword(MD5SaltPwd);
        if (ChannelEntity.getStatus() != null && ChannelEntity.getStatus().equals("on")) {//转换switch状态
            ChannelEntity.setStatus("0");
        } else if (ChannelEntity.getStatus() == null) {
            ChannelEntity.setStatus("1");
        }
        if (ChannelEntity.getLocked() != null && ChannelEntity.getLocked().equals("on")) {//转换switch状态
            ChannelEntity.setLocked("0");
        } else if (ChannelEntity.getLocked() == null) {
            ChannelEntity.setLocked("1");
        }
        int result1 = channelMapper.addChannel(ChannelEntity);//在user表中插入数据
        //System.out.println("插入user之后的ChannelEntity数据： "+ChannelEntity);
        int result2 = channelMapper.addChannelUser(ChannelEntity);//在Channel_user表中插入数据
        int result3=channelMapper.addUserRole(ChannelEntity);
        //System.out.println("插入Channel_user结果： "+result2);
        RoleEntity rr=roleMapper.getRoleById(ChannelEntity);
        //System.out.println("查询出来的roleEntity： "+);
        ChannelEntity.setRoleEntity(rr);
        //int result4 = channelMapper.addUserRole(ChannelEntity);//操作user_role,设置角色，这个地方出了问题
        ResultBean rb = new ResultBean();
        if (!(result1 == 1 && result2 == 1)) {
            rb.setCode(1);
            rb.setMsg("系统内部错误，请联系管理员!");
        }
        return rb;
    }

    /**
     * 删除管理员
     *
     * @param ChannelEntityList
     * @return
     */
    public ResultBean<ChannelEntity> delChannel(List<ChannelEntity> ChannelEntityList) {
        log.info("delChannel--前端传过来的bean:" + ChannelEntityList.toString());
        ResultBean rb = new ResultBean();

        for (ChannelEntity ad : ChannelEntityList) {//如果list中有管理员，则不允许被删除，直接返回
            if (ad.getId().equals("1")) {
                rb.setCode(1);
                rb.setMsg("超级管理员不可删除！");
                return rb;
            }
        }
        //正常执行以下代码
        int result = channelMapper.delChannel(ChannelEntityList);
        int count = ChannelEntityList.size();
        if (result != count) {
            rb.setCode(1);
            rb.setMsg("部分操作未执行成功，请联系管理员!");
        }
        return rb;
    }

    /**
     * 编辑管理员
     *
     * @param ChannelEntity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 36000, rollbackFor = Exception.class)
    public ResultBean<ChannelEntity> editChannel(ChannelEntity ChannelEntity) {
        //前台不允许修改accountName，是否还需要在后台约束一下？
        log.info("editChannel--前端传过来的bean:" + ChannelEntity.toString());
        ResultBean rb = new ResultBean();
        if (ChannelEntity.getId() == 1) {//超级管理员不可以编辑账号、名称、状态
            ChannelEntity.setUsername("");
            ChannelEntity.setName("");
        }
        if (ChannelEntity.getStatus() != null && ChannelEntity.getStatus().equals("on")) {//转换switch状态
            ChannelEntity.setStatus("0");
        } else if (ChannelEntity.getStatus() == null) {
            ChannelEntity.setStatus("1");
        }
        if (ChannelEntity.getLocked() != null && ChannelEntity.getLocked().equals("on")) {//转换switch状态
            ChannelEntity.setLocked("0");
        } else if (ChannelEntity.getLocked() == null) {
            ChannelEntity.setLocked("1");
        }
        if (ChannelEntity.getPassword() != null) {//如果要修改密码
            ChannelEntity.setSalt(PasswordUtils.getRandomSalt());//获取salt
            String MD5SaltPwd = PasswordUtils.getSaltMD5(PasswordUtils.md5Hex(ChannelEntity.getPassword()), ChannelEntity.getSalt());//获取加盐密码
            ChannelEntity.setPassword(MD5SaltPwd);
        }
        int result = channelMapper.editChannel(ChannelEntity);//修改user表
        int result2 = channelMapper.editUserRole(ChannelEntity);//修改user_role表

        if (result != 1) {
            rb.setCode(1);
            rb.setMsg("部分操作未执行成功，请联系管理员!");
        }
        return rb;
    }

    /**
     * 获取单个管理员--登录使用
     *
     * @param ChannelEntity
     * @return
     */
    public ResultBean<ChannelEntity> getChannelByAccountName(ChannelEntity ChannelEntity) {
        ResultBean<ChannelEntity> result = new ResultBean<>();
        ChannelEntity ad2 = channelMapper.getChannelByAccountName(ChannelEntity);
        return result;
    }

    /**
     * 登录
     *
     * @param ChannelEntity
     * @return
     */
    public ResultBean<ChannelEntity> login(ChannelEntity ChannelEntity) {
        //先验证是否有此账户
        //然后查出此账户所有权限和所属角色
        //返回给controller
        return null;
    }

    /**
     * 获取用户所属角色
     *
     * @param ChannelEntity
     * @return
     */
    private List<RoleEntity> getRoles(ChannelEntity ChannelEntity) {
        return null;
    }

    /**
     * 获取某角色的权限集合
     *
     * @return
     */
    private List<Permissions> getPermissions(RoleEntity roleEntity) {
        return null;
    }

    /**
     * 登录使用，根据用户名和密码查询相关用户
     *
     * @param ChannelEntity
     * @return
     */
    public ChannelEntity getChannelByUsername(ChannelEntity ChannelEntity) {
        ChannelEntity queryEntity = channelMapper.getChannelByUsername(ChannelEntity);


        return queryEntity;
    }

}
