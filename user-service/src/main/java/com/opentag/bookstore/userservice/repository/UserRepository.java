package com.opentag.bookstore.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.opentag.bookstore.userservice.model.entity.User;

/**
 * User repository used to work with the db.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findUserByEmail(String email);

}
