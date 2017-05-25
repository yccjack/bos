package ycc.bdqn.bao.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.StaffDao;
import ycc.bdqn.bao.domain.Staff;
import ycc.bdqn.bao.service.StaffService;
import ycc.bdqn.bao.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDao staffDao;

	@Override
	public void sava(Staff model) {
		staffDao.save(model);

	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);

	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDel(String ids) {
		String[] staffIds = ids.split(",");
		for (String id : staffIds) {
			staffDao.executeUpdate("staff.delete", id);
		}
	}

	@Override
	public Staff getStaff(String id) {
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
		
	}

	/**
	 * 查询没有作废的取派员，deltag为“0”
	 */
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.ne("deltag", "1"));//<>1的取派員
		return staffDao.findByCriteria(detachedCriteria);
	}

}
