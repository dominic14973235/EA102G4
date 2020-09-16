package tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.bodyrecord.model.*;
import com.group.model.GroupService;
import com.group.model.GroupVO;
import com.group.model.PicUpload;
import com.memphoto.model.*;

public class updatePhoto extends HttpServlet{

	public static byte[] getPictureByteArray(String path) throws IOException {
		  File file = new File(path);
		  FileInputStream fis = new FileInputStream(file);
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  byte[] buffer = new byte[8192];
		  int i;
		  while ((i = fis.read(buffer)) != -1) {
		   baos.write(buffer, 0, i);
		  }
		  baos.close();
		  fis.close();

		  return baos.toByteArray();
		 }
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		updatePhoto up = new updatePhoto();
		MemPhotoJDBCDAO memPhotodao = new MemPhotoJDBCDAO();
		MemPhotoVO memPhotoVO = new MemPhotoVO();
		
		BodyRecordJDBCDAO bodyRecorddao = new BodyRecordJDBCDAO();
		BodyRecordVO bodyRecordVO = new BodyRecordVO();
		
		PicUpload groJDBCDAO = new PicUpload();
		List<GroupVO> group_list = groJDBCDAO.getAll();
		
		String  url = "C:\\Users\\p124127\\Desktop\\照片\\會員";
		String  url_Group = "C:\\Users\\p124127\\Desktop\\照片\\揪團";

		  
		  byte[] pic = null;
		  
		  // 揪團圖片
		  for(int i = 0;i<group_list.size();i++) {
			  GroupVO groVO_new = group_list.get(i);
			  pic = up.getPictureByteArray(url_Group+i+".jpg");
			  groVO_new.setGro_pic(pic);
			  
			  
			  groJDBCDAO.update(groVO_new);
		  }
		  
		  // 大頭貼圖片
		  memPhotoVO.setPhoto_no("MEMP000001");
		  memPhotoVO.setMem_id("MEM0000001");
		  pic = up.getPictureByteArray(url + "03.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  
		  memPhotoVO.setPhoto_no("MEMP000002");
		  memPhotoVO.setMem_id("MEM0000002");
		  pic = up.getPictureByteArray(url + "01.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000003");
		  memPhotoVO.setMem_id("MEM0000003");
		  pic = up.getPictureByteArray(url + "13.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000004");
		  memPhotoVO.setMem_id("MEM0000004");
		  pic = up.getPictureByteArray(url + "04.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000005");
		  memPhotoVO.setMem_id("MEM0000005");
		  pic = up.getPictureByteArray(url + "05.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000006");
		  memPhotoVO.setMem_id("MEM0000006");
		  pic = up.getPictureByteArray(url + "06.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000007");
		  memPhotoVO.setMem_id("MEM0000007");
		  pic = up.getPictureByteArray(url + "07.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000008");
		  memPhotoVO.setMem_id("MEM0000008");
		  pic = up.getPictureByteArray(url + "08.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000009");
		  memPhotoVO.setMem_id("MEM0000009");
		  pic = up.getPictureByteArray(url + "09.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000010");
		  memPhotoVO.setMem_id("MEM0000010");
		  pic = up.getPictureByteArray(url + "10.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000011");
		  memPhotoVO.setMem_id("MEM0000011");
		  pic = up.getPictureByteArray(url+ "11.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  
		  memPhotoVO.setPhoto_no("MEMP000012");
		  memPhotoVO.setMem_id("MEM0000012");
		  pic = up.getPictureByteArray(url+ "12.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000013");
		  memPhotoVO.setMem_id("MEM0000013");
		  pic = up.getPictureByteArray(url+ "05.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000014");
		  memPhotoVO.setMem_id("MEM0000014");
		  pic = up.getPictureByteArray(url+ "14.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000015");
		  memPhotoVO.setMem_id("MEM0000015");
		  pic = up.getPictureByteArray(url+ "15.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000016");
		  memPhotoVO.setMem_id("MEM0000016");
		  pic = up.getPictureByteArray(url+ "03-1.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000017");
		  memPhotoVO.setMem_id("MEM0000017");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000018");
		  memPhotoVO.setMem_id("MEM0000018");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000019");
		  memPhotoVO.setMem_id("MEM0000019");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000020");
		  memPhotoVO.setMem_id("MEM0000020");
		  pic = up.getPictureByteArray(url+ "07.png");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000021");
		  memPhotoVO.setMem_id("MEM0000021");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  
		  memPhotoVO.setPhoto_no("MEMP000022");
		  memPhotoVO.setMem_id("MEM0000022");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000023");
		  memPhotoVO.setMem_id("MEM0000023");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000024");
		  memPhotoVO.setMem_id("MEM0000024");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000025");
		  memPhotoVO.setMem_id("MEM0000025");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000026");
		  memPhotoVO.setMem_id("MEM0000026");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000027");
		  memPhotoVO.setMem_id("MEM0000027");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000028");
		  memPhotoVO.setMem_id("MEM0000028");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000029");
		  memPhotoVO.setMem_id("MEM0000029");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000030");
		  memPhotoVO.setMem_id("MEM0000030");
		  pic = up.getPictureByteArray(url+ "coach8.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  memPhotoVO.setPhoto_no("MEMP000031");
		  memPhotoVO.setMem_id("MEM0000031");
		  pic = up.getPictureByteArray(url+ "16.jpg");
		  memPhotoVO.setPhoto(pic);
		  memPhotodao.update(memPhotoVO);
		  
		  
		  
		  
		  
		  
		  //個人身體數據資料圖片
		  bodyRecordVO.setMem_id("MEM0000001");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000001");
		  pic = up.getPictureByteArray(url+ "01-1.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000002");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000002");
		  pic = up.getPictureByteArray(url+ "02.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000003");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000003");
		  pic = up.getPictureByteArray(url+ "03.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000004");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000004");
		  pic = up.getPictureByteArray(url+ "04.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000005");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000005");
		  pic = up.getPictureByteArray(url+ "05.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000006");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000006");
		  pic = up.getPictureByteArray(url+ "06.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000007");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000007");
		  pic = up.getPictureByteArray(url+ "07.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000008");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000008");
		  pic = up.getPictureByteArray(url+ "08.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000009");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000009");
		  pic = up.getPictureByteArray(url+ "09.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
		  
		  bodyRecordVO.setMem_id("MEM0000010");
		  bodyRecordVO.setMem_height((float)180.5);
		  bodyRecordVO.setMem_weight(70);
		  bodyRecordVO.setMem_bmi(24);
		  bodyRecordVO.setMem_old(30);
		  bodyRecordVO.setMem_bmr(1500);
		  bodyRecordVO.setFrequency(2);
		  bodyRecordVO.setMem_tdee(2000);
		  bodyRecordVO.setBody_no("BODY000010");
		  pic = up.getPictureByteArray(url+ "10-1.png");
		  bodyRecordVO.setPhoto(pic);
		  bodyRecorddao.update(bodyRecordVO);
			  
	}
	
	 

}
