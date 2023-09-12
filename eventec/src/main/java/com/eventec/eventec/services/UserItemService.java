package com.eventec.eventec.services;

import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.repositories.UserItemRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@Service
public class UserItemService{
    @Autowired
    private UserItemRepository userItemRepository;

    public Optional<UserItem> getByEmailAndPassword(String email, String senha) {
        return userItemRepository.findByEmailAndPassword(email, senha);
    }
    public Iterable<UserItem> getAll() { return userItemRepository.findAll(); }

    public UserItem save(UserItem userItem) {
        return userItemRepository.save(userItem);
    }

    public List<UserItem> consultaUsuario(){
        return userItemRepository.findAll();
    }


}
