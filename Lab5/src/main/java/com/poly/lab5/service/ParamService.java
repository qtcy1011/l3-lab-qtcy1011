package com.poly.lab5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value != null ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        String value = getString(name, null);
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        String value = getString(name, null);
        try {
            return value != null ? Double.parseDouble(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = getString(name, null);
        if (value != null) {
            return value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("on");
        }
        return defaultValue;
    }

    public Date getDate(String name, String pattern) {
        String value = getString(name, null);
        if (value == null) {
            return null;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(false);
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Lỗi sai định dạng thời gian (" + pattern + "): " + e.getMessage());
        }
    }

    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
 
            String realPath = request.getServletContext().getRealPath(path);
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
        }
    }
}