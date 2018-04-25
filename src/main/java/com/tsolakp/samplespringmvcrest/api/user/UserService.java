package com.tsolakp.samplespringmvcrest.api.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository = null;
    
    public List<User> getUsers(){
        return userRepository.findAll( new Sort(Sort.Direction.ASC, "userName") );
    }
    
    public User saveUser(User user){
        return userRepository.save(user);
    }
    
    public Long deleteUser(String userName){
        return userRepository.deleteByUserName(userName);
    }
}
