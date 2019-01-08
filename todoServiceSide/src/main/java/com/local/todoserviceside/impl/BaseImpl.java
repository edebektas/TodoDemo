/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Laughmare
 */
public class BaseImpl {
     EntityManager em;
     EntityManagerFactory emfactory;
     
    public void createem(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "com.local_todoServiceSide_war_1.0-SNAPSHOTPU" );
        em = emfactory.createEntityManager();
    }
    
    public void closeem(){
        try{
            
        if(em != null){
            em.close();
        }
        if(emfactory != null){
            emfactory.close();
        }
        }catch(Exception e){
            
        }
    }
}
