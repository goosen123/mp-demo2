package com.goosen.commons.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.model.po.Menu;
import com.goosen.commons.node.MenuNode;
import com.goosen.commons.node.ZTreeNode;

/**
 * 菜单接口
 * @author Goosen
 * 2018年7月4日 -下午1:50:58
 */
public interface MenuService extends BaseService<Menu>{
    
	List<Map<String,Object>> findByParams(Map<String,Object> params);
    
    PageInfo<Map<String,Object>> findByParamsByPage(Map<String,Object> params);
    
    Map<String,Object> findOneByParams(Map<String,Object> params);
    
    
    List<MenuNode> getMenusByRoleIds(List<String> roleIds);
    
    
    List<String> getMenuIdsByRoleId(String roleId);
    List<ZTreeNode> menuTreeList();
    List<ZTreeNode> menuTreeListByMenuIds(List<String> menuIds);
    
}
