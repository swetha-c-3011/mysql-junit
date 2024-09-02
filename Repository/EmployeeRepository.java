package com.example.Mongo.Repository;

import com.example.Mongo.Models.Department;
import com.example.Mongo.Models.Designation;
import com.example.Mongo.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Find employees by managerId
    List<Employee> findByManagerId(Integer managerId);

    // Custom query to find employees by managerId and experience (date of joining)
    @Query("SELECT e FROM Employee e WHERE e.managerId = :managerId AND e.dateOfJoining <= :experience")
    List<Employee> findByManagerIdAndExperienceGreaterThanEqual(@Param("managerId") Integer managerId, @Param("experience") LocalDateTime experience);

    // Custom query to find employees by years of experience (without managerId)
    @Query("SELECT e FROM Employee e WHERE e.dateOfJoining <= :dateOfJoiningThreshold")
    List<Employee> findByExperienceGreaterThanEqual(@Param("dateOfJoiningThreshold") LocalDateTime dateOfJoiningThreshold);

    boolean existsByDepartmentAndDesignation(Department department, Designation designation);

    Optional<Employee> findByEmployeeId(Integer employeeId);
}

// // package com.example.Mongo.Repository;

// // public interface EmployeeRepository {

    
// // }
// package com.example.Mongo.Repository;

// import com.example.Mongo.Models.Department;
// import com.example.Mongo.Models.Designation;
// import com.example.Mongo.Models.Employee;
// //import org.springframework.data.mongodb.repository.MongoRepository;
// //import org.springframework.data.mongodb.repository.Query;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//     // Find employees by managerId
//     List<Employee> findByManagerId(Integer managerId);
    

//     // Custom query to find employees by managerId and years of experience

//     @Query("SELECT e FROM Employee e WHERE e.managerId = :managerId AND e.experience >= :experience")
// List<Employee> findByManagerIdAndExperienceGreaterThanEqual(@Param("managerId") Integer managerId, @Param("experience") LocalDateTime experience);

//     // @Query("{'managerId': ?0, 'dateOfJoining': {$lte: ?1}}")
//     // List<Employee> findByManagerIdAndExperienceGreaterThanEqual(Integer managerId, LocalDateTime dateOfJoiningThreshold);

//     // Custom query to find employees by years of experience (without managerId)
//     @Query("{'dateOfJoining': {$lte: ?0}}")
//     List<Employee> findByExperienceGreaterThanEqual(LocalDateTime dateOfJoiningThreshold);

//     boolean existsByDepartmentAndDesignation(Department department, Designation designation);

//     Optional<Employee> findByEmployeeId(Integer newManagerId);

// }
