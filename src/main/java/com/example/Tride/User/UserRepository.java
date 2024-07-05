package com.example.Tride.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
User findByEmailid(String emailid);
User findByName(String name);
User findByRideid(String rideid);
User deleteByEmailid(String emailid);

}
