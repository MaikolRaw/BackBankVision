package com.maikolraw.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maikolraw.mongo.documentos.LoginUser;


public interface LoginRepository extends MongoRepository<LoginUser, Integer>{
	
	// Método para buscar un usuario por su número de identificación
	LoginUser findByUserId(Integer userId);


}
	