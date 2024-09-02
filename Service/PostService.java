package com.example.Mongo.Service;
import com.example.Mongo.Models.Designation;
import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public PostService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public String createEmployee(List<Employee> employees) {
        List<String> errors = new ArrayList<>();
        List<String> successfullySavedEmployees = new ArrayList<>();
       Boolean  bool=true;
        for(Employee employee : employees) {
            // Check if the employee ID already exists
            Optional<Employee> employeeOpt = employeeRepository.findById(employee.getEmployeeId());
            if(employeeOpt.isPresent()) {
                errors.add("Employee ID " + employee.getEmployeeId() + " already exists. Please try a different employee ID.");
                bool=false;
                //continue; // Skip this employee and move to the next
            }

            if (Designation.Account_manager==(employee.getDesignation())) {
                if (employeeRepository.existsByDepartmentAndDesignation(employee.getDepartment(), Designation.Account_manager)) {
                    errors.add("employee id "+ employee.getEmployeeId()+" Department " + employee.getDepartment() + " already has a manager.");
                    bool=false;
                    //continue;
                }
            }

            if(!employee.validName()){
                errors.add("name cannot be empty or you have given a invalid name "+employee.getEmployeeId());
               // continue;
               bool=false;
            }
            if(!employee.chkDesignation()){
                errors.add("designation should not be empty or designation is not valid for the employee :"+employee.getEmployeeId());
                bool=false;
                //continue;
            }
            
            if(!employee.isValidEmail()){
                errors.add("email cant be null or you havn't given valid email "+employee.getEmployeeId());
                bool=false;
                //continue;

            }
            if(!employee.chkDepartment()){
                errors.add("department can be null "+employee.getEmployeeId());
                bool=false;
                //continue;
            }

            if(!employee.isValidMobileNum()){
                errors.add("mobile number is not valid  for the employee :"+employee.getEmployeeId());
                bool=false;
                //continue;
            }
            if(!employee.chkLocation()){
                errors.add("location cant be null "+employee.getEmployeeId());
                bool=false;
                //continue;
            }


            if(!employee.isValidManagerId()){
               errors.add("manager id is not valid for the employee :"+employee.getEmployeeId());
               bool=false;
               //continue;
            }
            
            

           if(!employee.validDateOfJoining()){
                errors.add("date of joining cant be null or date of joining is invalid "+employee.getEmployeeId());
                bool=false;//continue;
            }

             // Save the employee
             if(bool){
            employeeRepository.save(employee);
            successfullySavedEmployees.add("Employee ID " + employee.getEmployeeId() + " is saved successfully.");}
        }
    
        // Combine error messages and success messages
        String response = String.join(" | ", successfullySavedEmployees);
        System.out.println("response before adding error "+response);
        if (!errors.isEmpty()) {
            response += " | Errors: " + String.join(" | ", errors);
        }
        System.out.println("response affter adding error "+response);
    
        return response;
    }
    
}
