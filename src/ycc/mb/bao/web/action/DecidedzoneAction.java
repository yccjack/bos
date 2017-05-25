package ycc.mb.bao.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;

import cn.ycc.crm.domain.Customer;
import ycc.mb.bao.domain.Decidedzone;
import ycc.mb.bao.web.action.base.BaseAction;

@Controller
public class DecidedzoneAction extends BaseAction<Decidedzone>{
	
	// 接收分区id
		private String[] subareaid;

		public void setSubareaid(String[] subareaid) {
			this.subareaid = subareaid;
		}

	
	public String save(){
		decidedzoneService.save(model,subareaid);
	
		return "list";
	}
	
	public String pageQuery() throws IOException{
		decidedzoneService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[] { "decidedzones",
				"subareas", "currentPage", "detachedCriteria", "pageSize" });
		
		return NONE;
	}
	
	public String findnoassociationCustomers() throws IOException{
		List<Customer> list = customerService.findnoassociationCustomers();
		String[] excludes=new String[]{};
		this.writeList2Json(list, excludes);
		return NONE;
	}
	
	public String findhasassociationCustomers() throws IOException{
		List<Customer> list = customerService.findhasassociationCustomers(model.getId());
		String[] excludes=new String[]{""};
		this.writeList2Json(list, excludes);
		
		return NONE;
	}
	
	
	private Integer[] customerIds;
	public String assigncustomerstodecidedzone(){
		customerService.assignCustomersToDecidedZone(customerIds, model.getId());
		return "list";
	}
	public Integer[] getCustomerIds() {
		return customerIds;
	}
	
	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}
}
