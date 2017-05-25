package ycc.bdqn.bao.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ycc.bdqn.bao.domain.Workordermanage;
import ycc.bdqn.bao.web.action.base.BaseAction;

@Controller
@Scope
public class WorkorderbillAction extends BaseAction<Workordermanage>{

	public String add() throws IOException{
		String flag = "1";
		try{
			workorderbillService.save(model);
		}catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
}
