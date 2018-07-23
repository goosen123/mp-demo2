package com.goosen.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goosen.commons.dao.RoleMapper;
import com.goosen.commons.model.po.Role;
import com.goosen.commons.node.ZTreeNode;
import com.goosen.commons.service.RoleService;
import com.goosen.commons.utils.CommonUtil;

/**
 * 角色接口实现
 * @author Goosen
 * 2018年7月5日 -下午6:07:35
 */
@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> findByParams(Map<String, Object> params){
		return roleMapper.findByParams(params);
	}

    @Transactional(readOnly=true)
	@Override
	public PageInfo<Map<String, Object>> findByParamsByPage(Map<String, Object> params){
		PageHelper.startPage(CommonUtil.getIntValue(params, "pageNum"),CommonUtil.getIntValue(params, "pageSize"));
		List<Map<String, Object>> list = findByParams(params);
		return new PageInfo<Map<String, Object>>(list);
	}

    @Transactional(readOnly=true)
	@Override
	public Map<String, Object> findOneByParams(Map<String, Object> params){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = findByParams(params);
		if(list != null && list.size() > 0)
			resultMap = list.get(0);
		return resultMap;
	}
    
    @Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> findByParamsByPage2(Map<String, Object> params){
		PageHelper.startPage(CommonUtil.getIntValue(params, "pageNum"),CommonUtil.getIntValue(params, "pageSize"));
		List<Map<String, Object>> list = findByParams(params);
		return list;
	}

	@Override
	public List<String> getRoleIdsByUserId(String userId) {
		return roleMapper.getRoleIdsByUserId(userId);
	}

	@Override
	public List<ZTreeNode> roleTreeList() {
		return roleMapper.roleTreeList();
	}

	@Override
	public List<ZTreeNode> roleTreeListByRoleIds(List<String> roleIds) {
		return roleMapper.roleTreeListByRoleIds(roleIds);
	}

}
