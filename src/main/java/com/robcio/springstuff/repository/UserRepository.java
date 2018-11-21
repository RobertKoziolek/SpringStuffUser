package com.robcio.springstuff.repository;

import com.robcio.springstuff.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT *  FROM user ORDER BY rand() LIMIT 1")
    User findRandom();

    @Override
    List<User> findAll();
}
