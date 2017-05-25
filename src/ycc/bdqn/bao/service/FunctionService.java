package ycc.bdqn.bao.service;

import java.util.List;

import ycc.bdqn.bao.domain.Function;
import ycc.bdqn.bao.utils.PageBean;

public interface FunctionService {

	public void  pageQuery(PageBean pageBean);

	public List<Function> findAll();

	public void save(Function model);

}
