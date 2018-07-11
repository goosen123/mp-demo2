package com.goosen.commons.service;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.model.BasePO;

import java.util.List;

/**
 * 接口基类
 * @author Goosen
 * 2018年6月27日 -下午4:00:04
 */
public interface BaseService<T extends BasePO> {
    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<T> findAll();

    /**
     * 根据条件查询一条数据，如果有多条数据会抛出异常
     *
     * @param record
     * @return
     */
    public T findOne(T record);

    /**
     * 根据条件查询数据列表
     *
     * @param record
     * @return
     */
    public List<T> findListByWhere(T record);
    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    public PageInfo<T> findPageListByWhere(Integer pageNum, Integer pageSize, T record);

    /**
     * 新增数据，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer save(T record);

    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer saveSelective(T record);

    /**
     * 批量保存，返回保存的条数
     * @param list
     * @return
     */
    public int saveList(List<T> list);

    /**
     * 修改数据，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer update(T record);

    /**
     * 修改数据，使用不为null的字段，返回成功的条数
     *
     * @param record
     * @return
     */
    public Integer updateSelective(T record);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    public Integer deleteById(String id);

    /**
     * 批量删除
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values);

    /**
     * 根据条件做删除
     *
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record);

    /**
     * 自定义查询
     * @param example
     * @return
     */
    public List<T> selectByExample(Object example);
}
