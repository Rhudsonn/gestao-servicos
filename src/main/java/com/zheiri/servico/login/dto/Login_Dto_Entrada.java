package com.zheiri.servico.login.dto;

import com.zheiri.servico.login.entity.Login_Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Login_Dto_Entrada {


    @NotBlank(message = "é obrigatório !")
    private String login;


    @NotBlank(message = "é obrigatório !")
    @Size(min = 8, message = "mínimo 8 caracteres")
    private String senha;

    // Tranformando Dto em entity
    public Login_Dto_Entrada(Login_Entity  entity){
        this.login = entity.getLogin();
        this.senha = entity.getSenha();
    }


    public Login_Dto_Entrada() {
    }

    public Login_Dto_Entrada(String login, String senha) {
        this.login = login;
        this.senha = senha;
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
}
