package com.goosen.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goosen.commons.dao.MenuMapper;
import com.goosen.commons.model.po.Menu;
import com.goosen.commons.node.MenuNode;
import com.goosen.commons.node.ZTreeNode;
import com.goosen.commons.service.MenuService;
import com.goosen.commons.utils.CommonUtil;

/**
 * 接口接口实现
 * @author Goosen
 * 2018年7月4日 -下午1:52:06
 */
@Transactional
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> findByParams(Map<String, Object> params){
		return menuMapper.findByParams(params);
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

	@Override
	public List<MenuNode> getMenusByRoleIds(List<String> roleIds) {
		return menuMapper.getMenusByRoleIds(roleIds);
	}

	@Override
	public List<String> getMenuIdsByRoleId(String roleId) {
		return menuMapper.getMenuIdsByRoleId(roleId);
	}

	@Override
	public List<ZTreeNode> menuTreeList() {
		return menuMapper.menuTreeList();
	}

	@Override
	public List<ZTreeNode> menuTreeListByMenuIds(List<String> menuIds) {
		return menuMapper.menuTreeListByMenuIds(menuIds);
	}

}
