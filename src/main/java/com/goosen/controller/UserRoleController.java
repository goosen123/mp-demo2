package com.goosen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.model.po.UserRole;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.service.UserRoleService;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="用户角色管理",description="用户角色管理")
@RestController
@RequestMapping(value="userRole")
public class UserRoleController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserRoleService userRoleService;
	
	@ApiOperation(value="分配角色")
	@ResponseResult
	@RequestMapping(value = {"assignRole"},method=RequestMethod.POST)
	public BaseCudRespData<String> assignPerm(@ApiParam(name="userId",value="用户id",required=true)String userId,@ApiParam(name="roleIds",value="角色id集",required=true) @RequestParam("roleIds")List<Object> roleIds) throws Exception {
		
		CheckUtil.notEmpty(userId, "userId", "userId不能空");
		CheckUtil.check(roleIds!=null && roleIds.size() > 0, "roleIds", "roleIds不能空");
		
		//删除该用户所有的角色
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRoleService.deleteByWhere(userRole);
		
		//添加新的角色
		for (int i = 0; i < roleIds.size(); i++) {
			String roleId = (String) roleIds.get(i);
			UserRole record = new UserRole();
			record.setId(IdGenUtil.uuid());
			record.setUserId(userId);
			record.setRoleId(roleId);
			userRoleService.save(record);
		}
		
		return buildBaseCudRespData("");
	}
	
}
