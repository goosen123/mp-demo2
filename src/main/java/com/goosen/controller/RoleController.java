package com.goosen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.constants.factory.IConstantFactory;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.Role;
import com.goosen.commons.model.request.BaseDeleteReqData;
import com.goosen.commons.model.request.role.RoleReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.role.RoleRespData;
import com.goosen.commons.node.ZTreeNode;
import com.goosen.commons.page.PageInfoBT;
import com.goosen.commons.service.RoleService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="角色管理",description="角色管理")
@Controller
@RequestMapping(value="role")
public class RoleController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	private static String PREFIX = "/system/role/";
	
	@Resource
	private RoleService roleService;
	@Resource
	private IConstantFactory iConstantFactory;
	
	/**
     * 跳转到角色列表页面
     */
	@GetMappingNoLog
    @RequestMapping(value="",method=RequestMethod.GET)
    public String index() {
        return PREFIX + "role.html";
    }
	
	/**
     * 跳转到添加角色页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"add"},method=RequestMethod.GET)
    public String add() {
		return PREFIX + "role_add.html";
    }
	
	/**
     * 跳转到编辑角色页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"edit"},method=RequestMethod.GET)
    public String edit(@ApiParam(name="id",value="角色id",required=true)String id,Model model) {
		model.addAttribute("id", id);
        return PREFIX + "role_edit.html";
    }
	
	/**
     * 跳转到权限配置页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"assignPerm"},method=RequestMethod.GET)
    public String assignPerm(@ApiParam(name="roleId",value="角色id",required=true)String roleId,Model model) {
		model.addAttribute("roleId", roleId);
		model.addAttribute("roleName", iConstantFactory.getSingleRoleName(roleId));
        return PREFIX + "role_assign.html";
    }
	
	@ApiOperation(value="添加角色")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> add(@Validated @RequestBody RoleReqData reqData){
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		
		Role record = new Role();
		BeanUtil.beanCopyNotNull(record, reqData);
		record.setId(IdGenUtil.uuid());
		roleService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改角色")
	@ResponseResult
	@RequestMapping(value = {"edit"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> edit(@Validated @RequestBody RoleReqData reqData) {
		
		String id = reqData.getId();
		CheckUtil.notEmpty("id", "id", "id不能空");
		if(id.equals("20a00e3f72a54f91a24cb903ac84083f"))
			throw new BusinessException(ResultCode.SUPER_ADMIN_UPDATE);
		Role record = roleService.findById(id);
		if(record == null)
			throw new BusinessException(ResultCode.DATA_IS_WRONG);
		
		BeanUtil.beanCopyNotNull(record, reqData);
		roleService.update(record);
		
		return buildBaseCudRespData("");
	}
	
	@ApiOperation(value="获取角色详情")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getDetail"},method=RequestMethod.GET)
	@ResponseBody
    public RoleRespData getDetail(@ApiParam(name="id",value="角色id",required=true)String id){
		
		CheckUtil.notEmpty("id", "id", "id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
		Map<String, Object> map = roleService.findOneByParams(params);
		
        return (RoleRespData) buildBaseModelRespData(map, new RoleRespData());
    }
	
	@ApiOperation(value="获取角色列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getList"},method=RequestMethod.GET)
	@ResponseBody
    public List<RoleRespData> getList(@ApiParam(name="name",value="角色名称")String name) throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(name))
			params.put("name", name);
		List<Map<String, Object>> list = roleService.findByParams(params);
		
		return (List<RoleRespData>) buildBaseListRespData(list, "role.RoleRespData");
    }
	
	@ApiOperation(value="获取角色树列表")
	@GetMappingNoLog
//	@ResponseResult
	@RequestMapping(value = {"roleTreeListByUserId"})//,method=RequestMethod.GET
	@ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@ApiParam(name="userId",value="用户id")String userId) throws Exception{
		
		List<String> roleIds = roleService.getRoleIdsByUserId(userId);
		if(roleIds == null || roleIds.size() == 0){
			List<ZTreeNode> roleTreeList = roleService.roleTreeList();
            return roleTreeList;
		}else{
			List<ZTreeNode> roleTreeListByUserId = roleService.roleTreeListByRoleIds(roleIds);
            return roleTreeListByUserId;
		}
		
//		return (List<RoleRespData>) buildBaseListRespData(list, "role.RoleRespData");
    }
	
//	@ApiOperation(value="分页获取角色列表")
//	@GetMappingNoLog
//	@ResponseResult
//	@RequestMapping(value = {"getListByPage"},method=RequestMethod.GET)
//	@ResponseBody
//    public PageInfo<RoleRespData> getListByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="name",value="角色名称")String name) throws Exception {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		if(!CommonUtil.isTrimNull(name))
//			params.put("name", name);
//		addPageParams(pageNum, pageSize, params);
//		PageInfo<Map<String, Object>> pageInfo = roleService.findByParamsByPage(params);
//		
//        return (PageInfo<RoleRespData>) buildBasePageRespData(pageInfo, "role.RoleRespData");
//    }
	
	@ApiOperation(value="分页获取角色列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"listByPage"},method=RequestMethod.GET)
	@ResponseBody
    public PageInfoBT<RoleRespData> listByPage(@ApiParam(name="roleName",value="角色名称")String roleName) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(roleName))
			params.put("name", roleName);
		defaultPage(params);
		List<Map<String, Object>> list = roleService.findByParamsByPage2(params);
		
        return (PageInfoBT<RoleRespData>) buildBasePageRespData2(list, "role.RoleRespData");
    }
	
	@ApiOperation(value="删除角色")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> delete(@Validated @RequestBody BaseDeleteReqData reqData) {
		
		List<Object> ids = reqData.getIds();
		CheckUtil.check(ids!=null && ids.size() > 0, "ids", "ids不能空");
		roleService.deleteByIds(Role.class, "id", ids);
		
		return buildBaseCudRespData("");
	}
	
}
