package com.spring.jpa.services;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface FileWorkerService
{
    Resource downloadSpring(HttpServletResponse response);
    public @ResponseBody void downloadResponse(HttpServletResponse response);
    public @ResponseBody String upload(HttpServletRequest request);
}
