package com.goosen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.model.po.RoleMenu;
import com.goosen.commons.model.request.BaseDeleteReqData;
import com.goosen.commons.model.request.role.RoleMenuReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.service.RoleMenuService;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="角色菜单管理",description="角色菜单管理")
@RestController
@RequestMapping(value="roleMenu")
public class RoleMenuController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@ApiOperation(value="分配权限")
	@ResponseResult
	@RequestMapping(value = {"assignPerm"},method=RequestMethod.POST)
	public BaseCudRespData<String> assignPerm(@Validated @RequestBody RoleMenuReqData reqData) throws Exception {//List<Object>
		
		String roleId = reqData.getRoleId();
		List<Object> menuIds = reqData.getMenuIds();
		CheckUtil.notEmpty(roleId, "roleId", "roleId不能空");
		CheckUtil.check(menuIds!=null && menuIds.size() > 0, "menuIds", "menuIds不能空");//
		
		//删除该角色所有的权限
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenuService.deleteByWhere(roleMenu);
		
		//添加新的权限
		for (int i = 0; i < menuIds.size(); i++) {
			String menuId = (String) menuIds.get(i);
			RoleMenu record = new RoleMenu();
			record.setId(IdGenUtil.uuid());
			record.setRoleId(roleId);
			record.setMenuId(menuId);
			roleMenuService.save(record);
		}
//		String[] menuIdsStr = menuIds.split(",");
//		for (int i = 0; i < menuIdsStr.length; i++) {
//			String menuId =  menuIdsStr[i];
//			RoleMenu record = new RoleMenu();
//			record.setId(IdGenUtil.uuid());
//			record.setRoleId(roleId);
//			record.setMenuId(menuId);
//			roleMenuService.save(record);
//		}
		
		return buildBaseCudRespData("");
	}
	
}
