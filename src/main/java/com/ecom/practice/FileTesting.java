package com.ecom.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileTesting {
	
	public static void main(String[] args) throws IOException {
		
		
		
		String path = "D:\\Messi.jpg";
//		String path = "C:\\Users\\Its\\Desktop\\Messi.jpg";
		System.out.println(path);

		
		File f = new File(path);
		System.out.println(f.exists());
		if (!f.exists()) {
			boolean mkdir = f.mkdir();
			System.out.println(mkdir);
		}
		
		FileInputStream fileInputStream = new FileInputStream(path);
		
		
		Files.copy(fileInputStream, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		fileInputStream.close();
	}

}
