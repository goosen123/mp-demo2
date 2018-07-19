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
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.User;
import com.goosen.commons.model.request.user.UserReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.user.UserRespData;
import com.goosen.commons.page.PageInfoBT;
import com.goosen.commons.page.PageReq;
import com.goosen.commons.service.UserService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="用户管理",description="用户管理")
@Controller
@RequestMapping(value="mgr")
public class UserController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	private static String PREFIX = "/system/user/";
	
	@Resource
	private UserService userService;
	
	
	/**
     * 跳转到查看管理员列表的页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {""},method=RequestMethod.GET)
    public String index() {
        return PREFIX + "user.html";
    }
	
	/**
     * 跳转到添加管理员页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"add"},method=RequestMethod.GET)
    public String add() {
        return PREFIX + "user_add.html";
    }
	
	/**
     * 跳转到编辑管理员页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"edit"},method=RequestMethod.GET)
    public String edit(@ApiParam(name="id",value="用户id",required=true)String id,Model model) {
		model.addAttribute("id", id);
        return PREFIX + "user_edit.html";
    }
	
	/**
     * 跳转到编辑管理员页面
     */
//    @Permission
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable String userId, Model model) {
    	CheckUtil.notEmpty("userId", "userId", "用户id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(userId))
			params.put("id", userId);
		Map<String, Object> map = userService.findOneByParams(params);
		User user = new User();
    	if(map != null && map.size() > 0)
    		BeanUtil.mapToBean(map, user);
        return PREFIX + "user_edit.html";
    }
	
	
	@ApiOperation(value="添加用户")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> add(@Validated @RequestBody UserReqData reqData){
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		
		//判断账号是否已存在
		String account = reqData.getAccount();
		Integer userType = reqData.getUserType();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", account);
		params.put("userType", userType);
		Map<String,Object> userMap = userService.findOneByParams(params);
		if(userMap != null && userMap.size() > 0)
			throw new BusinessException(ResultCode.USER_HAS_EXISTED);
		
		User record = new User();
		BeanUtil.beanCopyNotNull(record, reqData);
		record.setId(IdGenUtil.uuid());
		record.setUserMoney(0.0);
		userService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改用户")
	@ResponseResult
	@RequestMapping(value = {"edit"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> edit(@Validated @RequestBody UserReqData reqData) {
		
		String id = reqData.getId();
		CheckUtil.notEmpty("id", "id", "用户id不能空");
		User record = userService.findById(id);
		if(record == null)
			throw new BusinessException(ResultCode.DATA_IS_WRONG);
		//账号不给修改
		reqData.setAccount(null);
		BeanUtil.beanCopyNotNull(record, reqData);
		userService.update(record);
		
		return buildBaseCudRespData("");
	}
	
	@ApiOperation(value="获取用户详情")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getDetail"},method=RequestMethod.GET)
	@ResponseBody
    public UserRespData getDetail(@ApiParam(name="id",value="用户id",required=true)String id){
		
		CheckUtil.notEmpty(id, "id", "用户id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
		Map<String, Object> map = userService.findOneByParams(params);
		
        return (UserRespData) buildBaseModelRespData(map, new UserRespData());
    }
	
	@ApiOperation(value="获取用户列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"list"},method=RequestMethod.GET)//
	@ResponseBody
    public List<UserRespData> list(@ApiParam(name="userName",value="用户名称")String userName) throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(userName))
			params.put("userName", userName);
		List<Map<String, Object>> list = userService.findByParams(params);
		
		return (List<UserRespData>) buildBaseListRespData(list, "user.UserRespData");
    }
	
//	@ApiOperation(value="分页获取用户列表")
//	@GetMappingNoLog
//	@ResponseResult
//	@RequestMapping(value = {"listByPage"},method=RequestMethod.GET)
//	@ResponseBody
//    public PageInfo<UserRespData> listByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="userName",value="用户名称")String userName) throws Exception {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		if(!CommonUtil.isTrimNull(userName))
//			params.put("userName", userName);
//		addPageParams(pageNum, pageSize, params);
//		PageInfo<Map<String, Object>> pageInfo = userService.findByParamsByPage(params);
//		
//        return (PageInfo<UserRespData>) buildBasePageRespData(pageInfo, "user.UserRespData");
//    }
	
//	@ApiOperation(value="分页获取用户列表")
//	@GetMappingNoLog
//	//@ResponseResult
//	@RequestMapping(value = {"listByPage"},method=RequestMethod.GET)
//	@ResponseBody
//    public Object listByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="userName",value="用户名称")String userName) throws Exception {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		if(!CommonUtil.isTrimNull(userName))
//			params.put("userName", userName);
//		//addPageParams(pageNum, pageSize, params);
//		PageReq paramsPage = defaultPage();
//		params.put("pageNum", paramsPage.getOffset());
//		params.put("pageSize", paramsPage.getLimit());
//		List<Map<String, Object>> result = userService.findByParamsByPage2(params);
//		
//        return packForBT(result);
//    }
	
	@ApiOperation(value="分页获取用户列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"listByPage"},method=RequestMethod.GET)
	@ResponseBody
    public PageInfoBT<UserRespData> listByPage(@ApiParam(name="searchKey",value="账号/姓名/手机号")String searchKey,
    		@ApiParam(name="beginTime",value="注册开始日期") String beginTime,
    		@ApiParam(name="endTime",value="注册结束日期") String endTime) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("searchKey", searchKey);
	    params.put("beginTime", beginTime);
	    params.put("endTime", endTime);
		defaultPage(params);
		List<Map<String, Object>> list = userService.findByParamsByPage2(params);
		
        return (PageInfoBT<UserRespData>) buildBasePageRespData2(list, "user.UserRespData");
    }
	
	@ApiOperation(value="删除用户")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> delete(@ApiParam(name="ids",value="id集",required=true) List<Object> ids) {//@RequestParam("ids")
		
		userService.deleteByIds(User.class, "id", ids);
		
		return buildBaseCudRespData("");
	}
	
}
