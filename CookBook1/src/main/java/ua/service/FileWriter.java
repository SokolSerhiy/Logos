package ua.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		INGREDIENT
	}
	
	boolean write(Folder folder, MultipartFile file, Long id);
}
