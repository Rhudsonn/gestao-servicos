package com.zheiri.servico.login.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Login_Dto_Atualiza_Senha {

    @NotBlank(message = "é obrigatório !")
    private String login;

    @Min(value = 8, message = "mínimo 8 caracteres")
    @NotBlank(message = "é obrigatório !")
    private String senha;


    @NotBlank(message = "é obrigatório !")
    @Size(min = 8, message = "mínimo 8 caracteres")
    private String novaSenha;

    @NotBlank(message = "é obrigatório !")
    private String confirmaSenha;

    public Login_Dto_Atualiza_Senha() {
    }

    public Login_Dto_Atualiza_Senha(String login, String senha, String novaSenha, String confirmaSenha) {
        this.login = login;
        this.senha = senha;
        this.novaSenha = novaSenha;
        this.confirmaSenha = confirmaSenha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }
}
