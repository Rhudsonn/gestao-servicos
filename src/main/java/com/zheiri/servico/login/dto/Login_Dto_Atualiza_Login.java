package com.zheiri.servico.login.dto;

import com.zheiri.servico.login.entity.Login_Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Login_Dto_Atualiza_Login {

    @NotBlank(message = "é obrigatório !")
    private String login;


    @NotBlank(message = "é obrigatório !")
    @Size(min = 8, message = "mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "é obrigatório !")
    private String novoLogin;

    public Login_Dto_Atualiza_Login() {
    }

    public Login_Dto_Atualiza_Login(Login_Entity entity){
        this.login = entity.getLogin();
        this.senha = entity.getSenha();
        this.novoLogin = entity.getLogin();
    }

    public Login_Dto_Atualiza_Login(String login, String senha, String novoLogin) {
        this.login = login;
        this.senha = senha;
        this.novoLogin = novoLogin;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNovoLogin() {
        return novoLogin;
    }
}
