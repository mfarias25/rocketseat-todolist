package com.mfarias25.todolist.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

//@Column(name = "usuario") //settar outro nome para a coluna do banco de dados.
    private String name;
    private String username;
    private String password;

    @CreationTimestamp // Quando for gerado o banco de dados vai ter as info do datetime.
    private LocalDateTime createdAt;
}
