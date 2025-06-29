package org.pertisth.loginauthentication.repository;

import org.bson.types.ObjectId;
import org.pertisth.loginauthentication.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User getUsersByUsername(String username);
}
