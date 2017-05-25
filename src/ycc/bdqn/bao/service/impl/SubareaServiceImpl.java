package ycc.bdqn.bao.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.SubareaDao;
import ycc.bdqn.bao.domain.Subarea;
import ycc.bdqn.bao.service.SubareaService;
import ycc.bdqn.bao.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {

	@Autowired
	private SubareaDao subareaDao;

	@Override
	public void save(Subarea entity) {
		subareaDao.save(entity);

	}

	@Override
	public void pageQuery(PageBean pageBean) {

		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> getListNotAssociation() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(detachedCriteria);
		
	}

}
