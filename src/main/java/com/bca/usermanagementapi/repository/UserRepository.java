package com.bca.usermanagementapi.repository;

import com.bca.usermanagementapi.model.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
}
