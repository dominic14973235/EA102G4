package com.grouporder.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeForINGYM {

	// 掛上兩個jar檔
	// 1. zxing-core
	// 2. zxing-javase
	public static byte[] parameterQRcode(StringBuffer url, String webAppFilePath,String g_time_no) throws IOException, WriterException{
		String text = url.toString();
		int width = 300;
		int height = 300;
		String format = "jpg";
		// 設定編碼格式與容錯率
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		String fileName = g_time_no + new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + ".jpg";
		Path path = FileSystems.getDefault().getPath(webAppFilePath, fileName);
		// 開始產生QRCode
		BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		// 把產生的QRCode存到指定的目錄
		MatrixToImageWriter.writeToPath(matrix, format, path);
//		System.out.println("path=" + path.toString());
		File file = new File(path.toString());
		InputStream input = new FileInputStream(file);
		byte[] result = new QRCodeForINGYM().convert2ByteReturnByetArray(input);
		file.delete();
		return result;
	}

	public static void main(String[] args) throws IOException, WriterException {
		parameterQRcode(new StringBuffer("AAA"),"C:\\img","123");
	}

	private String convert2Byte(InputStream input) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int length = 0;
		while ((length = input.read(buff, 0, 100)) > 0) {
			baos.write(buff, 0, length);
		}
		byte[] in2b = baos.toByteArray();
		baos.flush();
		baos.close();
		input.close();
		return new String(Base64.getEncoder().encodeToString(in2b));

	}
	private byte[] convert2ByteReturnByetArray(InputStream input) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int length = 0;
		while ((length = input.read(buff, 0, 100)) > 0) {
			baos.write(buff, 0, length);
		}
		byte[] in2b = baos.toByteArray();
		baos.flush();
		baos.close();
		input.close();
		return in2b;

	}
}
