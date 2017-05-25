package ycc.bdqn.bao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.WorkorderbillDao;
import ycc.bdqn.bao.domain.Workordermanage;
import ycc.bdqn.bao.service.WorkorderbillService;

@Service
@Transactional
public class WorkorderbillServiceImpl implements WorkorderbillService {

	@Autowired
	private WorkorderbillDao workorderbillDao;

	@Override
	public void save(Workordermanage model) {
	workorderbillDao.save(model);
		
	}
}
