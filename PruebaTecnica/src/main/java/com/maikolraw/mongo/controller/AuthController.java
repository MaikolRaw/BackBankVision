package com.maikolraw.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maikolraw.mongo.documentos.LoginUser;
import com.maikolraw.mongo.documentos.Users;
import com.maikolraw.mongo.repository.LoginRepository;
import com.maikolraw.mongo.repository.UserRepository;
	
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginRepository loginRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<Boolean> authenticateUser(@RequestBody LoginUser loginUser) {
        // Imprimir los valores recibidos en la consola
        System.out.println("Identification Number: " + loginUser.getUserId());
        System.out.println("Password: " + loginUser.getPassword());
        
        // Buscar el usuario en la base de datos por su número de identificación
        LoginUser user = loginRepository.findByUserId(loginUser.getUserId());

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            // Si las credenciales coinciden, la autenticación es exitosa
            return ResponseEntity.ok(true);
        } else {
            // Si las credenciales no coinciden, la autenticación falla
            return ResponseEntity.ok(false);
        }
    }

    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody LoginUser newUser) {
        try {
            // Verificar si el usuario ya existe en la base de datos
            LoginUser existingUser = loginRepository.findByUserId(newUser.getUserId());

            if (existingUser != null) {
                // Si el usuario ya existe, devolver false
                return ResponseEntity.ok(false);
            }

            // Guardar el nuevo usuario en la colección de Login
            loginRepository.save(newUser);

            // Devolver true para indicar que el usuario se registró con éxito
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            // Si ocurre algún error durante el registro, devolver false
            return ResponseEntity.ok(false);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public ResponseEntity<List<LoginUser>> getAllUsers() {
        try {
            // Obtener todos los usuarios de la colección de Login
            List<LoginUser> users = loginRepository.findAll();
            
            // Devolver la lista de usuarios en la respuesta HTTP
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            // Si ocurre algún error, devolver una respuesta de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value="{userId}")
    public boolean deleteLoginUser(@PathVariable int userId) {
        try {
        	loginRepository.deleteById(userId);
            return true; // Devuelve true si se elimina correctamente
        } catch (Exception e) {
            return false; // Devuelve false si no se puede eliminar
        }
    }
    
    
    
}
