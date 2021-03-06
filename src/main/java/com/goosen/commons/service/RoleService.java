package com.goosen.commons.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.model.po.Role;
import com.goosen.commons.node.ZTreeNode;

/**
 * 角色接口
 * @author Goosen
 * 2018年7月5日 -下午6:06:47
 */
public interface RoleService extends BaseService<Role>{
    
	List<Map<String,Object>> findByParams(Map<String,Object> params);
    
    PageInfo<Map<String,Object>> findByParamsByPage(Map<String,Object> params);
    
    Map<String,Object> findOneByParams(Map<String,Object> params);
    
    List<Map<String,Object>> findByParamsByPage2(Map<String,Object> params);
    
    
    List<String> getRoleIdsByUserId(String userId);
    List<ZTreeNode> roleTreeList();
    List<ZTreeNode> roleTreeListByRoleIds(List<String> roleIds);
    
}
