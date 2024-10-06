package com.xujie.business.controller;

import com.xujie.business.common.entity.Result;
import jakarta.annotation.Resource;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @author Xujie
 * @since 2024/10/5 20:44
 */
@RestController
@RequestMapping("/file")
public class FileController {
  @Resource private FileStorageService fileStorageService;

  /**
   * 上传文件
   *
   * @param file 文件
   * @return 文件信息
   */
  @PostMapping("/upload")
  public Result<FileInfo> upload(@RequestParam("file") MultipartFile file) {
    FileInfo upload = fileStorageService.of(file).upload();
    return Result.ok(upload);
  }
}
