package ycc.bdqn.bao.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;
import ycc.bdqn.bao.domain.Staff;
import ycc.bdqn.bao.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	private static final long serialVersionUID = 1L;
	// 接收页面的当前页和每页显示数量


	public String add() {
	
		staffService.sava(model);
		return "list";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws IOException
	 */
	public String pageQuery() throws IOException {
		staffService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
		return NONE;
	}
	/**
	 * 获取操作的ids
	 */
	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}
	@RequiresPermissions(value="staffs")
	public String delete() {
		staffService.batchDel(ids);
		return "list";
	}

	/**
	 * 为了防止传过来的数据只有部分数据而直接修改会把别的数据置为0，先从数据库将数据查出来在进行数据更新
	 * 
	 * @return
	 */
	public String edit() {
		Staff staff = staffService.getStaff(model.getId());
		// 再按照页面提交的参数进行覆盖
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staffService.update(staff);// 如果上面代码在service层不需要调用此方法
		return "list";
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String view() throws IOException{
		Staff staff = staffService.getStaff(ids);	
		JSONObject fromObject = JSONObject.fromObject(staff);
		String json = fromObject.toString();
		System.out.println(json);
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");// 需要设置写入数据为json格式
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
	

	/**
	 * 查询没有作废的取派员，返回json
	 * @throws IOException 
	 */
	public String getListNotDel() throws IOException{
		List<Staff> list = staffService.findListNotDelete();
		String[] excludes = new String[]{"decidedzones"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
}
