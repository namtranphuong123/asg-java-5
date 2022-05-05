package edu.poly.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class cookieService {
    @Autowired
    HttpServletRequest req;
    @Autowired
    HttpServletResponse resp;

    
    
    
  
    
    
    public Cookie get(String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) return cookie;
            }
            return null;
        }
        return null;
    }
    public String getValue(String name){
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) return cookie.getValue();
            }
            return "";
        }
        return "";
    }
    public Cookie add(String name , String value , int hours){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(hours*60*60);
        cookie.setPath("/");
        return cookie;
    }
    public void  remove(String name){
        Cookie cookie = new Cookie(name,"");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
