package com.eventec.eventec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_items")
public class UserItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    public enum UserType {
        aluno,
        usuarioComum,
        professor
    }
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(unique = true)
    private Long cpf;

    @Column(unique = true)
    private String emailInstitucional;

    //Aluno
    private Long ra;
    private String unidade;
    private String semestre;
    private String curso;

    @Override
    public String toString() {
        return String.format("userItem{userId=%d, userName=%s, email=%s, password=%s, cpf=%d}",
                userid, userName, email, password, cpf);
    }
}