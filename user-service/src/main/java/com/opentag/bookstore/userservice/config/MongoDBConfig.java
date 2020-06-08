package com.opentag.bookstore.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration for mongo db.
 */
@Configuration
@EnableMongoRepositories("com.opentag.bookstore.userservice.repository")
public class MongoDBConfig {

}
