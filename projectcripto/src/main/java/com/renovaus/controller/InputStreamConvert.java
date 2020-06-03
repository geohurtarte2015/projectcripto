package com.renovaus.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public class InputStreamConvert {
	
	public static File convertInputStream(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		  try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
			  fos.write(file.getBytes());
			  fos.close(); 
		} catch (IOException e) {
			System.out.println("Error create file "+e);
		} 
		  
		  return convFile;
	
	}
	
	public static Long sizeFile(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		
		  try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
			  fos.write(file.getBytes());
			  fos.close(); 
		} catch (IOException e) {
			System.out.println("Error create file "+e);
		} 
		 Long size =  convFile.length();
		  
		  return size;
	
	}
	
	 @SuppressWarnings("unused")
	public static byte[] readBytesFromFile(MultipartFile file) throws IOException {
		 
		 File convFile = new File(file.getOriginalFilename());
		  try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
			  fos.write(file.getBytes());
			  fos.close(); 
		} catch (IOException e) {
			System.out.println("Error create file "+e);
		} 
		 
		  FileInputStream inputStream = new FileInputStream(convFile);
		  
	         
	        byte[] fileBytes = new byte[(int) convFile.length()];
	        inputStream.read(fileBytes);
	        inputStream.close();
	         
	        return fileBytes;
	    }
}
