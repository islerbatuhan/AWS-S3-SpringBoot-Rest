package springboot.s3rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springboot.s3rest.services.*;

@RestController
@RequestMapping("/assignment2/s3storage")
public class UploadFileController {

	@Autowired
	S3Services s3Services;
	
	
	
	//Put//////////////////////////
	
    @PutMapping("/uploadFilePut/{keyname}")
    public String uploadMultipartFilePut(@PathVariable("keyname") String keyname, 
    		@RequestPart(value = "uploadfileput") MultipartFile file) {
		s3Services.uploadFilePut(keyname, file);
		return "Put Upload Successfully. -> KeyName = " + keyname;
    }
    
    @RequestMapping(value= "/{folderName}/uploadFilePut/{keyname}", method = RequestMethod.PUT)
    public String uploadMultipartFilePut(@PathVariable("keyname") String keyname, 
    		@RequestPart("uploadfileput") MultipartFile file, 
    		@PathVariable("folderName") String folderName) {
    	s3Services.uploadFilePut(keyname, file, folderName);
    	return "Put Upload Successfully. -> KeyName = " + keyname;
    }
    ///////////////////////
    //Post///////////////////////
    
    @PostMapping("/uploadFilePost")
    public String uploadMultipartFilePost(@RequestParam("uploadfilepost") MultipartFile file) {
		s3Services.uploadFilePost(file);
		return "Post Upload Successfully. -> KeyName = "  ;
    }
    
    @RequestMapping(value= "/{folderName}/uploadFilePost", method = RequestMethod.POST)
    public String uploadMultipartFilePost( @RequestParam("uploadfilepost") MultipartFile file, 
    		@PathVariable("folderName") String folderName) {
    	s3Services.uploadFilePost(file, folderName);
    	return "Post Upload Successfully. -> KeyName = " ;
    }
    
    
    
}
