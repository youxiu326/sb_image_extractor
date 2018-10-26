package com.huarui.action;

import com.huarui.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping("/upload")
    public @ResponseBody String fileUpload(MultipartFile file) throws IOException {

        File localFile = new File("e://a.jpg");
        String ex = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //图片翻转90
        int angle =  ImageUtil.getAngle(ImageUtil.getExif(file.getInputStream()));
        BufferedImage bf =ImageUtil.getBufferedImg(ImageIO.read(file.getInputStream()), ImageUtil.getWidth(file.getInputStream()), ImageUtil.getHeight(file.getInputStream()), angle);
        ImageIO.write(bf, ex.substring(1), localFile);
        //度问题
        return localFile.getAbsolutePath();
    }

    @RequestMapping("/")
    public String index(){
        return "test";
    }
}