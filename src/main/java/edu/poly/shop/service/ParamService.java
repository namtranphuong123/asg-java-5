package edu.poly.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest req;
    @Autowired
    ServletContext app;

    public String getString(String name, String defaultValue) {
        String a = req.getParameter(name);
        if (a.equals("") || a == null) {
            return defaultValue;
        } else {
            return a;
        }
    }

    public int getInt(String name, int defaultValue) {
        try {
            int a = Integer.parseInt(req.getParameter(name));
            return a;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        try {
            double a = Double.parseDouble(req.getParameter(name));
            return a;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        try {
            Boolean a = Boolean.parseBoolean(req.getParameter(name));
            return a;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Date getDate(String name, String pattern) {
        if (name != null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date date = simpleDateFormat.parse(req.getParameter(name));
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return null;
    }

    public File save(MultipartFile photo, String path) {
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        try {
            String filename = photo.getOriginalFilename();
            File savefile = new File(dir,"/"+filename);
            photo.transferTo(savefile);
            return savefile;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
