/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.beans;

import com.local.todoserviceside.impl.UserImpl;
import com.local.todoserviceside.tables.User;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Laughmare
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {
    UserImpl impl = new UserImpl();
    public String a;
    public String b;
    public List<User> list;
    @PostConstruct
    public void initialize(){
        list = impl.findAll();
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
    
    public void click(){
        User user = new User();
        user.setName(a);
        user.setPassword(b);
        user.setCrtDate(new Date());
        impl.persist(user);
        initialize();
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
    
    
}
