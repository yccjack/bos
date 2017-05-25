package ycc.mb.bao.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import ycc.mb.bao.utils.PageBean;

/**
 * 抽取持久层通用方法
 * 
 * @author zhaoqx
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 通用保存
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 通用删除
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 通用修改
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> findAll();

	// 提供通用修改方法
	public void executeUpdate(String queryName, Object... objects);

	/**
	 * 通用分页查询
	 * 
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	/**
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
