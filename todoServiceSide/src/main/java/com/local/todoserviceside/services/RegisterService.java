package com.local.todoserviceside.services;

import com.google.gson.Gson;
import com.local.todoserviceside.impl.GeneralImpl;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterService extends HttpServlet{
    
    GeneralImpl service = new GeneralImpl(); 

    String name;
    String password;
    String confPassword;
    Gson gson = new Gson();
        
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)    
      throws ServletException,IOException { 
        res.setContentType("application/json; charSet=UTF-8");
        Enumeration en=req.getParameterNames();
        name = null;
        password = null;
        confPassword = null;
        while(en.hasMoreElements()) 
        { 
            Object obj=en.nextElement(); 
            String param=(String)obj; 
            String pvalue=req.getParameter(param);
            if(((String)param).equals("name")){
                if(pvalue != null){
                    name = pvalue;
                }
            }else if(((String)param).equals("password")){   
                if(pvalue != null){
                    password = pvalue;
                }
            }else if(((String)param).equals("confPassword")){   
                if(pvalue != null){
                    confPassword = pvalue;
                }
            }
        }
        
        if(res.getStatus() == HttpServletResponse.SC_OK){
            res.getWriter().write(gson.toJson(service.register(name, password, confPassword)));
        }else{
            res.getWriter().write(gson.toJson("fail"));
        }
    }
}