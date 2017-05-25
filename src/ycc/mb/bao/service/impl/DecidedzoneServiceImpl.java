package ycc.mb.bao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.mb.bao.dao.DecidedzoneDao;
import ycc.mb.bao.dao.SubareaDao;
import ycc.mb.bao.domain.Decidedzone;
import ycc.mb.bao.domain.Subarea;
import ycc.mb.bao.service.DecidedzoneService;
import ycc.mb.bao.utils.PageBean;

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
