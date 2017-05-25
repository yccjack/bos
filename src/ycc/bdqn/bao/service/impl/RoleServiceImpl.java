package ycc.bdqn.bao.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.RoleDao;
import ycc.bdqn.bao.domain.Function;
import ycc.bdqn.bao.domain.Role;
import ycc.bdqn.bao.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	@Override
	public void save(Role model, String ids) {
		roleDao.save(model);
		String[] split = ids.split(",");
		for (String string : split) {
			Function f=new Function();
			f.setId(string);
			model.getFunctions().add(f);
		}
		
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	
	
}
