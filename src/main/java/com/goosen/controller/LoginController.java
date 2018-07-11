package com.goosen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.UserRole;
import com.goosen.commons.model.request.login.LoginReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.login.LoginRespData;
import com.goosen.commons.service.UserRoleService;
import com.goosen.commons.service.UserService;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;

@Api(value="登录管理",description="登录管理")
@Controller
public class LoginController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		return "/login.html";
    }
	
	@ApiOperation(value="登录")
	@ResponseResult
	@RequestMapping(value = {"login"},method=RequestMethod.POST)
	@ResponseBody
	public LoginRespData login(@Validated @RequestBody LoginReqData reqData){
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		
		//判断账号是否已存在
		String account = reqData.getAccount();
		//管理员
		Integer userType = 1;
		String password = reqData.getPassword();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", account);
		params.put("userType", userType);
		Map<String,Object> userMap = userService.findOneByParams(params);
		if(userMap == null || userMap.size() == 0)
			throw new BusinessException(ResultCode.USER_NOT_EXIST);
		//检查密码是否正确
		String passwordFan = CommonUtil.getStrValue(userMap, "password");
		if(!passwordFan.equals(password))
			throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
		String userId = CommonUtil.getStrValue(userMap, "id");
		
		//判断是否禁用
		//检验验证码
		
		LoginRespData loginRespData = (LoginRespData) buildBaseModelRespData(userMap, new LoginRespData());
		//拿角色id集
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		List<UserRole> list = userRoleService.findListByWhere(userRole);
		if(list != null && list.size() > 0){
			List<String> roleIds = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				UserRole userRoleNew = list.get(i);
				if(userRoleNew != null){
					String roleid = userRoleNew.getRoleId();
					if(!CommonUtil.isTrimNull(roleid))
						roleIds.add(roleid);
				}
			}
			loginRespData.setRoleIds(roleIds);
		}
		
		return loginRespData;
	}
	
	@ApiOperation(value="退出登录")
	@ResponseResult
	@RequestMapping(value = {"logout"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> logout(@ApiParam(name="userId",value="用户id",required=true)String userId) {
		
		CheckUtil.notEmpty("userId", "userId", "userId不能空");
		
		return buildBaseCudRespData("");
	}
	
}
