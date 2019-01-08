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
public class GetDependableTodoListService extends HttpServlet{
    
    GeneralImpl service = new GeneralImpl(); 
    
    Integer listId;
    String todo;
    Gson gson = new Gson();
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)    
      throws ServletException,IOException { 
        res.setContentType("application/json; charSet=UTF-8");
        Enumeration en=req.getParameterNames();
        listId = null;
        todo = null;
        while(en.hasMoreElements()) 
        { 
            Object obj=en.nextElement(); 
            String param=(String)obj; 
            String pvalue=req.getParameter(param);
             if(((String)param).equals("listId")){   
                if(pvalue != null){
                    listId = Integer.parseInt(pvalue);
                }
            }
             if(((String)param).equals("todo")){   
                if(pvalue != null){
                    todo = pvalue;
                }
            }
        }
        
        if(res.getStatus() == HttpServletResponse.SC_OK){
            if(listId == null){
                res.getWriter().write(gson.toJson("List id cant be empty"));               
            }else if(todo == null){
                res.getWriter().write(gson.toJson("Todo cant be empty"));
            }else{
                res.getWriter().write(gson.toJson(service.findDependableTodoList(listId, todo)));
            }
        }else{
            res.getWriter().write(gson.toJson("fail"));
        }
    }
    
}
