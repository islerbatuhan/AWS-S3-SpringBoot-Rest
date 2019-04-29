package springboot.s3rest.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import springboot.s3rest.services.*;

@RestController
@RequestMapping("/assignment2/s3storage")
public class DownloadFileController {

	@Autowired
	S3Services s3Services;
	 
    /*
     * Download Files
     */
	@GetMapping("/{keyname}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) {
		ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(keyname);
	
		return ResponseEntity.ok()
					.contentType(contentType(keyname))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
					.body(downloadInputStream.toByteArray());	
	}
	
	@RequestMapping(value= "/{folderName}/{keyname}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFile(@PathVariable("folderName") String folderName, 
			@PathVariable("keyname") String keyname){
		ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(keyname,folderName);
		return ResponseEntity.ok()
				.contentType(contentType(keyname))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
				.body(downloadInputStream.toByteArray());	
	}
	
	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length-1];
		switch(type) {
			case "txt": return MediaType.TEXT_PLAIN;
			case "png": return MediaType.IMAGE_PNG;
			case "jpg": return MediaType.IMAGE_JPEG;
			default: return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
	
	
	
	
}
