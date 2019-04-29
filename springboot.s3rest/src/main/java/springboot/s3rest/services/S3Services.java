package springboot.s3rest.services;

import java.io.ByteArrayOutputStream;

import org.springframework.web.multipart.MultipartFile;

public interface S3Services {
	
	public ByteArrayOutputStream downloadFile(String keyName);
	public ByteArrayOutputStream downloadFile(String keyName,String folderName);
	
	public void uploadFilePut(String keyName, MultipartFile file);
	public void uploadFilePut(String keyName, MultipartFile file, String folderName);
	
	public void uploadFilePost(MultipartFile file);
	public void uploadFilePost(MultipartFile file, String folderName);
	
	public void deleteFile(String fileUrl);
	public void deleteFile(String fileUrl, String folderName);
}