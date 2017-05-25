package ycc.bdqn.bao.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import ycc.bdqn.bao.crm.CustomerService;
import ycc.bdqn.bao.service.DecidedzoneService;
import ycc.bdqn.bao.service.FunctionService;
import ycc.bdqn.bao.service.NoticebillService;
import ycc.bdqn.bao.service.RegionService;
import ycc.bdqn.bao.service.RoleService;
import ycc.bdqn.bao.service.StaffService;
import ycc.bdqn.bao.service.SubareaService;
import ycc.bdqn.bao.service.WorkorderbillService;
import ycc.bdqn.bao.utils.PageBean;

/**
 * 通用Action实现
 * 
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	// 模型对象
	@Autowired
	protected RegionService regionService;
	@Autowired
	protected StaffService staffService;
	@Autowired
	protected SubareaService subareaService;
	@Autowired
	protected DecidedzoneService decidedzoneService;
	@Autowired
	protected CustomerService customerService;
	@Autowired
	protected NoticebillService noticebillService;
	@Autowired
	protected WorkorderbillService workorderbillService;
	@Autowired
	protected FunctionService functionService;
	@Autowired
	protected RoleService roleService;
	protected PageBean pageBean = new PageBean();
	DetachedCriteria detachedCriteria = null;

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	// 模型对象
	protected T model;

	public T getModel() {
		return model;
	}

	/**
	 * 在构造方法中动态获得实现类型，通过反射创建模型对象
	 */
	public BaseAction() {
		ParameterizedType genericSuperclass = null;

		
			genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		// 获得实体类型
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			// 通过反射创建对象
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void writePageBean2Json(PageBean pageBean, String[] excludes) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}

	public void writeList2Json(List list, String[] excludes) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONArray jsonObject = JSONArray.fromObject(list, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}

	public void writeObject2Json(Object object, String[] excludes) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
