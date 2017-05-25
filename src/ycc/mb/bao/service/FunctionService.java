package ycc.mb.bao.service;

import java.util.List;

import ycc.mb.bao.domain.Function;
import ycc.mb.bao.utils.PageBean;

public interface FunctionService {

	public void  pageQuery(PageBean pageBean);

	public List<Function> findAll();

	public void save(Function model);

}
