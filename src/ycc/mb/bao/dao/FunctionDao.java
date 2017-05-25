package ycc.mb.bao.dao;

import java.util.List;

import ycc.mb.bao.dao.base.IBaseDao;
import ycc.mb.bao.domain.Function;

public interface FunctionDao extends IBaseDao<Function>{

	List<Function> findListByUserid(String id);

}
