package com.tsolakp.samplespringmvcrest.api.user;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    
    @NotEmpty
    @JsonProperty("username")
    @Column( name="username" )
    private String userName = null;
    @NotEmpty
    private String email = null;
    
    @NotNull  
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="UTC")  
    private Date registrationDate = null;
        
    public Long getId(){
        return id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public Date getRegistrationDate(){
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate){
        this.registrationDate = registrationDate;
    }
}
