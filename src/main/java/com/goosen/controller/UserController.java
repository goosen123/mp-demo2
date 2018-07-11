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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.goosen.commons.service.UserService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="用户管理",description="用户管理")
@RestController
@RequestMapping(value="user")
public class UserController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	
	@ApiOperation(value="添加用户")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
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
		record.setId(IdGenUtil.uuid());
		record.setUserMoney(0.0);
		BeanUtil.beanCopyNotNull(record, reqData);
		userService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改用户")
	@ResponseResult
	@RequestMapping(value = {"update"},method=RequestMethod.POST)
	public BaseCudRespData<String> update(@Validated @RequestBody UserReqData reqData) {
		
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
    public UserRespData getDetail(@ApiParam(name="id",value="用户id",required=true)String id){
		
		CheckUtil.notEmpty("id", "id", "用户id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
		Map<String, Object> map = userService.findOneByParams(params);
		
        return (UserRespData) buildBaseModelRespData(map, new UserRespData());
    }
	
	@ApiOperation(value="获取用户列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getList"},method=RequestMethod.GET)
    public List<UserRespData> getList(@ApiParam(name="userName",value="用户名称")String userName) throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(userName))
			params.put("userName", userName);
		List<Map<String, Object>> list = userService.findByParams(params);
		
		return (List<UserRespData>) buildBaseListRespData(list, "user.UserRespData");
    }
	
	@ApiOperation(value="分页获取用户列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getListByPage"},method=RequestMethod.GET)
    public PageInfo<UserRespData> getListByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="userName",value="用户名称")String userName) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(userName))
			params.put("userName", userName);
		addPageParams(pageNum, pageSize, params);
		PageInfo<Map<String, Object>> pageInfo = userService.findByParamsByPage(params);
		
        return (PageInfo<UserRespData>) buildBasePageRespData(pageInfo, "user.UserRespData");
    }
	
	@ApiOperation(value="删除用户")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	public BaseCudRespData<String> delete(@ApiParam(name="ids",value="角色id集",required=true) @RequestParam("ids")List<Object> ids) {
		
		userService.deleteByIds(User.class, "id", ids);
		
		return buildBaseCudRespData("");
	}
	
}
