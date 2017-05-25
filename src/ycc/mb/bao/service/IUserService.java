package ycc.mb.bao.service;

import java.util.List;

import ycc.mb.bao.domain.User;

public interface IUserService {

	public User login(User model);

	public void editPassword(String password, String id);

	public List<User> findAll();

}
