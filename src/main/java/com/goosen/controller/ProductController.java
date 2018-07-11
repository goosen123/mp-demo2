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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.Product;
import com.goosen.commons.model.po.ProductAttr;
import com.goosen.commons.model.request.product.ProductReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.product.ProductRespData;
import com.goosen.commons.service.ProductAttrService;
import com.goosen.commons.service.ProductService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="商品管理",description="商品管理")
@Controller
@RequestMapping(value="product")
public class ProductController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ProductService productService;
	@Resource
	private ProductAttrService productAttrService;
	
	@ApiOperation(value="添加商品")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> add(@Validated @RequestBody ProductReqData reqData) {
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		Product record = new Product();
		record.setId(IdGenUtil.uuid());
		record.setSalesVolume(0);
		BeanUtil.beanCopyNotNull(record, reqData);
		productService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改商品")
	@ResponseResult
	@RequestMapping(value = {"update"},method=RequestMethod.PUT)
	@ResponseBody
	public BaseCudRespData<String> update(@Validated @RequestBody ProductReqData reqData) {
		
		String id = reqData.getId();
		CheckUtil.notEmpty(id,"id", "商品id不能空");
		Product record = productService.findById(id);
		if(record == null)
			throw new BusinessException(ResultCode.DATA_IS_WRONG);
		BeanUtil.beanCopyNotNull(record, reqData);
		productService.update(record);
		
		return buildBaseCudRespData("");
	}
	
	@ApiOperation(value="获取商品详情")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getDetail"},method=RequestMethod.GET)
	@ResponseBody
    public ProductRespData getDetail(@ApiParam(name="id",value="商品id",required=true)String id){
		
		CheckUtil.notEmpty(id, "id", "商品id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
//		CheckUtil.notNull(null, "user", "参数有误");
//		Assert.notNull(null, "str is not null.");
//		int i = 1/0;
//		String str = null;
//		if(str == null)
//			throw new BusinessException(ResultCode.RESULE_DATA_NONE);
		Map<String, Object> map = productService.findOneByParams(params);
		
        return (ProductRespData) buildBaseModelRespData(map, new ProductRespData());
    }
	
	@ApiOperation(value="获取商品列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getList"},method=RequestMethod.GET)
	@ResponseBody
    public List<ProductRespData> getList(@ApiParam(name="productTitle",value="商品标题")String productTitle) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(productTitle))
			params.put("productTitle", productTitle);
		List<Map<String, Object>> list = productService.findByParams(params);
		
        return (List<ProductRespData>) buildBaseListRespData(list, "product.ProductRespData");
    }
	
	@ApiOperation(value="分页获取商品列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getListByPage"},method=RequestMethod.GET)
	@ResponseBody
    public PageInfo<ProductRespData> getListByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="productTitle",value="商品标题")String productTitle) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(productTitle))
			params.put("productTitle", productTitle);
		addPageParams(pageNum, pageSize, params);
		PageInfo<Map<String, Object>> pageInfo = productService.findByParamsByPage(params);
		
        return (PageInfo<ProductRespData>) buildBasePageRespData(pageInfo, "product.ProductRespData");
    }
	
	@ApiOperation(value="删除商品")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> delete(@ApiParam(name="ids",value="商品id集",required=true) @RequestParam("ids")List<Object> ids) {
		
		if(ids != null && ids.size() > 0){
			productService.deleteByIds(Product.class, "id", ids);
			//关联删除商品属性
			productAttrService.deleteByIds(ProductAttr.class, "productId", ids);
		}
		
		return buildBaseCudRespData("");
	}
	
}
