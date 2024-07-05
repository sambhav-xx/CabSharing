package com.example.Tride.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RideController {
    private final RideRepository repo;
    private final UserRepository userRepository;
    public RideController(RideRepository repo, UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }


///for HOmepage and ride component
 @DeleteMapping("/cancelride/{emailid}")
 public int  cancelRide(@PathVariable  String emailid,@RequestParam String rideid, @RequestParam String useremail){
     System.out.println(emailid);
     System.out.println(useremail);
     System.out.println(rideid);
    User user = this.userRepository.findByEmailid(emailid);

    try {
        if(emailid.equals(useremail)){
            this.repo.deleteByRideid(rideid);
            return 1;
        }
        else{
            Rides ride = this.repo.findByRideid(rideid);
            ride.removePassenger(rideid);
            return 0;
        }

    }
    catch (Exception e){
        return 500;
    }


 }



    @PostMapping("/newride/{emailid}")
    public String newride(@RequestBody Rides ride,@PathVariable String emailid){
//        this.repo.save(ride);
        int length =10;
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            stringBuilder.append(digits.charAt(index));
        }
        String id = stringBuilder.toString();

        String rideid = emailid + stringBuilder.toString();
         User userupdate=this.userRepository.findByEmailid(emailid);
        System.out.println("checking thiis email :" + emailid);
        Rides rides = new Rides(ride.seats(), ride.price(), ride.From(),ride.To(),ride.time(),null,ride.emailid(),ride.originalPrice(),ride.originalSeats(),rideid);
        this.repo.save(rides);

        List<String> rideids = userupdate.rideid() != null ? new ArrayList<>(userupdate.rideid()) : new ArrayList<>();
        rideids.add(rideid);
        User updatedUser = new User(userupdate.name(),userupdate.emailid(),userupdate.phoneno(),rideids,userupdate.role());
        this.userRepository.deleteByEmailid(emailid);
         this.userRepository.save(updatedUser);
         return  rideid;
    }

    @PostMapping("/addpassenger/{rideid}")
    public void addPassengerdefault(@PathVariable String rideid,@RequestBody String email) {
        System.out.println("checki");
        Rides ride = this.repo.findByRideid(rideid);

        System.out.println(rideid);
        Rides updatedRide = ride.addPassengerdefault(email);
        this.repo.save(updatedRide);
        this.repo.deleteByRideid(rideid);

    }
    @PostMapping("/addpassengers")
    public String addPassenger(@RequestParam String rideid, @RequestParam String useremail) {

        Rides ride = this.repo.findByRideid(rideid);


        Rides updatedRide = ride.addPassenger(useremail);
        System.out.println("checki");
        this.repo.save(updatedRide);
        this.repo.deleteByRideid(rideid);
        return rideid;
    }

//    @PostMapping("/selectride/{email}")   //// updating user rideid
//    public void selectride(@RequestBody String emailid,@PathVariable String email){
//        System.out.println("checling");
//        String decodedEmailid = URLDecoder.decode(emailid, StandardCharsets.UTF_8);
//
//        String modifiedEmail = removeLastCharacter(decodedEmailid);
//        System.out.println(modifiedEmail);
//        System.out.println(email);
//        User userupdate = this.userRepository.findByEmailid(modifiedEmail);
//        User user2 = this.userRepository.findByEmailid(email);
//        List<String> rideids = new ArrayList<>();
//        User updatedUser = new User(userupdate.name(),userupdate.emailid(),userupdate.phoneno(), user2.rideid(),userupdate.role());
//        this.userRepository.deleteByEmailid(modifiedEmail);
//        this.userRepository.save(updatedUser);
//
//    }
@PostMapping("/confirmride")
    public void confirm(@RequestParam String rideid, @RequestParam String useremail) {
        User user = this.userRepository.findByEmailid(useremail);

        if (user != null) {
            // Check if rideid list is null and initialize it if necessary
            List<String> rideids = user.rideid() != null ? new ArrayList<>(user.rideid()) : new ArrayList<>();
            rideids.add(rideid);

            // Create a new User object with the updated ride IDs
            User updatedUser = new User(user.name(), user.emailid(), user.phoneno(), rideids, user.role());

            // Save the updated user
            this.userRepository.deleteByEmailid(useremail);
            this.userRepository.save(updatedUser);

            System.out.println("Ride confirmed for user: " + useremail);
        } else {
            System.out.println("User not found: " + useremail);
        }
    }



    public static String removeLastCharacter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }

    @PostMapping("/yourrides/{emailid}")
    public List<Rides> yourrides(@PathVariable String emailid) {
        User user = this.userRepository.findByEmailid(emailid);
        List<String> rides = user.rideid();
        List<Rides> yourrides = new ArrayList<>();
        for (int i = 0; i < rides.size(); i++) {

            yourrides.add(this.repo.findByRideid(rides.get(i)));


        }
                System.out.println(rides.size());
        return yourrides;
    }
    @GetMapping("/selectride/{rideid}")
    ResponseEntity<Rides> getride(@PathVariable String rideid){
//        System.out.println(email);
        return  ResponseEntity.ok(this.repo.findByRideid(rideid));
    }

    @GetMapping("/allride")
    public ResponseEntity<List<Rides>> getallrides(){
        return  ResponseEntity.ok(this.repo.findAll());
    }

//    @GetMapping("/cancelride/{rideid}")
//    public void cancelride(@PathVariable String rideid){
//        this.repo.deleteByRideid(rideid);
//        System.out.println("canceled the ride successffullyy");
//    }


}
