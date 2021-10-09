package com.cyfhandsome.download.controller;

import com.cyfhandsome.download.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author cyf
 * @date 2021/10/8 16:31
 */
@RestController
public class PicDownloadController {
    /**
     * data为链接，用 , 分割
     * @param response
     * @param data
     * @throws Exception
     */
    @RequestMapping("/download")
    public void download(HttpServletResponse response , String data) throws Exception {
        ZipOutputStream zos;
        //转换中文否则可能会产生乱码
        String downloadFilename = URLEncoder.encode("测试压缩文件", "UTF-8");
        // 指明response的返回对象是文件流
        response.setContentType("application/octet-stream");
        // 设置在下载框默认显示的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename+".zip");
        zos = new ZipOutputStream(response.getOutputStream());
        String[] admissions = data.split(",");
        for (String admission : admissions) {
            zos.putNextEntry(new ZipEntry(admission + ".png"));
            ImageIO.write(QRCodeUtil.encode(admission, "D:\\logo.png", true), "jpg", zos);
        }
        zos.flush();
        zos.close();
    }

}
