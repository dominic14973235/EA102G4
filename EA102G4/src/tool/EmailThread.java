package tool;

import tool.MailService;
import com.mem.model.MemVO;


public class EmailThread extends Thread {

	
	MemVO memVO;
	

	public EmailThread() {
	}

	public EmailThread( MemVO memVO) {
		this.memVO = memVO;
	}

	public void run() {
		MailService mailSvc = new MailService();

		
			String subject = "密碼通知";
			String messageText = "Hello! " + memVO.getMem_name() +",請謹記此密碼: "+ memVO.getMem_psw() +"\n(帳號已啟用)";
			
			MailService mailservice = new MailService();
			mailservice.sendMail(memVO.getMem_email(), subject, messageText);
		
	}
}
