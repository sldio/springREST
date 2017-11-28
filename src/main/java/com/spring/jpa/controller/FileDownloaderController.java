package com.spring.jpa.controller;


import com.spring.jpa.services.FileWorkerService;
import com.spring.jpa.services.file.FileWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
public class FileDownloaderController implements FileWorkerService
{
    @Override
    @GetMapping(value = "/downloadHttp")
    public @ResponseBody void downloadResponse(HttpServletResponse response)
    {
        new FileWorker().downloadFileHttpServletResponse(response);
    }

    @Override
    @GetMapping(value = "/downloadSpring")
    public @ResponseBody Resource downloadSpring(HttpServletResponse response)  {

        File file = new FileWorker().downloadFileSpringResources(response);

        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));

        return new FileSystemResource(file);
    }

    @Override
    @PostMapping(value = "/upload")
    public @ResponseBody String upload(HttpServletRequest request){

        return new FileWorker().uploadFile(request);
    }
}
