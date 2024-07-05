package com.example.Tride.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
;
@RestController
public class  AppController {
    private final UserControl userControl;
    private final ObjectMapper objectMapper;
    private final RideRepository riderepo;
    private final UserRepository userrepo;
    public AppController(UserControl userControl, ObjectMapper objectMapper, RideRepository riderepo, UserRepository userrepo) {
        this.userControl = userControl;
        this.objectMapper = objectMapper;
        this.riderepo = riderepo;
        this.userrepo = userrepo;
    }


//        @GetMapping("/allrides")
//    public ResponseEntity<List<User>> getAllRides(){
//
//        List<Rides> rides =this.riderepo.findAll();
//        for(Rides ride: rides){
//            Optional<User> user = userrepo.findById(ride.id());
//
//        }
//
//
//    }

@GetMapping("/newride/{id}")
public void getAllUserscheck(@PathVariable String id){
    Optional<User> user =   this.userrepo.findById(id);



}

//    public ResponseEntity getById(@PathVariable String id){
//        Optional<User> user = this.repo.findById(id);
//        if(user.isPresent()){
//            return ResponseEntity.ok(user.get());
//        }
//        else {
//            return ResponseEntity.ok("The ride you are searching for may have been deletd by the user");
//        }
//    }


}
