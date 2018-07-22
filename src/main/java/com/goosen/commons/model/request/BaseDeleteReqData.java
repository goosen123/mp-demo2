package com.goosen.commons.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

@ApiModel(value="删除基类")
public class BaseDeleteReqData extends BaseReq{

	private static final long serialVersionUID = 3536431311056183802L;
	
	@ApiModelProperty(value = "id集",required=true,example="618eb09683d946ddb747a5b8ebc300e4")
	@NotEmpty
	private List<Object> ids;

	public List<Object> getIds() {
		return ids;
	}

	public void setIds(List<Object> ids) {
		this.ids = ids;
	}
}
