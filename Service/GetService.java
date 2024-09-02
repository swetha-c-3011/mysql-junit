package com.example.Mongo.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.Mongo.Models.Department;
import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetService {

    @Autowired
    private EmployeeRepository employeeRepo;
    public ResponseEntity<?> getEmployeeDetails(int managerId, int yearsOfExperience) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Employee> employees = employeeRepo.findAll();
        Map<String, String> errors = new HashMap<>();
 
        // Calculate the cutoff datetime for experience filtering
        LocalDateTime experienceCutoffDateTime = yearsOfExperience != -1
                ? currentDateTime.minusYears(yearsOfExperience)
                : null;
 
        List<Employee> filteredEmployees = employees.stream()
            .filter(employee -> {

                // Check if dateOfJoining is null
        if (employee.getDateOfJoining() == null) {
            return false; // Skip employees without a date of joining
        }
                // Calculate experience using compareTo
                boolean matchesExperience = experienceCutoffDateTime == null
                        || employee.getDateOfJoining().compareTo(experienceCutoffDateTime) <= 0;
                boolean matchesManager = managerId == 0 || employee.getManagerId() == managerId;
                return matchesExperience && matchesManager;
            })
            .collect(Collectors.toList());
            if(filteredEmployees.isEmpty()){
                errors.put("error","No employee exists with the given credentials");
                return new ResponseEntity<>(errors, HttpStatus.OK);
            }
 
            Optional<Employee> accountManagerOptional = employeeRepo.findById(managerId);
            Integer accountManagerId= accountManagerOptional.map(Employee::getEmployeeId).orElse(0);
            String accountManagerName = accountManagerOptional.map(Employee::getName).orElse("Unknown");
            Optional<Department> accountManagerDepartment=accountManagerOptional.map(Employee::getDepartment);
 
 
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("message", "successfully fetched");
 
            Map<String, Object> details = new LinkedHashMap<>();
            details.put("accountManager", accountManagerName);
            details.put("department", accountManagerDepartment);
            details.put("id", accountManagerId);
            details.put("employeeList", filteredEmployees.stream().map(emp -> {
                Map<String, Object> employeeDetails = new LinkedHashMap<>();
                employeeDetails.put("name", emp.getName());
                employeeDetails.put("id", emp.getEmployeeId());
                employeeDetails.put("designation", emp.getDesignation());
                employeeDetails.put("department", emp.getDepartment());
                employeeDetails.put("email", emp.getEmail());
                employeeDetails.put("mobile", emp.getMobile());
                employeeDetails.put("location", emp.getLocation());
                employeeDetails.put("dateOfJoining", emp.getDateOfJoining());
                employeeDetails.put("createdTime", emp.getDateOfJoining());
                employeeDetails.put("updatedTime", emp.getDateOfJoining());
                return employeeDetails;
            }).collect(Collectors.toList()));
 
        response.put("details", List.of(details));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
 
        // Map the list of employees to a response format
        // Map<String, Object> response = Map.of(
        //     "employees", filteredEmployees.stream().map(emp -> Map.of(
        //         "employeeId", emp.getEmployeeId(),
        //         "name", emp.getName(),
        //         "designation", emp.getDesignation(),
        //         "department", emp.getDepartment(),
        //         "location", emp.getLocation(),
        //         "dateOfJoining", emp.getDateOfJoining()
        //     )).collect(Collectors.toList())
        // );
 
       

//     private LocalDateTime calculateExperienceThreshold(int yearsOfExperience) {
//         return LocalDateTime.now().minusYears(yearsOfExperience);
//     }

//     public List<Employee> filterEmployees(Integer yearsOfExperience, Integer managerId) {
//         if (managerId != null && yearsOfExperience != null) {
//             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
//             return employeeRepository.findByManagerIdAndExperienceGreaterThanEqual(managerId, threshold);
//         } else if (managerId != null) {
//             return employeeRepository.findByManagerId(managerId);
//         } else if (yearsOfExperience != null) {
//             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
//             return employeeRepository.findByExperienceGreaterThanEqual(threshold);
//         } else {
//             return employeeRepository.findAll();
//         }
//     }

//     public List<Employee> getAllEmployees() {
//         return employeeRepository.findAll();
//     }
// }


// package com.example.Mongo.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import com.example.Mongo.Models.Employee;
// import com.example.Mongo.Repository.EmployeeRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class GetService {

//     @Autowired
//     private EmployeeRepository employeeRepository;

//     private LocalDateTime calculateExperienceThreshold(int yearsOfExperience) {
//         return LocalDateTime.now().minusYears(yearsOfExperience);
//     }

//     public List<Employee> filterEmployees(Integer yearsOfExperience, Integer managerId) {
//         if (managerId != null && yearsOfExperience != null) {
//             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
//             return employeeRepository.findByManagerIdAndExperienceGreaterThanEqual(managerId, threshold);
//         } else if (managerId != null) {
//             return employeeRepository.findByManagerId(managerId);
//         } else if (yearsOfExperience != null) {
//             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
//             return employeeRepository.findByExperienceGreaterThanEqual(threshold);
//         } else {
//             return employeeRepository.findAll();
//         }
//     }

//     // New method to handle multiple manager IDs with corresponding years of experience
//     public List<Employee> filterEmployees(List<Integer> managerIds, List<Integer> yearsOfExperience) {
//         List<Employee> filteredEmployees = new ArrayList<>();

//         for (int i = 0; i < managerIds.size(); i++) {
//             Integer managerId = managerIds.get(i);
//             Integer experience = yearsOfExperience.get(i);

//             List<Employee> employees = filterEmployees(experience, managerId);
//             filteredEmployees.addAll(employees);
//         }

//         return filteredEmployees;
//     }
// }


// // package com.example.Mongo.Service;

// // import java.time.LocalDateTime;
// // import java.util.ArrayList;
// // import java.util.List;

// // import com.example.Mongo.Models.Employee;
// // import com.example.Mongo.Repository.EmployeeRepository;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class GetService {

// //     @Autowired
// //     private EmployeeRepository employeeRepository;
// //     private LocalDateTime calculateExperienceThreshold(int yearsOfExperience) {
// //         return LocalDateTime.now().minusYears(yearsOfExperience);
// //     }
// //     //private EmployeeRepository employeeRepository;
// //     public List<Employee> filterEmployees(Integer yearsOfExperience,Integer managerId ) {
// //         if (managerId != null && yearsOfExperience != null) {
// //             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
// //             return employeeRepository.findByManagerIdAndExperienceGreaterThanEqual(managerId, threshold);
// //         } else if (managerId != null) {
// //             return employeeRepository.findByManagerId(managerId);
// //         } else if (yearsOfExperience != null) {
// //             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
// //             return employeeRepository.findByExperienceGreaterThanEqual(threshold);
// //         } else {
// //             return employeeRepository.findAll();
// //         }
// //     }
// //     public List<FilterResult> filterEmployees(List<FilterRequest> requests) {
// //         List<FilterResult> results = new ArrayList<>();
        
// //         for (FilterRequest request : requests) {
// //             List<Employee> employees = filterEmployees(request.getManagerId(), request.getYearsOfExperience());
// //             FilterResult result = new FilterResult(request, employees);
// //             results.add(result);
// //         }

// //         return results;
// //     }

// //     public static class FilterRequest {
// //         private Integer managerId;
// //         private Integer yearsOfExperience;

// //         public Integer getManagerId() {
// //             return managerId;
// //         }

// //         public void setManagerId(Integer managerId) {
// //             this.managerId = managerId;
// //         }

// //         public Integer getYearsOfExperience() {
// //             return yearsOfExperience;
// //         }

// //         public void setYearsOfExperience(Integer yearsOfExperience) {
// //             this.yearsOfExperience = yearsOfExperience;
// //         }
// //     }

// //     public static class FilterResult {
// //         private FilterRequest request;
// //         private List<Employee> employees;

// //         public FilterResult(FilterRequest request, List<Employee> employees) {
// //             this.request = request;
// //             this.employees = employees;
// //         }

// //         public FilterRequest getRequest() {
// //             return request;
// //         }

// //         public List<Employee> getEmployees() {
// //             return employees;
// //         }
// //     }

// // }
