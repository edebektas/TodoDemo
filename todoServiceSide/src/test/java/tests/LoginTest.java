/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.local.todoserviceside.impl.GeneralImpl;
import com.local.todoserviceside.impl.UserImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laughmare
 */
public class LoginTest {
    
    GeneralImpl test = new GeneralImpl();
    UserImpl userImpl = new UserImpl();
    public LoginTest() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void test() {
        assertEquals("Error : Username cant be empty", test.login(null, null));
        assertEquals("Error : Password cant be empty", test.login("aaaa", null));
        assertEquals("Error : Username cant be empty", test.login(null, "aaaa"));
        assertEquals("Error : Check password or username", test.login("admin", "aaaa"));
        assertEquals("Error : Check password or username", test.login("aaaa", "admin"));
        assertEquals(userImpl.findByName("admin").getId().toString(), test.login("admin", "admin"));
    }
}
