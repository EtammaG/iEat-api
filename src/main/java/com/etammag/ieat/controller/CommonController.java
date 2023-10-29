package com.etammag.ieat.controller;

import com.etammag.ieat.entity.dto.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${ieat.sys.file-base-path}")
    private String basePath;

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('COMMON')")
    public Result<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID() + suffix;
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + filename));
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
        return Result.success(filename);
    }

    @GetMapping("/download")
    @PreAuthorize("hasAuthority('COMMON')")
    public void download(String name, HttpServletResponse response) {
        try (
                FileInputStream fileInputStream = new FileInputStream(basePath + name);
                ServletOutputStream outputStream = response.getOutputStream()) {
            // 图片
            response.setContentType("image/jpeg");
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("下载失败", e);
        }
    }
}
