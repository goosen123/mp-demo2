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
import com.goosen.commons.model.po.ProductAttr;
import com.goosen.commons.model.request.productattr.ProductAttrReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.product.ProductRespData;
import com.goosen.commons.model.response.productattr.ProductAttrRespData;
import com.goosen.commons.service.ProductAttrService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="商品属性管理",description="商品属性管理")
@RestController
@RequestMapping(value="productAttr")
public class ProductAttrController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ProductAttrService productAttrService;
	
	@ApiOperation(value="添加商品属性")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
	public BaseCudRespData<String> add(@Validated @RequestBody ProductAttrReqData reqData) {
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		ProductAttr record = new ProductAttr();
		record.setId(IdGenUtil.uuid());
		BeanUtil.beanCopyNotNull(record, reqData);
		productAttrService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改商品属性")
	@ResponseResult
	@RequestMapping(value = {"update"},method=RequestMethod.POST)
	public BaseCudRespData<String> update(@Validated @RequestBody ProductAttrReqData reqData) {
		
		String id = reqData.getId();
		CheckUtil.notEmpty("id", "id", "商品属性id不能空");
		ProductAttr record = productAttrService.findById(id);
		if(record == null)
			throw new BusinessException(ResultCode.DATA_IS_WRONG);
		BeanUtil.beanCopyNotNull(record, reqData);
		productAttrService.update(record);
		
		return buildBaseCudRespData("");
	}
	
	@ApiOperation(value="获取商品属性详情")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getDetail"},method=RequestMethod.GET)
    public ProductAttrRespData getDetail(@ApiParam(name="id",value="商品属性id",required=true)String id) throws Exception {
		
		CheckUtil.notEmpty("id", "id", "商品属性id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
		Map<String, Object> map = productAttrService.findOneByParams(params);
		
        return (ProductAttrRespData) buildBaseModelRespData(map, new ProductAttrRespData());
    }
	
	@ApiOperation(value="获取商品属性列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getList"},method=RequestMethod.GET)
    public List<ProductAttrRespData> getList(@ApiParam(name="attrName",value="商品属性名称")String attrName) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(attrName))
			params.put("attrName", attrName);
		List<Map<String, Object>> list = productAttrService.findByParams(params);
		
		return (List<ProductAttrRespData>) buildBaseListRespData(list, "productattr.ProductAttrRespData");
    }
	
	@ApiOperation(value="分页获取商品属性列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getListByPage"},method=RequestMethod.GET)
    public PageInfo<ProductAttrRespData> getListByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="attrName",value="商品属性名称")String attrName) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(attrName))
			params.put("attrName", attrName);
		addPageParams(pageNum, pageSize, params);
		PageInfo<Map<String, Object>> pageInfo = productAttrService.findByParamsByPage(params);
		
        return (PageInfo<ProductAttrRespData>) buildBasePageRespData(pageInfo, "productattr.ProductAttrRespData");
    }
	
	@ApiOperation(value="删除商品属性")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	public BaseCudRespData<String> delete(@ApiParam(name="ids",value="商品属性id集",required=true) @RequestParam("ids")List<Object> ids) {
		
		productAttrService.deleteByIds(ProductAttr.class, "id", ids);
		
		return buildBaseCudRespData("");
	}
	
}
