package ycc.mb.bao.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ycc.crm.domain.Customer;
import ycc.mb.bao.crm.CustomerService;
import ycc.mb.bao.dao.DecidedzoneDao;
import ycc.mb.bao.dao.NoticebillDao;
import ycc.mb.bao.dao.WorkbillDao;
import ycc.mb.bao.domain.Decidedzone;
import ycc.mb.bao.domain.Noticebill;
import ycc.mb.bao.domain.Staff;
import ycc.mb.bao.domain.Workbill;
import ycc.mb.bao.service.NoticebillService;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
	@Autowired
	private NoticebillDao noticebillDao;
	@Autowired
	CustomerService proxy;
	@Autowired
	DecidedzoneDao decidedzoneDao;
	@Autowired
	WorkbillDao workbillDao;
	@Override
	public void save(Noticebill model) {
		noticebillDao.save(model);//持久对象
		//获取取件地址
		String pickaddress = model.getPickaddress();
		//根据取件地址查询定区---从crm服务查询 
		Customer dId = proxy.findDecidedzoneIdByPickaddress(pickaddress);
		if(dId != null){
			//查询到定区id，可以自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(dId.getDecidedzone_id());
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);//业务通知单关联匹配到的取派员
			model.setOrdertype("自动");//分单类型
			//需要为取派员创建一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单创建的时间
			workbill.setNoticebill(model);//工单关联业务通知单
			workbill.setPickstate("未取件");//取件状态
			workbill.setRemark(model.getRemark());//备注
			workbill.setStaff(staff);//工单关联取派员
			workbill.setType("新单");
			workbillDao.save(workbill);//保存工单
			//调用短信平台服务，给取派员发送短信
		}else{
			//没有查询到定区id，转为人工分单
			model.setOrdertype("人工");
		}
	}
	
}
