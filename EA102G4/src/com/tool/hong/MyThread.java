package com.tool.hong;

import java.io.IOException;
import java.util.List;

import com.godetail.model.GroupOrderDetailVO;
import com.google.zxing.WriterException;
import com.groupclass.model.GroupClassService;
import com.groupclass.model.GroupClassVO;
import com.grouporder.controller.MailService;
import com.grouporder.controller.QRCodeForINGYM;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.pro.model.ProService;

public class MyThread extends Thread {

	List<GroupOrderDetailVO> orderDetails;
	MemVO memVO;
	String path;
	StringBuffer parameterURL;
	String start;
	public MyThread() {
	}

	public MyThread(List<GroupOrderDetailVO> orderDetails, MemVO memVO ,String path,StringBuffer parameterURL) {
		this.orderDetails = orderDetails;
		this.memVO = memVO;
		this.path=path;
		this.parameterURL=parameterURL;
		this.start=parameterURL.toString();
	}

	public void run() {
		MailService mailSvc = new MailService();

//		System.out.println("123");
		try {
			for (GroupOrderDetailVO godVO : this.orderDetails) {
				String mail=this.parameterURL.toString();
				this.parameterURL.append("?g_time_no=" + godVO.getG_time_no() + "&g_order_no=" + godVO.getG_order_no() + "&mem_id="
						+ this.memVO.getMem_id()+"&action=updateFor_Status&g_class_no="+godVO.getG_class_no());
				byte[] mailImg = QRCodeForINGYM.parameterQRcode(this.parameterURL, this.path, godVO.getG_time_no());
				GroupClassService gcSvc = new GroupClassService();
				ProService proSvc = new ProService();
				GroupClassVO  gcVO =gcSvc.getOneGroupClass(godVO.getG_class_no());
				MemService memSvc= new MemService();
				mailSvc.sendMail(this.memVO.getMem_email(), this.memVO.getMem_name(),
						godVO.getRdate() + "   " + (godVO.getHr().indexOf("1"))+"點        <br>教練:"+memSvc.getOneMem(proSvc.getOnePro(gcVO.getPro_id()).getMem_ID()).getMem_name()+"<br>上課地點:"+gcVO.getLoc(), mailImg);
				this.parameterURL=new StringBuffer(this.start);
			}
		} catch (IOException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
