package ycc.bdqn.bao.service;

import java.util.List;

import ycc.bdqn.bao.domain.Subarea;
import ycc.bdqn.bao.utils.PageBean;

public interface SubareaService {

	void save(Subarea entity);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> getListNotAssociation();

}
