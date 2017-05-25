package ycc.mb.bao.service;

import java.util.List;

import ycc.mb.bao.domain.Staff;
import ycc.mb.bao.utils.PageBean;

public interface StaffService {
	/**
	 * 
	 * @param model
	 */
	public void sava(Staff model);

	/**
	 * 
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	/**
	 * 
	 * @param ids
	 */
	public void batchDel(String ids);

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Staff getStaff(String id);
	/**
	 * 
	 * @param staff
	 */
	public void update(Staff staff);

	public List<Staff> findListNotDelete();
	
}
