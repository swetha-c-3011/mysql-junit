package com.example.Mongo.Controller;

import com.example.Mongo.Service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deleteController")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@RequestParam int employeeId) {
        return deleteService.deleteEmployee(employeeId);
    }
}

// package com.example.Mongo.Controller;

// import com.example.Mongo.Service.DeleteService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

// @RestController
// @RequestMapping("/deletecontroller")
// public class DeleteController {

//     @Autowired
//     private DeleteService deleteService;

//     @DeleteMapping("/delete")
//     public ResponseEntity<String> deleteEmployees(@RequestBody List<String> employeeIds) {
//         try {
//             deleteService.deleteEmployeesByIds(employeeIds);
//             return ResponseEntity.ok("Employees with IDs " + employeeIds + " have been deleted.");
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }
// }

// // package com.example.Mongo.Controller;



// // import java.util.ArrayList;
// // import java.util.List;

// // import com.example.Mongo.Service.DeleteService;
// // import com.example.Mongo.Service.DeleteService.DeleteResult;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.DeleteMapping;
// // //import org.springframework.web.bind.annotation.PathVariable;
// // //import org.springframework.web.bind.annotation.RequestBody;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // import org.springframework.web.bind.annotation.RequestParam;
// // import org.springframework.web.bind.annotation.RestController;

// // @RestController
// // @RequestMapping("/deleteController")
// // public class DeleteController {

// //     @Autowired
// //     private DeleteService deleteService;

// //     @DeleteMapping("/delete")
// //     public ResponseEntity<?> deleteEmployees(@RequestParam List<Integer> employeeIds) {
// //         List<String> successMessages = new ArrayList<>();
// //         List<String> errorMessages = new ArrayList<>();

// //         List<DeleteResult> results = deleteService.deleteEmployees(employeeIds);

// //         for (DeleteResult result : results) {
// //             if (result.isSuccess()) {
// //                 successMessages.add(result.getMessage());
// //             } else {
// //                 errorMessages.add(result.getMessage());
// //             }
// //         }

// //         return ResponseEntity.ok().body("Success: " + successMessages + " Errors: " + errorMessages);
// //     }
// // }

