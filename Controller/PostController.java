package com.example.Mongo.Controller;
import com.example.Mongo.Models.Employee;
//import com.example.Mongo.Service.EmployeeService;
import com.example.Mongo.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

//import jakarta.validation.Valid;

//import jakarta.validation.Valid;
@RestController
@RequestMapping("/postcontroller")
public class PostController {

     
    @Autowired
    private PostService postService;


// POST API to create an employee
@PostMapping("/post")
public ResponseEntity<?> createEmployee( @RequestBody List<Employee> employees) {
    //System.out.println("post controller is being called");

    String result = postService.createEmployee(employees);

    // Check if the result contains errors
    if (result.contains("Errors:")) {
        // Split the response into success and error parts
        String[] parts = result.split("\\| Errors:");
        List<String> successMessages = List.of(parts[0].trim());
        List<String> errorMessages = List.of(parts[1].trim());

        // Return success and error messages in the response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "success", successMessages,
            "errors", errorMessages
        ));
    }

    // If no errors, return a success message
    return ResponseEntity.ok("Successfully submitted: " + result);
}

}
