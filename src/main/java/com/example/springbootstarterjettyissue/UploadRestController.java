package com.example.springbootstarterjettyissue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadRestController {

    @PostMapping
    public void upload(@RequestParam("file") final MultipartFile file) {
        System.out.println("Success: fileName = " + file.getOriginalFilename() + ", fileSize = " + file.getSize() + " bytes.");
    }
}
