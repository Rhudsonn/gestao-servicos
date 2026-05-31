package com.zheiri.servico.login.entity;

import com.zheiri.servico.enums.Acesso_Login;
import com.zheiri.servico.enums.Status_Login;
import com.zheiri.servico.login.dto.Login_Dto_Atualiza_Login;
import com.zheiri.servico.login.dto.Login_Dto_Entrada;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "login")
public class Login_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    @Size(min = 8)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Acesso_Login acesso_login;

    @Enumerated(EnumType.STRING)
    private Status_Login status_login;



    // Transformando Entity em Dto
    public Login_Entity(Login_Dto_Entrada loginDtoEntrada){
        this.login = loginDtoEntrada.getLogin();
        this.senha = loginDtoEntrada.getSenha();
    }

    public Login_Entity(Login_Dto_Atualiza_Login loginDtoAtualizaLogin){
        this.login = loginDtoAtualizaLogin.getLogin();
        this.senha = loginDtoAtualizaLogin.getNovoLogin();
    }


    public Login_Entity() {
    }

    public Login_Entity(Long id, String login, String senha, Acesso_Login acesso_login, Status_Login status_login) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.acesso_login = acesso_login;
        this.status_login = status_login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Acesso_Login getAcesso_login() {
        return acesso_login;
    }

    public void setAcesso_login(Acesso_Login acesso_login) {
        this.acesso_login = acesso_login;
    }

    public Status_Login getStatus_login() {
        return status_login;
    }

    public void setStatus_login(Status_Login status_login) {
        this.status_login = status_login;
    }
}
