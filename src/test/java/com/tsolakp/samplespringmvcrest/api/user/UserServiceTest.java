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
import org.springframework.data.domain.Sort;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository = null;
    
    private UserService userService = null;
    
    @Before
    public void before(){
        userService = new UserService();
        Whitebox.setInternalState(userService, "userRepository", userRepository);
    }
    
    @Test
    public void testGetUsers(){
        List<User> users = Arrays.asList( createUser( "user1", "user@eq.com", new Date() ) );        
        when( userRepository.findAll(  new Sort(Sort.Direction.ASC, "userName") ) ).thenReturn(users);
        
        List<User> result = userService.getUsers();
        
        assertSame(users, result);
    }
       
    
    @Test
    public void testSaveUser(){
        User user1 = createUser( "user1", "user@eq.com", new Date() );  
        
        when( userRepository.save(user1) ).thenReturn(user1);
        
        User result = userService.saveUser(user1);        
        assertSame(user1, result);
    }
    
    @Test
    public void testDeleteUser(){                
        userService.deleteUser("user1");                
        verify( userRepository ).deleteByUserName("user1");
    }
    
    private User createUser(String userName, String email, Date registrationDate){
        User result = new User();
        result.setUserName(userName);
        result.setEmail(email);
        result.setRegistrationDate(registrationDate);
        return result;
    }
}
