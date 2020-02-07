package com.qr.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qr.admin.mapper.RoleMapper;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.RoleEntity;
import com.qr.common.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    private Logger logger= LoggerFactory.getLogger(RoleService.class);

    public ResultBean<List<RoleEntity>> getAllRole(RoleEntity roleEntity){
        //使用PageHelper获取分页和分页count
        if(roleEntity.getPage()==null||roleEntity.getPage()==0){
            roleEntity.setPage(1);
        }
        if(roleEntity.getLimit()==null||roleEntity.getLimit()==0){
            roleEntity.setLimit(99999);
        }
        PageHelper.startPage(roleEntity.getPage(),roleEntity.getLimit());//页码，每页数量
        List<RoleEntity> adList=roleMapper.getAllRole();//通过mybatis获取所有结果
        System.out.println("role list :"+adList);
        PageInfo<RoleEntity> pageInfo=new PageInfo<>(adList);//将所有结果给pageinfo
        //将结果集封装到页面bean中
        ResultBean<List<RoleEntity>> result=new ResultBean<>(pageInfo.getList(),(int)pageInfo.getTotal());
        return result;
    }

    /**
     * 通过ID获取角色list，只需要list即可，不需要封装，不需要分页
     * @param
     * @return
     */
    public List<RoleEntity> getRoleById(AdminEntity adminEntity){
        //使用PageHelper获取分页和分页count
        //List<RoleEntity> list=roleMapper.getRoleById(adminEntity);//通过mybatis获取所有结果

        //System.out.println("rolesList: "+list);
        return null;
    }
    public ResultBean<Object> addRole(RoleEntity roleEntity){
        logger.info("addRole--前端传过来的bean:"+roleEntity.toString());
        if(roleEntity.getStatus()!=null&&roleEntity.getStatus().equals("on")){//转换switch状态
            roleEntity.setStatus("0");
        }else if(roleEntity.getStatus()==null){
            roleEntity.setStatus("1");
        }
        int result= roleMapper.addRole(roleEntity);
        ResultBean rb=new ResultBean();
        if(result!=1){
            rb.setCode(1);
            rb.setMsg("系统内部错误，请联系管理员!");
        }
        return rb;
    }
    public RoleEntity getRole(int id){
        return roleMapper.getRole(id);
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,timeout = 36000,rollbackFor = Exception.class)
    public ResultBean<RoleEntity> delRole(List<RoleEntity> roleEntityList){
        logger.info("delRole--前端传过来的bean:"+roleEntityList.toString());
        ResultBean rb=new ResultBean();
        for (RoleEntity roleEntity:roleEntityList) {//是否删除系统自带用户角色
            if(roleEntity.getId()==1||roleEntity.getId()==2||roleEntity.getId()==3||roleEntity.getId()==4||roleEntity.getId()==5||roleEntity.getId()==6){
                rb.setCode(1);
                rb.setMsg("系统自带用户角色不可删除！");
                return rb;
            }
        }
        //正常执行
        int result=roleMapper.delRole(roleEntityList);
        int count=roleEntityList.size();
        if(result!=count){
            rb.setCode(1);
            rb.setMsg("部分操作未执行成功，请联系管理员!");
        }
        //执行修改user_role表，将已经删除的角色原有的用户角色重新设置一下
        roleMapper.resetUserRole(roleEntityList);
        return rb;
    }
    public ResultBean<RoleEntity> editRole(RoleEntity roleEntity){
        logger.info("editRole--前端传过来的bean:"+roleEntity.toString());
        ResultBean rb=new ResultBean();
        //默认的四个角色不可以编辑
        if(roleEntity.getId()==1||roleEntity.getId()==2||roleEntity.getId()==3||roleEntity.getId()==4||roleEntity.getId()==5||roleEntity.getId()==6){
            roleEntity.setName("");
            roleEntity.setRoleKey("");
        }
        if(roleEntity.getStatus()!=null&&roleEntity.getStatus().equals("on")){//转换switch状态
            roleEntity.setStatus("0");
        }else if(roleEntity.getStatus()==null){
            roleEntity.setStatus("1");
        }

        int result=roleMapper.editRole(roleEntity);

        if(result!=1){
            rb.setCode(1);
            rb.setMsg("部分操作未执行成功，请联系管理员!");
        }
        return rb;
    }
}
