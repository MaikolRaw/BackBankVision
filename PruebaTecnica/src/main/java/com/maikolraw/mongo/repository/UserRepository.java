package com.maikolraw.mongo.repository;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.maikolraw.mongo.documentos.Users;

public interface UserRepository extends MongoRepository<Users, Integer>{
	
	

}
