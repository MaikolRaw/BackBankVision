	package com.maikolraw.mongo.controller;
	
	import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;

	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.maikolraw.mongo.documentos.Users;
import com.maikolraw.mongo.repository.LoginRepository;
import com.maikolraw.mongo.repository.UserRepository;

	
	
	@RestController
	@RequestMapping("/Users")
	
	
	public class UsuariosController {
	    
	    @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private LoginRepository loginRepository;
	    
	    // Crear un nuevo usuario
	    @CrossOrigin(origins = "http://localhost:4200")
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
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @GetMapping
	    public ResponseEntity<List<Users>> getAllUsers() {
	        List<Users> users = userRepository.findAll();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/update")
	    public ResponseEntity<?> updateUsers(@RequestBody Users newUser) {
	        try {
	            // Buscar el usuario por userId
	            Users existingUser = userRepository.findById(newUser.getUserId()).orElse(null);
	            
	            if(existingUser == null) {
	                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
	            }
	            
	         
	            existingUser.setIdentificationType(newUser.getIdentificationType());
	            existingUser.setDateOfBirth(newUser.getDateOfBirth());
	            existingUser.setPhoneNumber(newUser.getPhoneNumber());
	            existingUser.setEmail(newUser.getEmail());
	            
	          
	            Users savedUser = userRepository.save(existingUser);
	            
	            return new ResponseEntity<>(savedUser, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	   
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/perfilbyid")
	    public ResponseEntity<?> buscarPerfilPorId(@RequestBody Map<String, Integer> requestBody) {
	        try {
	            Integer userId = requestBody.get("userId");
	            if (userId == null) {
	                return new ResponseEntity<>("El cuerpo de la solicitud debe contener el campo 'userId'", HttpStatus.BAD_REQUEST);
	            }
	            
	            Optional<Users> userOptional = userRepository.findById(userId);
	            if (userOptional.isPresent()) {
	                Users user = userOptional.get();
	                return new ResponseEntity<>(user, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("No se encontró ningún usuario con el userId: " + userId, HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @DeleteMapping(value="/{userId}")
	    public boolean deleteUser(@PathVariable int userId) {
	        try {
	            userRepository.deleteById(userId);
	            return true; // Devuelve true si se elimina correctamente
	        } catch (Exception e) {
	            return false; // Devuelve false si no se puede eliminar
	        }
	    }
	    
	    
	    
	    

	    


    

    
    



}