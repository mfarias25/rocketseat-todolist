package com.mfarias25.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


//Contrato da aplicação
public interface UserRepository extends JpaRepository<UserModel, UUID> {
   UserModel findByUsername(String username);

}
