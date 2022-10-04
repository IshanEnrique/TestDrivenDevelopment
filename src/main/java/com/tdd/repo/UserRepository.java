package com.tdd.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tdd.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
