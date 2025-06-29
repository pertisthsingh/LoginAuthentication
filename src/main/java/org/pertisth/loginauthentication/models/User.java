package org.pertisth.loginauthentication.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection = "users")
@Component
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;
    private String password;
}
