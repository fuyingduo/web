package com.fuyd.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
    private static final String FILE_CLASS = "[FileUploadController]";
    private static final String url = "/Volumes/fuydWork/work/web/src/main/webapp/upload/a.jpg";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@RequestPart(value = "profileName") MultipartFile profileName, HttpServletRequest request, HttpServletResponse response) {
        if (profileName == null) {
            log.error(FILE_CLASS + "[fileUpload] profileName is null");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = profileName.getOriginalFilename();
        File targetFile = new File(path, fileName);
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            profileName.transferTo(targetFile);
        } catch (IOException e) {
            log.error(FILE_CLASS + "[fileUpload] error:{}", e.getMessage());
            return "/view/upload-error";
        }
        return "/view/upload-success";
    }
}
