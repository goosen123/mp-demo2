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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.model.response.orders.OrdersRespData;
import com.goosen.commons.service.OrdersService;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;

@Api(value="订单管理",description="订单管理")
@RestController
@RequestMapping(value="orders")
public class OrdersController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private OrdersService ordersService;
	
	@ApiOperation(value="获取订单详情")
	@GetMappingNoLog
	@ResponseResult
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
	@RequestMapping(value = {"getListByPage"},method=RequestMethod.GET)
    public PageInfo<OrdersRespData> getListByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="userId",value="用户id")String userId) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		addPageParams(pageNum, pageSize, params);
		PageInfo<Map<String, Object>> pageInfo = ordersService.findByParamsByPage(params);
		
        return (PageInfo<OrdersRespData>) buildBasePageRespData(pageInfo, "orders.OrdersRespData");
    }
	
}
