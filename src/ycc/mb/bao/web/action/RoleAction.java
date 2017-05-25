package ycc.mb.bao.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ycc.mb.bao.domain.Role;
import ycc.mb.bao.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String add() {
		roleService.save(model, ids);
		return "list";
	}

	public String getAll() throws Exception {
		List<Role> list = roleService.findAll();
		String[] excludes = new String[] {"functions","users"};
		this.writeList2Json(list, excludes);

		return NONE;
	}

}
