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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.model.response.orders.OrdersRespData;
import com.goosen.commons.model.response.user.UserRespData;
import com.goosen.commons.page.PageInfoBT;
import com.goosen.commons.service.OrdersService;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;

@Api(value="订单管理",description="订单管理")
@Controller
@RequestMapping(value="orders")
public class OrdersController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	private static String PREFIX = "/shop/orders/";
	
	@Resource
	private OrdersService ordersService;
	
	/**
     * 跳转到查看订单列表的页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {""},method=RequestMethod.GET)
    public String index() {
        return PREFIX + "orders.html";
    }
	
	/**
     * 跳转到查看订单页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"view"},method=RequestMethod.GET)
    public String view(@ApiParam(name="id",value="订单id",required=true)String id,Model model) {
		model.addAttribute("id", id);
        return PREFIX + "orders_view.html";
    }
	
	@ApiOperation(value="获取订单详情")
	@GetMappingNoLog
	@ResponseResult
	@ResponseBody
	@RequestMapping(value = {"getDetail"},method=RequestMethod.GET)
    public OrdersRespData getDetail(@ApiParam(name="id",value="订单id",required=true)String id){
		
		CheckUtil.notEmpty(id, "id", "订单id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
		Map<String, Object> map = ordersService.findOneByParams(params);
		
        return (OrdersRespData) buildBaseModelRespData(map, new OrdersRespData());
    }
	
	@ApiOperation(value="获取订单列表")
	@GetMappingNoLog
	@ResponseResult
	@ResponseBody
	@RequestMapping(value = {"getList"},method=RequestMethod.GET)
    public List<OrdersRespData> getList(@ApiParam(name="userId",value="用户id")String userId) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<Map<String, Object>> list = ordersService.findByParams(params);
		
        return (List<OrdersRespData>) buildBaseListRespData(list, "orders.OrdersRespData");
    }
	
	@ApiOperation(value="分页获取订单列表")
	@GetMappingNoLog
	@ResponseResult
	@ResponseBody
	@RequestMapping(value = {"listByPage"},method=RequestMethod.GET)
    public PageInfoBT<OrdersRespData> listByPage(@ApiParam(name="searchKey",value="用户名称/订单号")String searchKey,
    		@ApiParam(name="beginTime",value="下单开始日期") String beginTime,
    		@ApiParam(name="endTime",value="下单结束日期") String endTime) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("searchKey", searchKey);
	    params.put("beginTime", beginTime);
	    params.put("endTime", endTime);
		defaultPage(params);
		List<Map<String, Object>> list = ordersService.findByParamsByPage2(params);
		
        return (PageInfoBT<OrdersRespData>) buildBasePageRespData2(list, "orders.OrdersRespData");
    }
	
}
