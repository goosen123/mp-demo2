package com.goosen.commons.model.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.goosen.commons.model.request.BaseReq;

@ApiModel(value="角色")
public class RoleMenuReqData extends BaseReq{

	private static final long serialVersionUID = 3536431311056183802L;
	
    @ApiModelProperty(value = "角色id",required=true,example="618eb09683d946ddb747a5b8ebc300e4")
	@NotEmpty
    private String roleId;
    @ApiModelProperty(value = "菜单id集",required=true,example="618eb09683d946ddb747a5b8ebc300e4")
	@NotEmpty
	private List<Object> menuIds;
	
    public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<Object> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Object> menuIds) {
		this.menuIds = menuIds;
	}
	
}
