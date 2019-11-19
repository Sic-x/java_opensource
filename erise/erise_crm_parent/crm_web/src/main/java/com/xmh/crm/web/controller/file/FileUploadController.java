package com.xmh.crm.web.controller.file;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public String uploadFile(MultipartFile file) throws Exception{
        String uploadPaths = "D:\\itsource\\作业\\IEDAWorkSpace\\_2019_11_08_crm_parent\\crm_web\\src\\main\\webapp\\upload";

        String extensionName = FilenameUtils.getExtension(file.getOriginalFilename()); //1.jpg  jpg
        String fileName = UUID.randomUUID().toString() + "." + extensionName;

        FileOutputStream os = null;
        os = new FileOutputStream(new File(uploadPaths, fileName));
        InputStream is = file.getInputStream();
        IOUtils.copy(is,os);
        os.close();
        return "/upload/" + fileName;


    }
}
