package com.example.crudbackend.controller;

import com.example.crudbackend.entity.User;
import com.example.crudbackend.exception.ResourceNotFoundException;
import com.example.crudbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

  /* @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable String id){

        return userRepository.findById(id);
    }
    */

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") String id)
          throws ResourceNotFoundException {
      User user = userRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
      return ResponseEntity.ok().body(user);
  }


 /*   @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable String id){
        userRepository.deleteById(id);
        return true;
    }

*/
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

  /*  @PutMapping("/edit")
    public User updateUser(User user){
        return userRepository.save(user);
    }

*/

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        user.setEmail(userDetails.getEmail());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }


    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user){
            return userRepository.save(user);
        }

}
