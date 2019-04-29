package springboot.s3rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import springboot.s3rest.services.S3Services;

@RestController
@RequestMapping("/assignment2/s3storage")
public class DeleteFileController {

	@Autowired
	S3Services s3Services;
	
	
	@RequestMapping(value= "/deleteFile", method = RequestMethod.DELETE)
	public String deleteFile( @RequestPart("keyname") String keyname) {
		s3Services.deleteFile(keyname);
		return "Delete succesfull. -> KeyName = " + keyname + " is deleted";
	}
	
	@RequestMapping(value= "/{folderName}/deleteFile", method = RequestMethod.DELETE)
	public String deleteFile( @RequestPart("keyname") String keyname, 
			@PathVariable("folderName") String folderName) {
		s3Services.deleteFile(keyname, folderName);
		return "Delete succesfull. -> KeyName = " + keyname+ " is deleted";
	}
	
	
}
