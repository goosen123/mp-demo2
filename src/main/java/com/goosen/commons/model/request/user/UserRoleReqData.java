package com.goosen.commons.model.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.goosen.commons.model.request.BaseReq;

@ApiModel(value="用户角色")
public class UserRoleReqData extends BaseReq{

	private static final long serialVersionUID = 3536431311056183802L;
	
    @ApiModelProperty(value = "用户id",required=true,example="618eb09683d946ddb747a5b8ebc300e4")
	@NotEmpty
    private String userId;
    @ApiModelProperty(value = "角色id集",required=true,example="618eb09683d946ddb747a5b8ebc300e4")
	@NotEmpty
	private List<Object> roleIds;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Object> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Object> roleIds) {
		this.roleIds = roleIds;
	}
	
}
