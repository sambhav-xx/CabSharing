package com.example.Tride.User;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserControl {

    private final UserRepository repo;

    public UserControl( UserRepository repo){


        this.repo = repo;
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.repo.findAll());
    }
//    @GetMapping("/getuserid/{emailid}")
//    public  ResponseEntity getemailid(@PathVariable String id){
//        Optional<User> user = this.repo.findById(id);
//        if(user.isPresent()){
//            return ResponseEntity.ok(user.getem);
//        }
//        else {
//            return ResponseEntity.ok("The ride you are searching for may have been deletd by the user");
//        }
//    }


    @PostMapping("/postuser")
    public ResponseEntity<User> createUser(@RequestBody User User){

        return ResponseEntity.ok(this.repo.save(User));
    }

    @GetMapping("/getname")
    public String getName(@RequestParam String emailid) {
        System.out.println("hit");
        System.out.println(emailid);
        User loggedUser = this.repo.findByEmailid(emailid);
        System.out.println(loggedUser.name());
        return loggedUser.name();
    }

    @GetMapping("/user/{emailid}")
    public ResponseEntity<User> check3(@PathVariable String emailid){
        System.out.println("Chec");
        return ResponseEntity.ok(this.repo.findByEmailid("sddadadvsv"));
    }

//    @PostMapping("/finduser/{emailid}")
//    public String finduser(@PathVariable String emailid){
//        System.out.print("here is yourride");
//        System.out.println(emailid);
//        User user = this.repo.findByEmailid(emailid);
//        String userid = user.rideid();
//        return userid;
//    }


    @GetMapping("/user2/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Optional<User> user = this.repo.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.ok("The ride you are searching for may have been deletd by the user");
        }
    }

    @DeleteMapping("user/remove/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        Optional<User> user = this.repo.findById(id);
        if(user.isPresent()){
            this.repo.deleteById(id);
            return ResponseEntity.ok("your request for rided has been successfully deleted from the database");
        }
        else {
            return ResponseEntity.ok("The ride you are trying to delete was not found in the database");
        }
    }

//    @PutMapping Mapping("ride/update/{id}")
//    public ResponseEntity updateById(@PathVariable String id){
//        Optional<User> user = this.repo.findById(id);
//        if(user.isPresent()){
//            this.repo.deleteById(id);
//            return ResponseEntity.ok("your request for rided has been successfully deleted from the database");
//        }
//        else {
//            return ResponseEntity.ok("The ride you are trying to delete was not found in the database");
//        }
//    }






}
