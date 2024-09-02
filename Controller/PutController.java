package com.example.Mongo.Controller;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.example.Mongo.Controller.EmployeeController.UpdateManagerRequest;
//import com.example.Mongo.Models.Employee;
import com.example.Mongo.Service.PutService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/putcontroller")
public class PutController {

    @Autowired
    PutService putservice;
    @PutMapping("/put")

public ResponseEntity<Map<String, String>> updateEmployeeManagers(@RequestBody List<Map<String, Integer>> requestBody) {
    // Pass the list of employee-manager pairs to the service
    return putservice.updateEmployeeManagers(requestBody);
}
}
// package com.example.Mongo.Controller;
// import java.util.ArrayList;
// import java.util.List;

// //import com.example.Mongo.Controller.EmployeeController.UpdateManagerRequest;
// import com.example.Mongo.Models.Employee;
// import com.example.Mongo.Service.PutService;

// import org.springframework.beans.factory.annotation.Autowired;
// //import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// @RestController
// @RequestMapping("/putcontroller")
// public class PutController {

//     @Autowired
//     PutService putservice;
//     @PutMapping("/put")
//     public ResponseEntity<?> updateManager(@RequestBody List<UpdateManagerRequest> requests){
//         List<String> successMessages = new ArrayList<>();
//         List<String> errorMessages = new ArrayList<>();

//         for(UpdateManagerRequest request:requests){
//             if(request.getManagerId()==0){
//                 errorMessages.add("Can't change the designation of an associate to a manager for Employee with ID " + request.getEmployeeId());
//                 continue;
//             }

//         Employee updatedEmployee=putservice.updateManager(request.getEmployeeId(), request.getManagerId());


//             if(updatedEmployee==null){
//                  errorMessages.add("Employee with ID " + request.getEmployeeId() + " not found, update failed.");
//             //return ResponseEntity.badRequest().body("Employee not found ,so update failed");
//             }else{
//                  successMessages.add("Employee with ID " + request.getEmployeeId() + " manager updated successfully.");

//             //return ResponseEntity.ok("Employee's manager has been changed succesfully");
//             }
            
//        }
//         if (errorMessages.isEmpty()) {
//         return ResponseEntity.ok(successMessages);
//         } else {
//         return ResponseEntity.ok().body("Success: " + successMessages + " Errors: " + errorMessages);
//         }
        
//     }
//     public static class UpdateManagerRequest {
//         private Integer employeeId;
//         private Integer managerId;
    
//         public Integer getEmployeeId() {
//             return employeeId;
//         }
    
//         public void setEmployeeId(Integer employeeId) {
//             this.employeeId = employeeId;
//         }
    
//         public Integer getManagerId() {
//             return managerId;
//         }
    
//         public void setManagerId(Integer managerId) {
//             this.managerId = managerId;
//         }
//       }


    
// }
