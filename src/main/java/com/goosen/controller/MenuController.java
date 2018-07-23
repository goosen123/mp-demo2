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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.Menu;
import com.goosen.commons.model.request.BaseDeleteReqData;
import com.goosen.commons.model.request.menu.MenuReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.menu.MenuRespData;
import com.goosen.commons.node.ZTreeNode;
import com.goosen.commons.service.MenuService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CheckUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="菜单管理",description="菜单管理")
@Controller
@RequestMapping(value="menu")
public class MenuController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	private static String PREFIX = "/system/menu/";
	
	@Resource
	private MenuService menuService;
	
	/**
     * 跳转到菜单列表列表页面
     */
	@GetMappingNoLog
    @RequestMapping(value="",method = RequestMethod.GET)
    public String index() {
        return PREFIX + "menu.html";
    }
	
	/**
     * 跳转到添加菜单页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"add"},method=RequestMethod.GET)
    public String add() {
        return PREFIX + "menu_add.html";
    }
	
	/**
     * 跳转到编辑菜单页面
     */
	@GetMappingNoLog
    @RequestMapping(value = {"edit"},method=RequestMethod.GET)
    public String edit(@ApiParam(name="id",value="id",required=true)String id,Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pcodeName", "父编号哈哈");
        return PREFIX + "menu_edit.html";
    }
	
	@ApiOperation(value="添加菜单")
	@ResponseResult
	@RequestMapping(value = {"add"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> add(@Validated @RequestBody MenuReqData reqData) throws Exception {
		
		if(reqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		
		//判断菜单是否已存在
		String code = reqData.getCode();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		Map<String,Object> menuMap = menuService.findOneByParams(params);
		if(menuMap != null && menuMap.size() > 0)
			throw new BusinessException(ResultCode.EXISTED_THE_MENU);
		
		Menu record = new Menu();
		BeanUtil.beanCopyNotNull(record, reqData);
		record.setId(IdGenUtil.uuid());
		
		//设置父菜单编号、父菜单编号集、层级
		menuSetPcode(reqData, record);
		
		//添加菜单
		record.setStatus(1);
		record.setIsopen(1);
		menuService.save(record);
		
		return buildBaseCudRespData(record.getId());
	}
	
	@ApiOperation(value="修改菜单")
	@ResponseResult
	@RequestMapping(value = {"edit"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> edit(@Validated @RequestBody MenuReqData reqData) throws Exception {
		
		String id = reqData.getId();
		CheckUtil.notEmpty("id", "id", "id不能空");
		Menu record = menuService.findById(id);
		if(record == null)
			throw new BusinessException(ResultCode.DATA_IS_WRONG);
		
		BeanUtil.beanCopyNotNull(record, reqData);
		//设置父菜单编号、父菜单编号集、层级
		menuSetPcode(reqData, record);
		menuService.update(record);
		
		return buildBaseCudRespData("");
	}
	
	@ApiOperation(value="获取菜单详情")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getDetail"},method=RequestMethod.GET)
	@ResponseBody
    public MenuRespData getDetail(@ApiParam(name="id",value="菜单id",required=true)String id) throws Exception {
		
		CheckUtil.notEmpty(id, "id", "id不能空");
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
		Map<String, Object> map = menuService.findOneByParams(params);
		
		//获取父级菜单的id
        Menu temp = new Menu();
        temp.setCode(CommonUtil.getStrValue(map, "pcode"));
        Menu pMenu = menuService.findOne(temp);

        //如果父级是顶级菜单
        if (pMenu == null) {
        	map.put("pcode", "0");
        	map.put("pcodeName", "");
        } else {
            //设置父级菜单的code为父级菜单的id
            //menu.setPcode(String.valueOf(pMenu.getId()));
        	map.put("pcode", pMenu.getId());
        	map.put("pcodeName", pMenu.getName());
        }
		
        return (MenuRespData) buildBaseModelRespData(map, new MenuRespData());
    }
	
	@ApiOperation(value="获取菜单列表")
	@GetMappingNoLog
//	@ResponseResult
	@RequestMapping(value = {"list"},method=RequestMethod.GET)
	@ResponseBody
    public List<MenuRespData> list(@ApiParam(name="name",value="菜单名称")String name,@ApiParam(name="levels",value="层级")String levels) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(name))
			params.put("name", name);
		if(!CommonUtil.isTrimNull(levels))
			params.put("levels", levels);
//		List<Object> ids = new ArrayList<Object>();
//		ids.add("c8ae71bf395b40d6bae1076374072c35");
//		ids.add("d31a0ebbf4124bbb93f17077088a7285");
//		params.put("ids", ids);
		List<Map<String, Object>> list = menuService.findByParams(params);
		
		return (List<MenuRespData>) buildBaseListRespData(list, "menu.MenuRespData");
    }
	
	@ApiOperation(value="获取菜单树列表")
	@GetMappingNoLog
//	@ResponseResult
	@RequestMapping(value = {"menuTreeListByRoleId"})//,method=RequestMethod.GET
	@ResponseBody
    public List<ZTreeNode> menuTreeListByRoleId(@ApiParam(name="roleId",value="角色id")String roleId) throws Exception {
		
		List<String> menuIds = menuService.getMenuIdsByRoleId(roleId);
		if(menuIds == null || menuIds.size() == 0){
			List<ZTreeNode> roleTreeList = menuService.menuTreeList();
            return roleTreeList;
		}else{
			List<ZTreeNode> roleTreeListByUserId = menuService.menuTreeListByMenuIds(menuIds);
            return roleTreeListByUserId;
		}
		
		//return (List<ZTreeNode>) buildBaseListRespData(list, "menu.MenuRespData");
    }
	
	/**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = menuService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }
	
	@ApiOperation(value="分页获取菜单列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getListByPage"},method=RequestMethod.GET)
	@ResponseBody
    public PageInfo<MenuRespData> getListByPage(@ApiParam(name="pageNum",value="当前页数")Integer pageNum,@ApiParam(name="pageSize",value="页大小")Integer pageSize,@ApiParam(name="name",value="菜单名称")String name) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(name))
			params.put("name", name);
		addPageParams(pageNum, pageSize, params);
		PageInfo<Map<String, Object>> pageInfo = menuService.findByParamsByPage(params);
		
        return (PageInfo<MenuRespData>) buildBasePageRespData(pageInfo, "menu.MenuRespData");
    }
	
	@ApiOperation(value="删除菜单")
	@ResponseResult
	@RequestMapping(value = {"delete"},method=RequestMethod.POST)
	@ResponseBody
	public BaseCudRespData<String> delete(@Validated @RequestBody BaseDeleteReqData reqData) {
		
		List<Object> ids = reqData.getIds();
		CheckUtil.check(ids!=null && ids.size() > 0, "ids", "ids不能空");
		
		//删除所有子菜单
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		List<Map<String, Object>> list = menuService.findByParams(params);
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> item = list.get(i);
				String code = CommonUtil.getStrValue(item, "code");
				if(!CommonUtil.isTrimNull(code)){
					Map<String, Object> paramsItem = new HashMap<String, Object>();
					paramsItem.put("pcodes", code);
					List<Map<String, Object>> listItem = menuService.findByParams(paramsItem);
					if(listItem != null && listItem.size() > 0){
						List<Object> idsItem = new ArrayList<Object>();
						for (int j = 0; j < listItem.size(); j++) {
							Map<String, Object> idsItemItem = listItem.get(j);
							String id = CommonUtil.getStrValue(idsItemItem, "id");
							if(!CommonUtil.isTrimNull(id))
								idsItem.add(id);
						}
						//删除
						if(idsItem != null && idsItem.size() > 0)
							menuService.deleteByIds(Menu.class, "id", idsItem);
					}
				}
			}
		}
		
		//删除当前菜单
		menuService.deleteByIds(Menu.class, "id", ids);
		
		return buildBaseCudRespData("");
	}
	
	/**
     * 设置父菜单编号、父菜单编号集、层级
	 * @throws Exception 
     */
    private void menuSetPcode(MenuReqData reqData,Menu menu) throws Exception {
    	//判断是否是一级菜单
    	String pId = reqData.getPcode();
    	if(CommonUtil.isTrimNull(pId) || pId.equals("0")){
    		menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
    	}else{
    		//获取父菜单
    		Menu pMenu = menuService.findById(pId);
    		if(pMenu == null)
    			throw new BusinessException(ResultCode.PMENU_NOT_EXISTED);
    		menu.setPcode(pMenu.getCode());
            Integer pLevels = pMenu.getLevels();
            
    		//如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) 
            	throw new BusinessException(ResultCode.MENU_PCODE_COINCIDENCE);
            
            menu.setLevels(pLevels + 1);
            menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
    	}
    }
	
}
