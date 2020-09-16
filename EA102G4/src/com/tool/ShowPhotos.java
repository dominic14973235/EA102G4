package com.tool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.individualClass.model.IndividualClassService;
import com.license.model.LicenseService;
import com.mem.model.MemService;
import com.memphoto.model.MemPhotoService;


@WebServlet("/ShowPhotos")
public class ShowPhotos extends HttpServlet {

	Connection con;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		
		try {
			//初始化
			byte[] imgBuf = null;
			

			//取出照片PK
			String pic_no = req.getParameter("pic_no");
			String type= req.getParameter("type");
			
			switch(type) {
				case "proPhoto":
					LicenseService licenseSvc = new LicenseService();
					imgBuf = licenseSvc.getOneLicense(pic_no).getL_pic();
					break;
				case "iClassPhoto":
					IndividualClassService IndividualClassSvc = new IndividualClassService();
					imgBuf = IndividualClassSvc.getOneIndividualClass(pic_no).getC_pic();	
					break;
				case "memPhoto":
					MemPhotoService MemPhotoSvc=new MemPhotoService();
					imgBuf= MemPhotoSvc.getOneMemPhotoByStatus(pic_no).getPhoto();
					break;
					
			}
			out.write(imgBuf);
	
		}catch(Exception e) {
			//無圖時顯示的部分
			InputStream in = getServletContext().getResourceAsStream("/front-end/assets/404.png");
			
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
		}
		
	}

}
