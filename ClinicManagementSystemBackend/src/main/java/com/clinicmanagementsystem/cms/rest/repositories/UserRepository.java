package com.clinicmanagementsystem.cms.rest.repositories;

import com.clinicmanagementsystem.cms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path="user" )
public interface UserRepository extends MongoRepository<User, String>{
    public User findByUsername(String username);
    public User findByEmail(String email);

}
