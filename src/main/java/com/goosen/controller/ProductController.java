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

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.Product;
import com.goosen.commons.model.po.ProductAttr;
import com.goosen.commons.model.request.BaseDeleteReqData;
import com.goosen.commons.model.request.product.ProductReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.product.ProductRespData;
import com.goosen.commons.page.PageInfoBT;
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
	private static String PREFIX = "/shop/product/";
	
	@Resource
	private ProductService productService;
	@Resource
	private ProductAttrService productAttrService;
	
	/**
     * 跳转到查看商品列表的页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {""},method=RequestMethod.GET)
    public String index() {
        return PREFIX + "product.html";
    }
	
	/**
     * 跳转到编辑商品页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"edit"},method=RequestMethod.GET)
    public String edit(@ApiParam(name="id",value="商品id",required=true)String id,Model model) {
		model.addAttribute("id", id);
        return PREFIX + "product_edit.html";
    }
	
	/**
     * 跳转到添加商品页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"add"},method=RequestMethod.GET)
    public String add() {
        return PREFIX + "product_add.html";
    }
	
	@ApiOperation(value="添加商品")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> add(@Validated @RequestBody ProductReqData reqData) {
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		Product record = new Product();
		BeanUtil.beanCopyNotNull(record, reqData);
		record.setId(IdGenUtil.uuid());
		record.setSalesVolume(0);
		productService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改商品")
	@ResponseResult
	@RequestMapping(value = {"edit"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> edit(@Validated @RequestBody ProductReqData reqData) {
		
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
	@RequestMapping(value = {"listByPage"},method=RequestMethod.GET)
	@ResponseBody
    public PageInfoBT<ProductRespData> listByPage(@ApiParam(name="searchKey",value="标题/编号")String searchKey,
    		@ApiParam(name="beginTime",value="注册开始日期") String beginTime,
    		@ApiParam(name="endTime",value="注册结束日期") String endTime) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("searchKey", searchKey);
	    params.put("beginTime", beginTime);
	    params.put("endTime", endTime);
		defaultPage(params);
		List<Map<String, Object>> list = productService.findByParamsByPage2(params);
		
        return (PageInfoBT<ProductRespData>) buildBasePageRespData2(list, "product.ProductRespData");
    }
	
	@ApiOperation(value="删除商品")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> delete(@Validated @RequestBody BaseDeleteReqData reqData) {
		
		List<Object> ids = reqData.getIds();
		CheckUtil.check(ids!=null && ids.size() > 0, "ids", "ids不能空");
		productService.deleteByIds(Product.class, "id", ids);
		//关联删除商品属性
		productAttrService.deleteByIds(ProductAttr.class, "productId", ids);
		
		return buildBaseCudRespData("");
	}
	
}
