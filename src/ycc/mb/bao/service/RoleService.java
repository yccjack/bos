package ycc.mb.bao.service;

import java.util.List;

import ycc.mb.bao.domain.Role;

public interface RoleService {

	public void save(Role model, String ids);

	public List<Role> findAll();

}
