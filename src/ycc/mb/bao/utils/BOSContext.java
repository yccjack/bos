package ycc.mb.bao.utils;

import org.apache.struts2.ServletActionContext;

import ycc.mb.bao.domain.User;

public class BOSContext {
	public static User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}
