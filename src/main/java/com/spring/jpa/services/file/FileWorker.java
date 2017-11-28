package com.spring.jpa.services.file;

import com.spring.jpa.controller.FileDownloaderController;
import com.spring.jpa.services.FileWorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileWorker
{
    private static final Logger logger = LoggerFactory.getLogger(FileDownloaderController.class);

    public File downloadFileSpringResources(HttpServletResponse response){

        File file = new File("C:\\Users\\А\\IdeaProjects\\springRest\\src\\main\\resources\\download\\test.txt");
        logger.info("starting download by SpringResources from {}", file.getAbsolutePath());

        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));


        File copyFile = new File("C:\\Users\\А\\IdeaProjects\\springRest\\src\\main\\resources\\upload\\test.txt");
        logger.info("starting download by SpringResources to {}", copyFile.getAbsolutePath());
        if (!copyFile.exists()){
            try{
                copyFile.createNewFile();
            }
            catch (IOException e){
                logger.error("can`t make file");
            }
        }

        try
        {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(copyFile));
            FileCopyUtils.copy(new FileSystemResource(file).getInputStream(), outputStream);
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException e){
            logger.error("no file");
        }

        return copyFile;
    }

    public HttpServletResponse downloadFileHttpServletResponse(HttpServletResponse response)
    {
        logger.info("begin download in download by httpservlet");
        try
        {
            File file = new File("C:\\Users\\А\\IdeaProjects\\springRest\\src\\main\\resources\\download\\test.txt");

            logger.info("file name {}", file.getAbsolutePath());
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            response.addHeader("Content-disposition", "attachment;filename="+file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setContentType("txt/plain");

            FileCopyUtils.copy(inputStream, response.getOutputStream());

            response.flushBuffer();
            inputStream.close();
        }
        catch (IOException e)
        {
            logger.error("can`t load file");
        }
        return response;
    }

    public String uploadFile(HttpServletRequest request){
        File uploadedFile = new File ("C:\\Users\\А\\IdeaProjects\\springRest\\src\\main\\resources\\upload\\test.txt");
        //File sourceFile = new File("C:\\Users\\А\\IdeaProjects\\springRest\\src\\main\\resources\\download\\test.txt");

        String result = null;
        try
        {
            logger.info("begin upload");
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
            FileCopyUtils.copy(request.getInputStream(), outputStream);

            outputStream.close();
            result = "file uploaded";
        }
        catch (IOException e){
            logger.error("can`t upload file");
            result = "file didn`t upload";
        }
        return result;
    }
}
