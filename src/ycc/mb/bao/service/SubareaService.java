package ycc.mb.bao.service;

import java.util.List;

import ycc.mb.bao.domain.Subarea;
import ycc.mb.bao.utils.PageBean;

public interface SubareaService {

	void save(Subarea entity);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> getListNotAssociation();

}
