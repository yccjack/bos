package ycc.mb.bao.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ycc.mb.bao.domain.Function;
import ycc.mb.bao.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	private static final long serialVersionUID = 1L;

	public String pageQuery() throws IOException {
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		String[] excludes = new String[] { "function", "functions", "roles" };
		this.writePageBean2Json(pageBean, excludes);
		return NONE;
	}

	public String getAll() throws IOException {
		List<Function> list = functionService.findAll();
		String[] excludes = new String[] { "function", "functions", "roles" };
		this.writeList2Json(list, excludes);
		return NONE;
	}
	
	public String add(){
		functionService.save(model);
		
		return "list";
	}
}
