package com.example.RestAndDatabase;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception{
        String uri = request.getRequestURI();
        System.out.println("Session ID: " + request.getSession().getId());
        if(uri.endsWith("login")){

            if(!request.getSession().isNew()){
                String token = (String) request.getSession().getAttribute("token");
                System.out.println(token);

                String email = request.getParameter("email");

                if(token!=null && token.equals("randomtoken"+email)){
                    System.out.println("Valid Request");
                }
                else if(token == null){
                    request.getSession().invalidate();
                }
                else{
                    System.out.println("Invalid Request! Token Mismatch!");
                    request.setAttribute("IsValidRequest","false");
                }
            }
        }
        else if(uri.endsWith("logout")){
            if(request.getSession().isNew()){
                request.getSession().invalidate();
                System.out.println("User not logged in!!");
            }
            else{
                String email = request.getParameter("email");
                System.out.println("Session token: " + request.getSession().getAttribute("token"));
                String token = "randomtoken" + email;
                if(!token.equals(request.getSession().getAttribute("token"))){
                    System.out.println("Invalid Request");
                    return false;
                }
                else{
                    System.out.println("Valid Log Out Request");
                }
            }
        }
        return true;
    }

}