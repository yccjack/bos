package ycc.bdqn.bao.service;

import ycc.bdqn.bao.domain.Decidedzone;
import ycc.bdqn.bao.utils.PageBean;

public interface DecidedzoneService {

	public void save(Decidedzone model, String[] subareaid);

	public void pageQuery(PageBean pageBean);

	

}
