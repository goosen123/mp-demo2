package com.goosen.commons.shiro.factory;

//import com.stylefeng.guns.common.constant.factory.ConstantFactory;
//import com.stylefeng.guns.common.constant.state.ManagerStatus;
//import com.stylefeng.guns.common.persistence.dao.MenuMapper;
//import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.User;
import com.goosen.commons.shiro.ShiroUser;
//import com.stylefeng.guns.core.util.Convert;
import com.goosen.commons.service.UserService;
//import com.goosen.commons.utils.SpringContextHolder;


import com.goosen.commons.utils.EncryUtil;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service
//@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

	@Resource
//	@Autowired
	private UserService userService;

//    @Autowired
//    private MenuMapper menuMapper;

//    public static IShiro me() {
//        return SpringContextHolder.getBean(IShiro.class);
//    }

    @Override
    public User user(String account) {

    	User userParams = new User();
    	userParams.setAccount(account);
    	userParams.setUserType(1);
        User user = userService.findOne(userParams);//getByAccount(account);

        // 账号不存在
        if (null == user) {
        	throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }
        // 账号被冻结
//        if (user.getStatus() != ManagerStatus.OK.getCode()) {
//            throw new LockedAccountException();
//        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());            // 账号id
        shiroUser.setAccount(user.getAccount());// 账号
//        shiroUser.setDeptId(user.getDeptid());    // 部门id
//        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));// 部门名称
        shiroUser.setName(user.getUserName());        // 用户名称

//        Integer[] roleArray = Convert.toIntArray(user.getRoleid());// 角色集合
        List<String> roleList = new ArrayList<String>();
        List<String> roleNameList = new ArrayList<String>();
//        for (int roleId : roleArray) {
//            roleList.add(roleId);
//            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
//        }
        //先假设值
        roleList.add("20a00e3f72a54f91a24cb903ac84083f");
        //roleList.add("");
        roleNameList.add("超级管理员");
        //roleNameList.add("");
        
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(String roleId) {
//        List<String> resUrls = menuMapper.getResUrlsByRoleId(roleId);
    	////先假设值
    	List<String> resUrls = new ArrayList<String>();
    	resUrls.add("#");
    	resUrls.add("/mgr");
    	resUrls.add("/mgr/add");
    	resUrls.add("/mgr/edit");
        return resUrls;
    }

    @Override
    public String findRoleNameByRoleId(String roleId) {
//        return ConstantFactory.me().getSingleRoleTip(roleId);
    	return "超级管理员";
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = EncryUtil.encodeByMD5("123456");//user.getPassword();
        // 密码加盐处理
        String source = "8l9ws";//user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
