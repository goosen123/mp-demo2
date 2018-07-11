package com.goosen.controller;

//import com.jk.common.DateEditor;
//import com.jk.common.StringEditor;
//import com.jk.util.WebUtil;
//import com.xiaoleilu.hutool.log.Log;
//import com.xiaoleilu.hutool.log.LogFactory;
//import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.product.ProductRespData;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseController {

//	protected final transient Logger log = LoggerFactory.getLogger(this.getClass());

//	protected final transient Log log = LogFactory.get(this.getClass());

	protected static final String FAILURE = "failure";
	protected static final String SUCCESS = "success";

	/**
	 * 默认页为1
     */
	protected static final Integer PAGENUM = 1;
	/**
	 * 页码大小10
     */
	protected static final Integer PAGESIZE = 10;

	/**
	 * ajax
	 * 提示常量
	 */
	protected static final String SUCCESS_LOAD_MESSAGE = "加载成功!";
	protected static final String FAILURE_LOAD_MESSAGE = "加载失败!";
	/**
	 * 保存
	 * 提示常量
	 */
	protected static final String SUCCESS_SAVE_MESSAGE = "保存成功!";
	protected static final String FAILURE_SAVE_MESSAGE = "保存失败!";
	/**
	 * 更新
	 * 提示常量
	 */
	protected static final String SUCCESS_UPDATE_MESSAGE = "更新成功!";
	protected static final String FAILURE_UPDATE_MESSAGE = "更新失败!";
	/**
	 * 充�??
	 * 提示常量
	 */
	protected static final String SUCCESS_CREDIT_MESSAGE = "充�?�成�?!";
	protected static final String FAILURE_CREDIT_MESSAGE = "充�?�失�?!";
	/**
	 * 删除
	 * 提示常量
	 */
	protected static final String SUCCESS_DELETE_MESSAGE = "删除成功!";
	protected static final String FAILURE_DELETE_MESSAGE = "删除失败!";
	protected static final String WARNING_DELETE_MESSAGE = "已经删除!";
	/**
	 * 禁用启用
	 */
	protected static final String SUCCESS_ENABLE_TRUE = "启用成功!";
	protected static final String SUCCESS_ENABLE_FALSE = "禁用成功!";
	
	protected static final String BASERESPPACKAGE = "com.goosen.commons.model.response.";
	
	protected static BaseCudRespData<String> baseCudRespData;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//		binder.registerCustomEditor(Date.class, new DateEditor(true));
//		binder.registerCustomEditor(String.class, "password", new StringEditor(true));
//	}

//	@ExceptionHandler
//	public void exceptionHandler(Exception exception,
//										 HttpServletRequest request,
//										 HttpServletResponse response) throws Exception {
//
//		if (exception instanceof UnauthorizedException) {
//			if(WebUtil.isAjaxRequest(request)){
//				response.setStatus(HttpServletResponse.SC_FORBIDDEN);//无权限异�?  主要用于ajax请求返回
//				response.setHeader("No-Permission", "{\"code\":403,\"msg\":'No Permission'}");
//				response.setContentType("text/html;charset=utf-8");
//			}else {
//				response.sendRedirect("/admin/403");
//			}
//		}
//	}
	
	public static String getSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Object obj = request.getSession().getAttribute(key);
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static void setSession(String key, Object value) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(key, value);
	}

	public static void removeSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute(key);
	}
	
	public static void addPageParams(Integer pageNum,Integer pageSize,Map<String, Object> map) {
		if(!CommonUtil.isVaileNum(pageNum))
			pageNum = PAGENUM;
		if(!CommonUtil.isVaileNum(pageSize))
			pageSize = PAGESIZE;
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
	}
	
	public static BaseCudRespData<String> buildBaseCudRespData(String id) {
		if(baseCudRespData == null)
			baseCudRespData = new BaseCudRespData<String>();
		baseCudRespData.setId(id);
		return baseCudRespData;
	}
	
	public static Object buildBaseModelRespData(Map<String, Object> map,Object model) {
		if(map != null && map.size() > 0)
			BeanUtil.mapToBean(map, model);
		return model;
	}
	
	public static Object buildBaseListRespData(List<Map<String, Object>> list,String respPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		List<Object> resultList = new ArrayList<Object>();
		if(list == null || list.size() == 0)
			return resultList;
		Class c1 = Class.forName(BASERESPPACKAGE+respPackage);
		for (int i = 0; i < list.size(); i++) {
			Object model = c1.newInstance();
			Map<String, Object> map = list.get(i);
			if(map != null && map.size() > 0)
				BeanUtil.mapToBean(map, model);
			resultList.add(model);
		}
		return resultList;
	}
	
	public static Object buildBasePageRespData(PageInfo<Map<String, Object>> pageInfo,String respPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		PageInfo<Object> resultPage = new PageInfo<Object>();
		List<Object> resultList = new ArrayList<Object>();
		if(pageInfo == null){
			resultPage.setList(resultList);
			return resultPage;
		}
		List<Map<String, Object>> list = pageInfo.getList();
		if(list != null && list.size() > 0){
			Class c1 = Class.forName(BASERESPPACKAGE+respPackage);
			for (int i = 0; i < list.size(); i++) {
				Object model = c1.newInstance();
				Map<String, Object> map = list.get(i);
				if(map != null && map.size() > 0)
					BeanUtil.mapToBean(map, model);
				resultList.add(model);
			}
		}
		BeanUtil.beanCopyUnFieldNotNull(resultPage, pageInfo, "list");
		resultPage.setList(resultList);
		return resultPage;
	}
	
}
