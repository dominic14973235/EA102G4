package com.product_photo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AddAllTestProductPhoto { // 只須執行一次，幫21個測試商品新增它自己的圖片，圖片資料夾路徑必須是C:\\test-product-photo
	public void addOneTestProductPhoto(String fileName, String productNo) {

		PhotoService photoService = new PhotoService();
		byte[] pPhoto = null;

		try {
			String filePath = "C:\\test-product-photo\\" + fileName + ".jpg";
//				String filePath = AddAllTestProductPhoto.class.getResourceAsStream() + fileName + ".jpg";
			File file = new File(filePath);
			@SuppressWarnings("resource")
			InputStream fis = new FileInputStream(file);
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			pPhoto = buf;
			photoService.addPhoto(productNo, pPhoto);
			System.out.println(productNo + "增加了一張圖片:" + fileName);

		} catch (Exception e) {
			System.out.println("**"); //因為有些圖片只有1張所以會發生例外，但是不須理會繼續執行
		}

	}

	public static void main(String[] args) {
		AddAllTestProductPhoto addAllTestProductPhoto = new AddAllTestProductPhoto();
		int x = 1;
		int y = 1;
		String fileName = null;

		while (x <= 21) {
			while (y <= 2) {
				fileName = x + "-" + y;
				String productNo = "P00000" + x;
				if (x >= 10) {
					productNo = "P0000" + x;
				}
				addAllTestProductPhoto.addOneTestProductPhoto(fileName, productNo);
				y++;
			}
			x++;
			y = 1;
		}
	}

}
