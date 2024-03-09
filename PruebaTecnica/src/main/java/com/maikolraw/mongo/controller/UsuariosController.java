package com.maikolraw.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maikolraw.mongo.documentos.Users;
import com.maikolraw.mongo.repository.UserRepository;



@RestController
@RequestMapping("/Users")


public class UsuariosController {
    
    @Autowired
    private UserRepository userRepository;
    
    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> createUsers(@RequestBody Users user) {
        try {
            Users savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
    @PutMapping
    public ResponseEntity<?> updateUsers(@RequestBody Users user) {
        try {
            Users savedUser = userRepository.save(user);
            return new ResponseEntity<Users>(savedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @DeleteMapping(value="/{userId}")
    public ResponseEntity<?> deletUsers(@PathVariable("userId") Integer userId) {
        try {
        	userRepository.deleteById(userId);	
            return new ResponseEntity<>("Fue eliminado "+userId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    



}