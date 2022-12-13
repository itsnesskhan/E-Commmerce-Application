package com.ecom.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.services.FileServices;

@Service
public class FileServiceImpl implements FileServices{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		String filename = file.getOriginalFilename();
		
		String randomuid = UUID.randomUUID().toString();
	
		String newfileName = randomuid.concat(filename.substring(filename.lastIndexOf(".")));
		
		String filePath = path+File.separator+newfileName;
		
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath),StandardCopyOption.REPLACE_EXISTING);
		
		return newfileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String filePathString= path+File.separator+fileName;
		return new FileInputStream(filePathString);
	}

}
