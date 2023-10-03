package com.eventec.eventec.repositories;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    //@Query(value = "SELECT * FROM user_items WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<UserItem> findUserByEmailAndPassword(String email, String password);

}
