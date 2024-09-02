package com.example.Mongo.Controller;




import com.example.Mongo.Service.GetService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getController")
public class GetController {

    @Autowired
    private GetService getService;

    @GetMapping("/get")
    public ResponseEntity<?> getEmployeeDetails(@RequestParam(defaultValue = "0")int managerId, @RequestParam(defaultValue = "-1")int yearsOfExperience) {
        // Pass the list of employee-manager pairs to the service
        return getService.getEmployeeDetails(managerId,yearsOfExperience);
    }
}
    // public ResponseEntity<Map<String, Object>> filterEmployees(
    //         @RequestParam(value = "managerIds", required = false) List<Integer> managerIds,
    //         @RequestParam(value = "yearsOfExperience", required = false) List<Integer> yearsOfExperience) {

    //     List<Map<String, Object>> results = new ArrayList<>();
    //     System.out.println(managerIds);
    //     if (managerIds != null && yearsOfExperience != null && managerIds.size() == yearsOfExperience.size()) {
    //         System.out.println("it is coming in ma and yoe");
    //         for (int i = 0; i < managerIds.size(); i++) {
    //             Integer managerId = managerIds.get(i);
    //             Integer experience = yearsOfExperience.get(i);
    //             List<Employee> employees = getService.filterEmployees(experience, managerId);

    //             Map<String, Object> result = new HashMap<>();
    //             result.put("managerId", managerId);
    //             result.put("yearsOfExperience", experience);
    //             result.put("employees", employees);
    //             results.add(result);
    //         }
    //     } else if (managerIds != null) {
    //         for (Integer managerId : managerIds) {
    //             List<Employee> employees = getService.filterEmployees(null, managerId);

    //             Map<String, Object> result = new HashMap<>();
    //             result.put("managerId", managerId);
    //             result.put("employees", employees);
    //             results.add(result);
    //         }
    //     } else if (yearsOfExperience != null) {
    //         for (Integer experience : yearsOfExperience) {
    //             List<Employee> employees = getService.filterEmployees(experience, null);

    //             Map<String, Object> result = new HashMap<>();
    //             result.put("yearsOfExperience", experience);
    //             result.put("employees", employees);
    //             results.add(result);
    //         }
    //     } else {
    //         List<Employee> employees = getService.getAllEmployees();
    //         Map<String, Object> result = new HashMap<>();
    //         result.put("employees", employees);
    //         results.add(result);
    //     }

    //     Map<String, Object> responseBody = new HashMap<>();
    //     responseBody.put("results", results);

    //     return ResponseEntity.ok(responseBody);
   // }


// package com.example.Mongo.Controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import com.example.Mongo.Models.Employee;
// //import com.example.Mongo.Models.Employee;
// import com.example.Mongo.Service.GetService;
// //import com.example.Mongo.Service.GetService.FilterRequest;
// //import com.example.Mongo.Service.GetService.FilterResult;

// import org.springframework.beans.factory.annotation.Autowired;
// //import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// //import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/getcontroller")
// public class GetController {
//     @Autowired
//     private GetService getService;
// @GetMapping("/get")
// public ResponseEntity<Map<String, Object>> filterEmployees(
//         @RequestParam( required = false ) List<Integer> managerIds,
//         @RequestParam(required = false) List<Integer> yearsOfExperience) {

//     List<Map<String, Object>> results = new ArrayList<>();

//     for (int i = 0; i < managerIds.size(); i++) {
//         Integer managerId = managerIds.get(i);
//         Integer experience = yearsOfExperience.get(i);

//         List<Employee> employees = getService.filterEmployees(experience, managerId);

//         Map<String, Object> result = new HashMap<>();
//         result.put("managerId", managerId);
//         result.put("yearsOfExperience", experience);
//         result.put("employees", employees);
//         results.add(result);
//     }

//     Map<String, Object> responseBody = new HashMap<>();
//     responseBody.put("results", results);

//     return ResponseEntity.ok(responseBody);
// }}


// // package com.example.Mongo.Controller;

// // import java.util.ArrayList;
// // import java.util.HashMap;
// // import java.util.List;
// // import java.util.Map;

// // //import com.example.Mongo.Models.Employee;
// // import com.example.Mongo.Service.GetService;
// // import com.example.Mongo.Service.GetService.FilterRequest;
// // import com.example.Mongo.Service.GetService.FilterResult;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.RequestBody;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // import org.springframework.web.bind.annotation.RestController;

// // @RestController
// // @RequestMapping("/getcontroller")
// // public class GetController {
// //     @Autowired
// //     private GetService getService;

// //     @GetMapping("/get")
// //     public ResponseEntity<Map<String, Object>> filterEmployees(@RequestBody List<FilterRequest> filterRequests) {
// //         List<Map<String, Object>> successResults = new ArrayList<>();
// //         List<String> errorMessages = new ArrayList<>();

// //         List<FilterResult> results = getService.filterEmployees(filterRequests);

// //         for (FilterResult result : results) {
// //             if (result.getEmployees().isEmpty()) {
// //                 errorMessages.add("No employees found for Manager ID: " + result.getRequest().getManagerId()
// //                         + " with " + result.getRequest().getYearsOfExperience() + " years of experience.");
// //             } else {
// //                 Map<String, Object> successData = new HashMap<>();
// //                 successData.put("managerId", result.getRequest().getManagerId());
// //                 successData.put("yearsOfExperience", result.getRequest().getYearsOfExperience());
// //                 successData.put("employees", result.getEmployees());
// //                 successResults.add(successData);
// //             }
// //         }

// //         Map<String, Object> responseBody = new HashMap<>();
// //         responseBody.put("success", successResults);
// //         responseBody.put("errors", errorMessages);

// //         return ResponseEntity.status(HttpStatus.OK).body(responseBody);
// //     }
// // }

// // package com.example.Mongo.Controller;

// // import java.util.ArrayList;
// // import java.util.List;

// // import com.example.Mongo.Models.Employee;
// // //import com.example.Mongo.Models.Employee;
// // import com.example.Mongo.Service.GetService;
// // import com.example.Mongo.Service.GetService.FilterRequest;
// // import com.example.Mongo.Service.GetService.FilterResult;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.RequestBody;
// // //import org.springframework.web.bind.annotation.PostMapping;
// // //import org.springframework.web.bind.annotation.PutMapping;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // //import org.springframework.web.bind.annotation.RequestParam;
// // import org.springframework.web.bind.annotation.RestController;

// // @RestController
// // @RequestMapping("/getcontroller")
// // public class GetController {
// //     @Autowired
// //     GetService getService;
// //     @GetMapping("/get")
// //     public ResponseEntity<?> filterEmployees(@RequestBody List<FilterRequest> filterRequests) {
// //         List<String> successMessages = new ArrayList<>();
// //         List<String> errorMessages = new ArrayList<>();

// //         List<FilterResult> results = getService.filterEmployees(filterRequests);
// //         //List<Employee> employees = getService.filterEmployees(Employee.managerId, yearsOfExperience);
// //         for (FilterResult result : results) {
// //             if (result.getEmployees().isEmpty()) {
// //                 errorMessages.add("No employees found for Manager ID: " + result.getRequest().getManagerId()
// //                         + " with " + result.getRequest().getYearsOfExperience() + " years of experience.");
// //             } else {
// //                 successMessages.add("Found " + result.getEmployees().size() + " employees for Manager ID: "
// //                         + result.getRequest().getManagerId() + " with " + result.getRequest().getYearsOfExperience()
// //                         + " years of experience.");
// //             }
// //         }

// //         return ResponseEntity.ok().body("Success: " + successMessages + " Errors: " + errorMessages);
// //     }
    
// // }
