/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.services;

import com.google.gson.Gson;
import com.local.todoserviceside.impl.GeneralImpl;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laughmare
 */
public class GetTodolistsByOwnerService extends HttpServlet{
    
    GeneralImpl service = new GeneralImpl(); 
    
    Integer userId;
    Gson gson = new Gson();
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)    
      throws ServletException,IOException { 
        res.setContentType("application/json; charSet=UTF-8");
        Enumeration en=req.getParameterNames();
        userId = null;
        while(en.hasMoreElements()) 
        { 
            Object obj=en.nextElement(); 
            String param=(String)obj; 
            String pvalue=req.getParameter(param);
             if(((String)param).equals("userId")){   
                if(pvalue != null){
                    userId = Integer.parseInt(pvalue);
                }
            }
        }
        
        if(res.getStatus() == HttpServletResponse.SC_OK){
            if(userId != null){
                res.getWriter().write(gson.toJson(service.getTodolistListByOwner(userId)));               
            }else{
                res.getWriter().write(gson.toJson("User id cant be empty"));               
                
            }
        }else{
            res.getWriter().write(gson.toJson("fail"));
        }
    }
}
