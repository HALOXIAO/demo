package com.web.demo.Controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class Login {

    private RequestCache requestCache = new HttpSessionRequestCache();//用来保存请求信息（为了重定向后保存信息）
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();//封装后的重定向

    @GetMapping(value = "/login")
    public String login(){
        return "Views/Login/login";
    }

    @GetMapping(value = "/requirement")
    @ResponseBody
    public String requirement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("hello world");
        SavedRequest savedRequest = requestCache.getRequest(request, response); //返回已保存的缓存
        /*getRequest源码所使用的是HttpSession里的getAttribute，request获取URL是可以获得不带.html的目标URL的，但是不清楚为什么一定要用Session来获取，可能是因为跳转之类的？*/
//    SaveRequest：用于保存请求缓存，在重定向后能够再现原始数据，通常用于表单登录的身份验证
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();//目标地址
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) { //测试去除大小写后，redirectUrl是否具有.html后缀

                redirectStrategy.sendRedirect(request, response, "/login");
            }
        }
        return "404";
    }
}
