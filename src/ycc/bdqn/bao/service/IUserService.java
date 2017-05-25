package ycc.bdqn.bao.service;

import java.util.List;

import ycc.bdqn.bao.domain.User;

public interface IUserService {

	public User login(User model);

	public void editPassword(String password, String id);

	public List<User> findAll();

}
