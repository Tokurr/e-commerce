package com.example.ecoomerce.user.repository;

import com.example.ecoomerce.user.exception.UserNotFoundException;
import com.example.ecoomerce.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


     Optional<User> findByMail(String mail);

     default User findByMailOrThrow(String mail) {
          return findByMail(mail)
                  .orElseThrow(() -> new UserNotFoundException("User not found with mail: " + mail));
     }
     default User findByIdOrThrow(Long id) {
          return findById(id)
                  .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
     }

}
