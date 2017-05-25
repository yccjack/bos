package ycc.bdqn.bao.dao;

import java.util.List;

import ycc.bdqn.bao.dao.base.IBaseDao;
import ycc.bdqn.bao.domain.Function;

public interface FunctionDao extends IBaseDao<Function>{

	List<Function> findListByUserid(String id);

}
