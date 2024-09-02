
package com.example.Mongo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;

//import com.example.Mongo.Models.Department;
//import com.example.Mongo.Models.Designation;
import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class PutService {
 
    @Autowired
    private EmployeeRepository employeeRepo;
 
    public ResponseEntity<Map<String, String>> updateEmployeeManagers(List<Map<String, Integer>> employeeManagerPairs) {
        Map<String, String> responseMessages = new HashMap<>();
        Map<String, String> failedUpdates = new HashMap<>();
 
        for (Map<String, Integer> pair : employeeManagerPairs) {
            Integer employeeId = pair.get("employeeId");
            Integer newManagerId = pair.get("managerId");
 
            //check if no body is given
            if (employeeId == null || newManagerId == null) {
                failedUpdates.put("error_" + employeeId, "Employee ID or Manager ID is missing");
                continue;
            }
 
            // check if
            Employee employee = employeeRepo.findById(employeeId).orElse(null);
            if (employee == null) {
                failedUpdates.put("error_" + employeeId, "Employee not found");
                continue;
            }
 
            // Fetch current manager
            Employee currentManager = employeeRepo.findById(employee.getManagerId()).orElse(null);
            if (currentManager == null) {
                failedUpdates.put("error_" + employeeId, "Current manager not found");
                continue;
            }
 
            // Fetch new manager
            Employee newManager = employeeRepo.findById(newManagerId).orElse(null);
            if (newManager == null) {
                failedUpdates.put("error_" + employeeId, "New manager not found");
                continue;
            }
 
            if(currentManager.getEmployeeId()==newManager.getEmployeeId()){
                failedUpdates.put("error_" + employeeId, "New manager is same as the current manager");
                continue;
            }
 
            // Check if employee is in the current manager's list
            List<Employee> employeesManagedByCurrent = new ArrayList<>(employeeRepo.findByManagerId(employee.getManagerId()));
 
            System.out.println("Employees managed by " + currentManager.getName() + ":");
            for (Employee emp : employeesManagedByCurrent) {
                System.out.println(emp);
            }
 
            if (!employeesManagedByCurrent.contains(employee)) {
                failedUpdates.put("error_" + employeeId, "Employee is not in the current manager's list");
                continue;
            }
 
            // Remove employee from current manager's list
            employeesManagedByCurrent.remove(employee);
            employeeRepo.saveAll(employeesManagedByCurrent);
 
            // Update employee's managerId and department
            employee.setManagerId(newManagerId);
            employee.setDepartment(newManager.getDepartment());
            employeeRepo.save(employee);
 
            // Add employee to new manager's list
            List<Employee> employeesManagedByNew = new ArrayList<>( employeeRepo.findByManagerId(newManagerId));
            employeesManagedByNew.add(employee);
            employeeRepo.saveAll(employeesManagedByNew);
 
            // Success message for this employee
            String message = String.format("%s's manager has been successfully changed from %s to %s.",
                    employee.getName(), currentManager.getName(), newManager.getName());
            responseMessages.put("employee_" + employeeId, message);
        }
 
        // Convert failedUpdates to Map<String, String>
        responseMessages.putAll(failedUpdates);
 
        if (!failedUpdates.isEmpty()) {
            return new ResponseEntity<>(responseMessages, HttpStatus.BAD_REQUEST);
        }
 
        return new ResponseEntity<>(responseMessages, HttpStatus.OK);
    }
}

// @Service
// public class PutService {
//     @Autowired
//     EmployeeRepository employeeRepository;
//     public Employee updateManager(Integer employeeId,Integer newManagerId){

//        Optional<Employee> toChkEmpId=employeeRepository.findById(employeeId);

//        if(toChkEmpId.isPresent()){
           
//            Employee empObj=toChkEmpId.get();
         
//            Employee newManager=employeeRepository.findByEmployeeId(newManagerId);
      
//            //empObj.setDesignation(Department.associate);
//            empObj.setDepartment(newManager.getDepartment());
           
//            empObj.setManagerId(newManagerId);
         
//            employeeRepository.save(empObj);
//            return empObj;
//        }return null;
//     }
    
// }
