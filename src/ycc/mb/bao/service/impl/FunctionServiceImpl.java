package ycc.mb.bao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.mb.bao.dao.FunctionDao;
import ycc.mb.bao.domain.Function;
import ycc.mb.bao.service.FunctionService;
import ycc.mb.bao.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDao functionDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findAll() {
		List<Function> list = functionDao.findAll();
		return list;
	}

	@Override
	public void save(Function model) {
		Function function = model.getFunction();
		if(function.getId()!=null&&function.getId().equals("")){
			model.setFunction(null);
		}
		functionDao.save(model);
		
	}
}
