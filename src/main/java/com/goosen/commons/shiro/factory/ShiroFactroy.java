package com.goosen.commons.shiro.factory;

//import com.stylefeng.guns.common.constant.factory.ConstantFactory;
//import com.stylefeng.guns.common.constant.state.ManagerStatus;
//import com.stylefeng.guns.common.persistence.dao.MenuMapper;
//import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.goosen.commons.constants.factory.IConstantFactory;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.Menu;
import com.goosen.commons.model.po.RoleMenu;
import com.goosen.commons.model.po.User;
import com.goosen.commons.model.po.UserRole;
import com.goosen.commons.shiro.ShiroUser;
import com.goosen.commons.service.MenuService;
import com.goosen.commons.service.RoleMenuService;
import com.goosen.commons.service.UserRoleService;
//import com.stylefeng.guns.core.util.Convert;
import com.goosen.commons.service.UserService;
//import com.goosen.commons.utils.SpringContextHolder;


import com.goosen.commons.utils.CommonUtil;
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
	@Resource
	private IConstantFactory iConstantFactory;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RoleMenuService roleMenuService;
	@Resource
	private MenuService menuService;

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
        if (user.getStatus() != 1) {//ManagerStatus.OK.getCode()
        	throw new BusinessException(ResultCode.USER_ACCOUNT_FORBIDDEN);
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());            // 账号id
        String userId = user.getId();
        shiroUser.setAccount(user.getAccount());// 账号
//        shiroUser.setDeptId(user.getDeptid());    // 部门id
//        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));// 部门名称
        shiroUser.setName(user.getUserName());        // 用户名称

//        Integer[] roleArray = Convert.toIntArray(user.getRoleid());// 角色集合
        List<String> roleList = new ArrayList<String>();
        List<String> roleNameList = new ArrayList<String>();
        //拿角色id集
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		List<UserRole> list = userRoleService.findListByWhere(userRole);
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				UserRole userRoleNew = list.get(i);
				if(userRoleNew != null){
					String roleId = userRoleNew.getRoleId();
					String roleName = iConstantFactory.getSingleRoleName(roleId);
					if(!CommonUtil.isTrimNull(roleId) && !CommonUtil.isTrimNull(roleName)){
						roleList.add(roleId);
						roleNameList.add(roleName);
					}
				}
			}
		}
//        for (int roleId : roleArray) {
//            roleList.add(roleId);
//            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
//        }
//        //先假设值
//        roleList.add("20a00e3f72a54f91a24cb903ac84083f");
//        //roleList.add("");
//        roleNameList.add("超级管理员");
//        //roleNameList.add("");
        
        shiroUser.setRoleList(roleList);//角色id集
        shiroUser.setRoleNames(roleNameList);//角色名称集

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(String roleId) {
//        List<String> resUrls = menuMapper.getResUrlsByRoleId(roleId);
    	////先假设值
//    	List<String> resUrls = new ArrayList<String>();
//    	resUrls.add("#");
//    	resUrls.add("/mgr");
//    	resUrls.add("/mgr/add");
//    	resUrls.add("/mgr/edit");
//    	resUrls.add("/mgr/delete");
//    	resUrls.add("/mgr/setRole");
    	//拿权限集   实质--权限就是菜单的url
    	List<String> resUrls = new ArrayList<String>();
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		List<RoleMenu> list = roleMenuService.findListByWhere(roleMenu);
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				RoleMenu roleMenuNew = list.get(i);
				if(roleMenuNew != null){
					String menuId = roleMenuNew.getMenuId();
					if(!CommonUtil.isTrimNull(menuId)){
						Menu menu = menuService.findById(menuId);
						if(menu != null && !CommonUtil.isTrimNull(menu.getUrl()))
							resUrls.add(menu.getUrl());
					}
				}
			}
		}
		resUrls.add("/mgr/delete");
    	resUrls.add("/mgr/setRole");
		resUrls.add("/menu/remove");
    	resUrls.add("/role/remove");
    	resUrls.add("/role/setAuthority");
        return resUrls;
    }

    @Override
    public String findRoleNameByRoleId(String roleId) {
//        return ConstantFactory.me().getSingleRoleTip(roleId);
    	return iConstantFactory.getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
    	String credentials = user.getPassword();//"b00a7ed95a0dd3f6bf5cb68c6bb547a6";//EncryUtil.encodeByMD5("123456");
    	// 密码加盐处理
    	String source = "8l9ws";//user.getSalt();
    	ByteSource credentialsSalt = new Md5Hash(source);
    	return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    	
//        String credentials = EncryUtil.encodeByMD5("123456");//user.getPassword();
//        // 密码加盐处理
//        String source = "8l9ws";//user.getSalt();
//        ByteSource credentialsSalt = new Md5Hash(source);
//        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
