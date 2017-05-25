package ycc.mb.bao.web.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ycc.crm.domain.Customer;
import ycc.mb.bao.domain.Noticebill;
import ycc.mb.bao.domain.User;
import ycc.mb.bao.utils.BOSContext;
import ycc.mb.bao.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	private static final long serialVersionUID = 1L;

	public String findCustomerByPhone() throws IOException{
		Customer customer = customerService.findCustomerByPhonenumber(model.getTelephone());
		String[] excludes=new String[]{};
		this.writeObject2Json(customer, excludes);
		return NONE;
	}	
	
	/**
	 * 保存业务通知单，尝试自动分单
	 * @return
	 */
	public String add(){
		User user = BOSContext.getLoginUser();
		model.setUser(user);
		noticebillService.save(model);
		return "addUI";
	}
}
