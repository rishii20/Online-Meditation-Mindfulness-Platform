package com.mindful.controller;

import com.mindful.dao.ContentDAO;
import com.mindful.dao.ContentDAOImpl;
import com.mindful.model.Content;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@WebServlet("/instructor/upload")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=50*1024*1024, maxRequestSize=100*1024*1024)
public class UploadContentServlet extends HttpServlet {
    private ContentDAO dao = new ContentDAOImpl();
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("media");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String mediaType = req.getParameter("mediaType"); // audio/video/text

        String appPath = req.getServletContext().getRealPath("");
        File uploads = new File(appPath, UPLOAD_DIR);
        if(!uploads.exists()) uploads.mkdirs();

        String fileName = filePart.getSubmittedFileName();
        File file = new File(uploads, System.currentTimeMillis()+"_"+fileName);
        try (InputStream in = filePart.getInputStream()) {
            Files.copy(in, file.toPath());
        }

        // for demo, createdBy is read as param (in real use from session user)
        int createdBy = Integer.parseInt(req.getParameter("createdBy"));

        Content c = new Content();
        c.setTitle(title);
        c.setDescription(description);
        c.setMediaType(mediaType);
        c.setMediaUrl(UPLOAD_DIR + "/" + file.getName());
        c.setCreatedBy(createdBy);

        try {
            dao.save(c);
            resp.sendRedirect(req.getContextPath() + "/instructor/dashboard.jsp?msg=uploaded");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
