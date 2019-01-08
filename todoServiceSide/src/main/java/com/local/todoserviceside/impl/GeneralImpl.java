/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.impl;

import com.local.todoserviceside.tables.Todo;
import com.local.todoserviceside.tables.Todolist;
import com.local.todoserviceside.tables.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Laughmare
 */
public class GeneralImpl {
    UserImpl uImpl = new UserImpl();
    TodoImpl tImpl = new TodoImpl();
    TodolistImpl tlImpl = new TodolistImpl();
    
    public String login(String name, String password){
        if(name == null || name.isEmpty()){
            return "Error : Username cant be empty";
        }else if(password == null || password.isEmpty()){
            return "Error : Password cant be empty";
        }else if(uImpl.findByName(name).getId() != null){
            if(password.equals(uImpl.findByName(name).getPassword())){
                return uImpl.findByName(name).getId().toString();
            }else{
                return "Error : Check password or username";
            }
        }else{
            return "Error : Check password or username";
        }
    }
    
    public String register(String name, String password, String confPassword){
        if(name == null || name.isEmpty()){
            return "Username cant be empty";
        }else if(password == null || password.isEmpty()){
            return "Password cant be empty";
        }else if(confPassword == null || confPassword.isEmpty()){
            return "Confirm Password cant be empty";
        }else if(!password.equals(confPassword)){
            return "Passwords dont match";
        }else if(uImpl.findByName(name).getId() == null){
            User user = new User();
            user.setCrtDate(new Date());
            user.setName(name);
            user.setPassword(password);
            uImpl.persist(user);
            return "success";
        }else{
            return "User already exists";
        }
    }
    
    public String addToDoList(Integer userId, String name){
        if(name == null || name.isEmpty()){
            return "List name cant be empty";
        }else if(userId == null){
            return "User id cant be empty";
        }else if(uImpl.findById(userId) == null){
            return "User not found";
        }else if(tlImpl.findByNameAndUser(name, userId).getId() != null){
            return "List with given name already exists";
        }else{
            Todolist todoList = new Todolist();
            todoList.setCrtDate(new Date());
            todoList.setName(name);
            User user = uImpl.findById(userId);
            todoList.setOwnerId(user.getId());
            tlImpl.persist(todoList);
            return "success";
        }
    }
    
    public String updateToDoList(Integer id, String name){
        if(name == null || name.isEmpty()){
            return "List name cant be empty";
        }else if(id == null){
            return "List id cant be empty";
        }else if(tlImpl.findById(id) == null){
            return "List not found";
        }else if(tlImpl.findByNameAndUser(name, tlImpl.findById(id).getOwnerId()).getId() != null && !tlImpl.findById(id).getName().equals(name)){
            return "List with given name already exists";
        }else{
            Todolist todoList = tlImpl.findById(id);
            todoList.setName(name);
            tlImpl.merge(todoList);
            return "success";
        }
    }
    
    public String deleteToDoList(Integer id){
        if(id == null){
            return "List id cant be empty";
        }else if(tlImpl.findById(id) == null){
            return "List not found";
        }else{
            tlImpl.delete(id);
            tImpl.deleteBylistId(id);
            return "success";
        }
    }
    
    public List<Todolist> getTodolistListByOwner(Integer userId){
        return tlImpl.findByOwnerId(userId);
    }
    
       
    public String addToDo(Integer listId, String name, String detail, Date dueDate, String dependantTodoId){
        if(listId == null){
            return "List cant be empty";
        }else if(name == null || name.isEmpty()){
            return "Name cant be empty";
        }else if(detail == null || detail.isEmpty()){
            return "Detail cant be empty";
        }else if(dueDate == null){
            return "Invalid due date";
        }else if(dueDate != null && dueDate.before(new Date())){
            return "Due date cant be set to past";
        }else if(tImpl.findByNameAndListId(name, listId).getId() != null){
            return "Todo item with given name already exists";
        }else{
            Todo todo = new Todo();
            todo.setCrtdate(new Date());
            todo.setDueDate(dueDate);
            todo.setName(name);
            todo.setDetail(detail);
            todo.setListId(listId);
            todo.setStatus("Uncompleted");
            if(dependantTodoId != null && !dependantTodoId.isEmpty()){
                if(tImpl.findByNameAndListId(dependantTodoId, listId).getId() != null){
                    todo.setDependedTodoId(dependantTodoId);
                }else{
                    return "Todo item with given depended todo name not found";
                }
            }
            tImpl.persist(todo);
            return "success";
        }
    }
       
    public String updateToDo(Integer id, String name, String detail, Date dueDate, String dependantTodoId){
        Todo upd = tImpl.findById(id);
        if(id == null){
            return "Id cant be empty";
        }else if(name == null || name.isEmpty()){
            return "Name cant be empty";
        }else if(detail == null || detail.isEmpty()){
            return "Detail cant be empty";
        }else if(dueDate == null){
            return "Invalid due date";
        }else if(tImpl.findById(id) == null){
            return "Todo item not found";
        }else if(tImpl.findByNameAndListId(name, tImpl.findById(id).getListId()).getId() != null && !upd.getName().equals(name)){
            return "Todo item with given name already exists";
        }else if(dueDate != null && dueDate.before(new Date())){
            return "Due date cant be set to past";
        }else if(upd.getDueDate()!= null && upd.getDueDate().before(new Date())){
            upd.setStatus("Expired");
            tImpl.merge(upd);
            return "Todo item is expired";
        }else if(!tImpl.findById(id).getStatus().equals("Uncompleted")){
            return "Only uncompleted todo items can be updated";
        }else{
            Todo todo = tImpl.findById(id);
            Todo depended = tImpl.findByNameAndListId(dependantTodoId, todo.getListId());
            if(dependantTodoId != null && !dependantTodoId.isEmpty()){
                if(dependantTodoId.equals(todo.getName())){
                    return "Depended todo item cant be same with to be updated todo item";
                }else if(depended.getId() != null){
                    if(depended.getDependedTodoId() != null && depended.getDependedTodoId().equals(todo.getName())){
                        return "Todo item that you want to bind is already binded to the item that you want to update";
                    }else{
                        todo.setDependedTodoId(dependantTodoId);
                    }
                }else{
                    return "Todo item with given depended todo name not found";
                }
            }
            todo.setName(name);
            todo.setDetail(detail);
            todo.setDueDate(dueDate);
            tImpl.merge(todo);
            return "success";
        }
    }
    
    public String deleteToDo(Integer id){
        if(id == null){
            return "Id cant be empty";
        }else if(tImpl.findById(id) == null){
            return "Todo item not found";
        }else{
            tImpl.delete(id);
            return "success";
        }
    }
    
    public List<Todo> getTodosByTodoList(Integer listId){
        updateToExpired();
        return tImpl.findByListId(listId);
    }
    
    public List<Todo> findDependableTodoList(Integer listId, String todo){
        return tImpl.findDependableTodoList(listId, todo);
    }
    
    public void updateToExpired(){
        List<Todo> list = tImpl.findExpired();
        for(Todo todo : list){
            todo.setStatus("Expired");
            tImpl.merge(todo);
        }
    }
    
    public String completeToDo(Integer id){
        Todo todo = tImpl.findById(id);
        if(id == null){
            return "Id cant be empty";
        }else if(todo == null){
            return "Todo item not found";
        }else if(todo.getStatus() != null && todo.getStatus().equals("Expired")){
            return "Todo item is expired";
        }else if(todo.getStatus() != null && todo.getStatus().equals("Completed")){
            return "Todo item is already completed";
        }else if(todo.getDueDate()!= null && todo.getDueDate().before(new Date())){
            todo.setStatus("Expired");
            tImpl.merge(todo);
            return "Todo item is expired";
        }else{
            if(todo.getDependedTodoId() != null && !todo.getDependedTodoId().isEmpty()){
                Todo depended = tImpl.findByNameAndListId(todo.getDependedTodoId() , todo.getListId());
                if(!"Completed".equals(depended.getStatus()))
                    return "Cant complete until depended todo item is completed";
            }
            todo.setStatus("Completed");
            tImpl.merge(todo);
            return "success";
        }
    }
    
}
