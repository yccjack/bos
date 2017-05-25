package ycc.mb.bao.web.intercepter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import ycc.mb.bao.domain.User;

public class MyInteceper extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user=(User) ActionContext.getContext().getSession().get("loginUser");
		if(user!=null){
			invocation.invoke();
		}
		return "login";
	}

}
