/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.local.todoserviceside.impl.GeneralImpl;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Laughmare
 */
public class RegisterTest {
    GeneralImpl test = new GeneralImpl();
    
    public RegisterTest() {
    }
    
    @Test
    public void hello() {
        assertEquals("Username cant be empty", test.register(null, "a", "a"));
        assertEquals("Password cant be empty", test.register("a", null, "a"));
        assertEquals("Confirm Password cant be empty", test.register("a", "a", null));
        assertEquals("Passwords dont match", test.register("a","a","b"));
    }
}
