/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.local.todoserviceside.impl.GeneralImpl;
import com.local.todoserviceside.impl.TodolistImpl;
import com.local.todoserviceside.impl.UserImpl;
import com.local.todoserviceside.tables.Todolist;
import com.local.todoserviceside.tables.User;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Laughmare
 */
public class TodoListTest {
    
    GeneralImpl test = new GeneralImpl();
    TodolistImpl todoListImpl = new TodolistImpl();
    UserImpl userImpl = new UserImpl();
    User user;
    Todolist todolist;
    public TodoListTest() {
    }

    @Before 
    public void before(){
        user = userImpl.findByName("admin");
        try{   
            todolist = todoListImpl.findByNameAndUser("dummy", user.getId());
            if(todolist.getId() != null){
                todoListImpl.delete(todolist.getId());
            }
        }catch(Exception e){
            
        }
        try{   
            todolist = todoListImpl.findByNameAndUser("dummy2", user.getId());
            if(todolist.getId() != null){
                todoListImpl.delete(todolist.getId());
            }
        }catch(Exception e){
        }
    }
    
    @After 
    public void after(){
        try{   
            todolist = todoListImpl.findByNameAndUser("dummy", user.getId());
            if(todolist.getId() != null){
                todoListImpl.delete(todolist.getId());
            }
        }catch(Exception e){
            
        }
        try{   
            todolist = todoListImpl.findByNameAndUser("dummy2", user.getId());
            if(todolist.getId() != null){
                todoListImpl.delete(todolist.getId());
            }
        }catch(Exception e){
        }
    }
    
    @Test
    public void test() {
        assertEquals("success", test.addToDoList(user.getId(), "dummy"));
        assertEquals("success", test.addToDoList(user.getId(), "dummy2"));
        assertEquals("List with given name already exists", test.updateToDoList(todoListImpl.findByNameAndUser("dummy2", user.getId()).getId(), "dummy"));
        assertEquals("List not found", test.deleteToDoList(0));
        
    
    }
}
