package com.tsolakp.samplespringmvcrest.api.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Long deleteByUserName(String userName);
}
