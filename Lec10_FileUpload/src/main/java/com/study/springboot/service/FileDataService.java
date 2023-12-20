package com.study.springboot.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.FileData;
import com.study.springboot.repository.FileDataRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileDataService {
	private final String FOLDER_PATH = "C:\\images\\";
	private final FileDataRepository filedataRepository;
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException{
		log.info("upload file: [}" + file.getOriginalFilename());
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		FileData fileData = filedataRepository.save(
				FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath)
				.build());
		
		//파일경로
		file.transferTo(new File(filePath));
		if(filePath != null) {
			return "file uploaded successfully! filePath" + filePath;
		}
		return null;
	}
	
	public byte[] downloadImageFromFileSystem(Long id) throws IOException {
        FileData fileData = filedataRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        String filePath = fileData.getFilePath();

        log.info("download fileData: {}", fileData);
        log.info("download filePath: {}", filePath);

        return Files.readAllBytes(new File(filePath).toPath());
    }

	public List<FileData> findAll() {
		return filedataRepository.findAll();
	}
}
