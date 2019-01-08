/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.impl;

import com.local.todoserviceside.tables.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Laughmare
 */
public class UserImpl extends BaseImpl{
      
      public void persist(User user){
        createem(); 
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        closeem();
      }
      
      public void merge(User user){
        createem(); 
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        closeem();
      }
      
      public void delete(Integer id){
        User user = findById(id);
        createem(); 
        em.getTransaction().begin();
        if (!em.contains(user)) {
                user = em.merge(user);
            }
        em.remove(user);
        em.getTransaction().commit();
        closeem();
      }
      
      public User findById(Integer id){
        createem(); 
        User user = em.find(User.class, id);
        closeem();
        return user;
      }
      
      public List<User> findAll(){
        List<User> list;
        createem();
        try{
            Query q = em.createNamedQuery("User.findAll");
            list = (List<User>) q.getResultList();
        }catch(NoResultException e){
            list = new ArrayList<User>();
        }
        closeem();
        return list;
      }
      
      public User findByName(String name){
        User user;
        createem();
        try{
            Query q = em.createNamedQuery("User.findByName").setParameter("name", name);
            user = (User) q.getSingleResult();
        }catch(NoResultException e){
            user = new User();
        }
        closeem();
        return user;
      }
}
