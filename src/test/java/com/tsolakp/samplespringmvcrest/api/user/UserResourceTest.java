/*
 * Copyright 2018, Charter Communications, All rights reserved.
 */
package com.tsolakp.samplespringmvcrest.api.user;

import java.util.List;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.powermock.reflect.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserResourceTest {

    @Mock
    private UserService userService = null;
    
    private UserResource userResource = null;
    
    @Before
    public void before(){
        userResource = new UserResource();
        Whitebox.setInternalState(userResource, "userService", userService);
    }
    
    @Test
    public void testGetUsers(){
        List<User> users = Arrays.asList( createUser( "user1", "user@eq.com", new Date() ) );        
        when( userService.getUsers() ).thenReturn(users);
        
        List<User> result = userResource.getUsers();
        
        assertSame(users, result);
    }
            
    @Test
    public void testRegisterUser(){
        User user1 = createUser( "user1", "user@eq.com", new Date() );  
        
        when( userService.saveUser(user1) ).thenReturn(user1);
        
        User result = userResource.registerUser(user1);        
        assertSame(user1, result);
    }
    
    @Test
    public void testDeleteUser(){                
        //delete with non existing user
        when( userService.deleteUser("user1") ).thenReturn(0L);        
        ResponseEntity<?> deleteResponse = userResource.deleteUser("user1");        
        assertEquals( HttpStatus.NOT_FOUND, deleteResponse.getStatusCode() ); 
        
        //delete with existing user
        when( userService.deleteUser("user1") ).thenReturn(1L);        
        deleteResponse = userResource.deleteUser("user1");        
        assertEquals( HttpStatus.NO_CONTENT, deleteResponse.getStatusCode() ); 
    }
    
    private User createUser(String userName, String email, Date registrationDate){
        User result = new User();
        result.setUserName(userName);
        result.setEmail(email);
        result.setRegistrationDate(registrationDate);
        return result;
    }
}
