/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.impl;

import com.local.todoserviceside.tables.Todo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Laughmare
 */
public class TodoImpl extends BaseImpl{
      
    public void persist(Todo todo){
        createem();
        em.getTransaction().begin();
        em.persist(todo);
        em.getTransaction().commit();
        closeem();
    }

    public void merge(Todo todo){
        createem();
        em.getTransaction().begin();
        em.merge(todo);
        em.getTransaction().commit();
        closeem();
    }
      
    public void delete(Integer id){
        Todo todo = findById(id);
        createem();
        em.getTransaction().begin();
        if (!em.contains(todo)) {
                todo = em.merge(todo);
            }
        em.remove(todo);
        em.getTransaction().commit();
        closeem();
    }

    public Todo findById(Integer id){
        createem();
        Todo todo = em.find(Todo.class, id);
        closeem();
        return todo;
    }

    public List<Todo> findAll(){
        List<Todo> list;
        createem();
        try{
            Query q = em.createNamedQuery("Todo.findAll");
            list = (List<Todo>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<>();
        } 
        closeem();
        return list;
    }

    public List<Todo> findByListId(Integer listId){
        List<Todo> list;
        createem();
        try{
            Query q = em.createNamedQuery("Todo.findByListId").setParameter("listId", listId);
            list = (List<Todo>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<>();
        } 
        closeem();
        return list;
    }

    public List<Todo> findDependableTodoList(Integer listId, String todo){
        List<Todo> list;
        createem();
        try{
            Query q = em.createNamedQuery("Todo.findDependableTodoList").setParameter("listId", listId);
            q.setParameter("todoId", todo);
            list = (List<Todo>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<>();
        } 
        closeem();
        return list;
    }

    public Todo findByNameAndListId(String name, Integer listid){
        Todo todo;
        createem();
        try{
            Query q = em.createNamedQuery("Todo.findByNameAndListId");
            q.setParameter("listId", listid);
            q.setParameter("name", name);
            todo = (Todo) q.getSingleResult();
        }catch(NoResultException e){
            todo = new Todo();
        } 
        closeem();
        return todo;
    }

    public void deleteBylistId(Integer listId){
        List<Todo> list = findByListId(listId);
        for(Todo todo : list){
            delete(todo.getId());
        }
    }
    
    public List<Todo> findExpired(){
        List<Todo> list;
        createem();
        try{
            Query q = em.createNamedQuery("Todo.findExpired");
            list = (List<Todo>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<>();
        } 
        closeem();
        return list;
    }

}
