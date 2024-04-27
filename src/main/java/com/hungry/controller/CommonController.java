package com.hungry.controller;

import com.hungry.pojo.Result;
import com.hungry.utils.AliOssUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static com.hungry.constant.MessageConstant.FILE_UPLOAD;

/**
 * @Author:ascrm
 * @Date:2024/4/27
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class CommonController {

    private final AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String prefix = UUID.randomUUID().toString();

        String fileName = prefix + suffix;
        aliOssUtil.upload(file.getBytes(),fileName);
        return Result.success(FILE_UPLOAD);
    }
}
