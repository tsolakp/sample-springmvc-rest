package com.tsolakp.samplespringmvcrest.api.user;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserResourceIntegrationTest {
    
    @Autowired
    private UserResource userResource = null;

    @Test
    public void testCRUD(){
        //start with empty database
        List<User> users = userResource.getUsers();        
        assertTrue( users.isEmpty() );
        
        //delete not existing user
        ResponseEntity<?> deleteReponse = userResource.deleteUser("user2");
        assertEquals( HttpStatus.NOT_FOUND, deleteReponse.getStatusCode() );
        users = userResource.getUsers();        
        assertTrue( users.isEmpty() );
        
        //add one user
        User user1 = createUser( "user1", "user@eq.com", new Date() );
        userResource.registerUser(user1);
        
        //get that user and makes sure it is same as what we added previously
        users = userResource.getUsers();   
        assertEquals( 1, users.size() );
        assertEqualsUser( user1,users.get(0) );
        
        //delete non existing user
        deleteReponse = userResource.deleteUser("user2");
        assertEquals( HttpStatus.NOT_FOUND, deleteReponse.getStatusCode() );
        
        //make sure it did not delete the existing user
        users = userResource.getUsers();   
        assertEquals( 1, users.size() );
        assertEqualsUser( user1,users.get(0) );
        
        //add another user
        User user2 = createUser( "user2", "user@eq.com", new Date() );
        userResource.registerUser(user2);
        
        //get users and makes sure it is same as what we added previously
        //user are sorted by username in ASC order
        users = userResource.getUsers();   
        assertEquals( 2, users.size() );
        assertEqualsUser( user1, users.get(0) );
        assertEqualsUser( user2, users.get(1) );
        
        //delete existing user
        deleteReponse = userResource.deleteUser("user1");
        assertEquals( HttpStatus.NO_CONTENT, deleteReponse.getStatusCode() );
        
        //make sure it only deleted the user1
        users = userResource.getUsers();   
        assertEquals( 1, users.size() );
        assertEqualsUser( user2,users.get(0) );
    }
    
    private void assertEqualsUser(User user1, User user2){
        assertEquals( user1.getUserName(), user2.getUserName() );
        assertEquals( user1.getEmail(), user2.getEmail() );
        assertEquals( user1.getRegistrationDate(), user2.getRegistrationDate() );
    }
    
    private User createUser(String userName, String email, Date registrationDate){
        User result = new User();
        result.setUserName(userName);
        result.setEmail(email);
        result.setRegistrationDate(registrationDate);
        return result;
    }
}
