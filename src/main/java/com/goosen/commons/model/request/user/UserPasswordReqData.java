package com.goosen.commons.model.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.NotEmpty;

import com.goosen.commons.model.request.BaseReq;

@ApiModel(value="用户密码")
public class UserPasswordReqData extends BaseReq{

	private static final long serialVersionUID = 3536431311056183802L;
	
	
	@ApiModelProperty(value = "原密码",required=true,example="123456")
	@NotEmpty
    private String oldPwd;
	@ApiModelProperty(value = "新密码",required=true,example="123456")
	@NotEmpty
    private String newPwd;
    @ApiModelProperty(value = "新密码验证",required=true,example="123456")
	@NotEmpty
    private String rePwd;
    
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getRePwd() {
		return rePwd;
	}
	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}
	
}
