package com.study.springboot.api;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.FileData;
import com.study.springboot.service.FileDataService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileDataApiController {
	
	
	private final FileDataService fileDataService;
	
	@PostMapping("/file")
	@CrossOrigin
//	public ResponseEntity<?> uploadImage(
//			@RequestParam("image") MultipartFile file) throws IOException{
//		String uploadImage = fileDataService.uploadImageToFileSystem(file);
//		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
//	}
	public ResponseEntity<?> uploadImages(@RequestParam("images") List<MultipartFile> files) throws IOException {
        List<String> uploadResults = files.stream()
                .map(file -> {
                    try {
                        return fileDataService.uploadImageToFileSystem(file);
                    } catch (IOException e) {
                        // Handle exception as needed
                        e.printStackTrace();
                        return "Failed to upload " + file.getOriginalFilename();
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(uploadResults);
    }
	
	@CrossOrigin
//	@GetMapping("/file/{filename}")
//	public ResponseEntity<?> downImage(
//			@PathVariable("filename") String fileName) throws IOException{
//		byte[] downloadImage = fileDataService.downloadImageFromFileSystem(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(downloadImage);
//	}
	@GetMapping("/file/{id}")
    public ResponseEntity<?> downImage(@PathVariable("id") Long id) throws IOException {
        byte[] downloadImage = fileDataService.downloadImageFromFileSystem(id);
        
        if (downloadImage != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(downloadImage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	
//	@GetMapping("/imageList")
//    public ResponseEntity<List<String>> getImageList() {
//        List<String> imageList = new ArrayList<>();
//
//        // Get the list of files from the directory where images are stored
//        File imageDirectoryFile = new File(imageDirectory);
//        File[] imageFiles = imageDirectoryFile.listFiles();
//
//        if (imageFiles != null) {
//            // Convert file names to image paths and add them to the list
//            Arrays.stream(imageFiles)
//                  .filter(File::isFile)
//                  .map(File::getName)
//                  .forEach(imageList::add);
//
//            return ResponseEntity.status(HttpStatus.OK).body(imageList);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
	@CrossOrigin
	 @GetMapping("/fileList")
	    public ResponseEntity<List<FileData>> getFileDataList() {
	        List<FileData> fileDataList = fileDataService.findAll();

	        if (!fileDataList.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.OK).body(fileDataList);
	        } else {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	    }
	
	
}
