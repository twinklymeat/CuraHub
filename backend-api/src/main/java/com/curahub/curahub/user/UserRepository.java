package com.curahub.curahub.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> getUserById(long id);
    List<User> getUserByEmail(String email);
    List<User> getUserByPhone(String phone);
    List<User> getUserByFirstName(String firstName);
    List<User> getUserByLastName(String lastName);
    List<User> getUserByName(String name);
}
