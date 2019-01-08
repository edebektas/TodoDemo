/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.services;

import com.google.gson.Gson;
import com.local.todoserviceside.impl.GeneralImpl;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laughmare
 */
public class AddTodoItemService extends HttpServlet{
    
    GeneralImpl service = new GeneralImpl(); 
    
    Integer listid;
    String name;
    String detail;
    Date dueDate;
    String dependantTodoId;
    Gson gson = new Gson();
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res)    
      throws ServletException,IOException { 
        res.setContentType("application/json; charSet=UTF-8");
        Enumeration en=req.getParameterNames();
        listid = null;
        name = null;
        detail = null;
        dueDate = null;
        dependantTodoId = null;
        while(en.hasMoreElements()) 
        { 
            Object obj=en.nextElement(); 
            String param=(String)obj; 
            String pvalue=req.getParameter(param);
            if(((String)param).equals("listid")){   
                if(pvalue != null){
                    listid = Integer.parseInt(pvalue);
                }
            }
            if(((String)param).equals("name")){   
                if(pvalue != null){
                    name = pvalue;
                }
            }
            if(((String)param).equals("detail")){   
                if(pvalue != null){
                    detail = pvalue;
                }
            }
            if(((String)param).equals("dueDate")){   
                if(pvalue != null){
                    try {
                        dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(pvalue);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddTodoItemService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(((String)param).equals("dependantTodoId")){   
                if(pvalue != null){
                    dependantTodoId = pvalue;
                }
            }
        }
        
        if(res.getStatus() == HttpServletResponse.SC_OK){
            res.getWriter().write(gson.toJson(service.addToDo(listid, name, detail, dueDate, dependantTodoId)));
        }else{
            res.getWriter().write(gson.toJson("fail"));
        }
    }
    
    
}
