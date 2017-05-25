package ycc.bdqn.bao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.DecidedzoneDao;
import ycc.bdqn.bao.dao.SubareaDao;
import ycc.bdqn.bao.domain.Decidedzone;
import ycc.bdqn.bao.domain.Subarea;
import ycc.bdqn.bao.service.DecidedzoneService;
import ycc.bdqn.bao.utils.PageBean;

@Service
@Scope("prototype")
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {

	@Autowired
	DecidedzoneDao decidedzoneDao;
	@Autowired
	SubareaDao subareaDao;

	@Override
	public void save(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save(model);
		for (String string : subareaid) {
			Subarea subarea = subareaDao.findById(string);
			subarea.setDecidedzone(model);
		}

	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	
	}

	

}
