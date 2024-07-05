package com.example.Tride.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRepository extends MongoRepository<Rides,String> {
    Rides findByEmailid(String emailid);
    Rides deleteByRideid(String rideid);
    Rides findAllByRideid(String rideid);
    Rides findByRideid(String rideid);

}
