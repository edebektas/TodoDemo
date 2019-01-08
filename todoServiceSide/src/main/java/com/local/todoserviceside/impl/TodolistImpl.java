/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.impl;

import com.local.todoserviceside.tables.Todolist;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Laughmare
 */
public class TodolistImpl extends BaseImpl{
      
    public void persist(Todolist todolist){
        createem();
        em.getTransaction().begin();
        em.persist(todolist);
        em.getTransaction().commit();
        closeem();
    }

    public void merge(Todolist todolist){
        createem();
        em.getTransaction().begin();
        em.merge(todolist);
        em.getTransaction().commit();
        closeem();
    }
      
    public void delete(Integer id){
        Todolist todolist = findById(id);
        createem();
        em.getTransaction().begin();
        if (!em.contains(todolist)) {
                todolist = em.merge(todolist);
            }
        em.remove(todolist);
        em.getTransaction().commit();
        closeem();
    }

    public Todolist findById(Integer id){
        createem();
        Todolist todoList = em.find(Todolist.class, id);
        closeem();
        return todoList;
    }

    public List<Todolist> findAll(){
        List<Todolist> list;
        createem();
        try{
            Query q = em.createNamedQuery("Todolist.findAll");
            list = (List<Todolist>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<Todolist>();
        } 
        closeem();
        return list;
    }
    
    public List<Todolist> findByOwnerId(Integer id){
        List<Todolist> list;
        createem();
        try{
            Query q = em.createNamedQuery("Todolist.findByOwnerId").setParameter("ownerId", id);
            list = (List<Todolist>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<Todolist>();
        } 
        closeem();
        return list;
    }
      
    public Todolist findByNameAndUser(String name, Integer userId){
        Todolist todolist;
        createem();
        try{
            Query q = em.createNamedQuery("Todolist.findByNameAndUser").setParameter("name", name);
            q.setParameter("userId", userId);
            todolist = (Todolist) q.getSingleResult();
        }catch(NoResultException e){
            todolist = new Todolist();
        }
        closeem();
        return todolist;
    }

}
