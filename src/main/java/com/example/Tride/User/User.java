package com.example.Tride.User;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
public record User(

        String name,

        String emailid,
        int phoneno,
      List<String> rideid,
        String role
) {
}
