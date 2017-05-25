package ycc.bdqn.bao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ycc.bdqn.bao.dao.FunctionDao;
import ycc.bdqn.bao.dao.base.impl.BaseDaoImpl;
import ycc.bdqn.bao.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{

	@Override
	public List<Function> findListByUserid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
