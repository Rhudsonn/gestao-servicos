package com.zheiri.servico.login.repository;

import com.zheiri.servico.login.entity.Login_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Login_Repository extends JpaRepository<Login_Entity, Long> {

   Optional<Login_Entity> findBySenhaAndLogin(String senha,String login);

   boolean existsByLogin(String login);
   boolean existsBySenha(String senha);
}
