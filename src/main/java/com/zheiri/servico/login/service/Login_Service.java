package com.zheiri.servico.login.service;

import com.zheiri.servico.enums.Status_Login;
import com.zheiri.servico.login.dto.Login_Dto_Atualiza_Login;
import com.zheiri.servico.login.dto.Login_Dto_Atualiza_Senha;
import com.zheiri.servico.login.dto.Login_Dto_Entrada;
import com.zheiri.servico.login.entity.Login_Entity;
import com.zheiri.servico.login.repository.Login_Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Login_Service {

    private final Login_Repository login_Repository;
    public Login_Service(Login_Repository loginRepository) {
        login_Repository = loginRepository;
    }


    //Criar login
    public boolean criarLogin(Login_Dto_Entrada login_Dto_Entrada){

        if (login_Repository.existsByLogin(login_Dto_Entrada.getLogin())){
            throw new RuntimeException("login existente");
        }

       Login_Entity entity = new Login_Entity(login_Dto_Entrada);
       entity.setStatus_login(Status_Login.ATIVO);
        login_Repository.save(entity);
        return true;

    }


    //Validar login
    public boolean validarLogin(Login_Dto_Entrada login_Dto_Entrada) {

        Optional<Login_Entity> existe = login_Repository.findBySenhaAndLogin(login_Dto_Entrada.getSenha(), login_Dto_Entrada.getLogin());

        if (existe.isEmpty()){
            return false;
        }

        Login_Entity entity = existe.get();

        if (entity.getStatus_login() == Status_Login.INATIVO){
            throw new RuntimeException("login INATIVO");
        }
        return true;
    }


    // Atualizar login
    public boolean atualizarLogin(Login_Dto_Atualiza_Login loginDtoAtualizaLogin) {
        Optional<Login_Entity> existe = login_Repository.findBySenhaAndLogin(loginDtoAtualizaLogin.getSenha(), loginDtoAtualizaLogin.getLogin());

        if (existe.isEmpty()){
            return false;
        }

        Login_Entity entity = existe.get();

        if (loginDtoAtualizaLogin.getLogin().equals(loginDtoAtualizaLogin.getNovoLogin())){
            throw new RuntimeException("login compativel com atual");
        }
        if (login_Repository.existsByLogin(loginDtoAtualizaLogin.getNovoLogin())){
            throw new RuntimeException("login existente");
        }
        entity.setLogin(loginDtoAtualizaLogin.getNovoLogin());
        login_Repository.save(entity);
        return true;
    }



    // Atualizar Senha
    public boolean atualizarSenha(Login_Dto_Atualiza_Senha loginDtoAtualizaSenha){
        Optional<Login_Entity> existe = login_Repository.findBySenhaAndLogin(loginDtoAtualizaSenha.getSenha(), loginDtoAtualizaSenha.getLogin());


                if (existe.isEmpty()){
                    return false;
                }

               Login_Entity entity = existe.get();

                if (entity.getSenha().equals(loginDtoAtualizaSenha.getNovaSenha())){
                   throw new RuntimeException("senha combativel com senha atual");
                }
                if (!loginDtoAtualizaSenha.getNovaSenha().equals(loginDtoAtualizaSenha.getConfirmaSenha())){
                throw new RuntimeException("senha não coresponde com confirmação de senha");
                }

               entity.setSenha(loginDtoAtualizaSenha.getConfirmaSenha());
                login_Repository.save(entity);
                return true;
    }




   
    // Excluir login
    // Aqui não excluo o login apenas desativo.
    // Se o email e senha que o usuario informa estiverem corretas ele apenas desativa, o login mas não exclui.
    public boolean desativarLogin(Login_Dto_Entrada login_Dto_Entrada) {
        Optional<Login_Entity> existe = login_Repository.findBySenhaAndLogin(login_Dto_Entrada.getSenha(), login_Dto_Entrada.getLogin());

        if (existe.isEmpty()) {
            return false;
        }

        Login_Entity entity = existe.get();

        Status_Login status_login = Status_Login.INATIVO;
        entity.setStatus_login(status_login);
        login_Repository.save(entity);

        return true;
    }

}
