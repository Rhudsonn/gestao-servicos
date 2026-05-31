package com.zheiri.servico.login.controller;


import com.zheiri.servico.login.dto.Login_Dto_Atualiza_Login;
import com.zheiri.servico.login.dto.Login_Dto_Atualiza_Senha;
import com.zheiri.servico.login.dto.Login_Dto_Entrada;
import com.zheiri.servico.login.service.Login_Service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class Login_Controller {

    private final Login_Service login_Service;

    public Login_Controller(Login_Service login_Service) {
        this.login_Service = login_Service;
    }


    //Criar login
    @PostMapping
    public ResponseEntity<?> criarLogin(@Valid @RequestBody Login_Dto_Entrada login_Dto_Entrada) {

        try {
            boolean retorno = login_Service.criarLogin(login_Dto_Entrada);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Login cadastrado com sucesso");
            }
        }catch (Exception e){
            if ("login existente".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login existente");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar login");
    }

    //Validar login
    @GetMapping
    public ResponseEntity<?> validarLogin(@RequestBody  @Valid Login_Dto_Entrada login_Dto_Entrada){
        //boolean retorno = login_Service.validarLogin(login_Dto_Entrada);

        try {
            boolean retorno = login_Service.validarLogin(login_Dto_Entrada);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Login valido");
            }
        }catch (Exception e){
            if ("login INATIVO".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login esta inativo");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login invalido");

        //if (retorno){
          //  return ResponseEntity.status(HttpStatus.OK).body("Login valido");
        //}else {
           // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login invalido");
       // }
    }


    //Atualizar login
    @PutMapping("atualiza/login")
    public ResponseEntity<?> atualizarLogin(@RequestBody @Valid Login_Dto_Atualiza_Login loginDtoAtualizaLogin){
        try {
            boolean retorno = login_Service.atualizarLogin(loginDtoAtualizaLogin);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Login atualizado com sucesso");
            }
        }catch (Exception e){
            if ("login compativel com atual".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login compativel com atual");
            }
            if ("login existente".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("login ja existente");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login invalido");
    }


    // Atualizar Senha
    @PutMapping("atualiza/senha")
    public ResponseEntity<?> atualizarSenha(@RequestBody @Valid Login_Dto_Atualiza_Senha loginDtoAtualizaSenha){
        try {
            boolean retorno = login_Service.atualizarSenha(loginDtoAtualizaSenha);
            if (retorno){
                return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso");
            }
        }catch (Exception e){
            if ("senha combativel com senha atual".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("senha combativel, com senha atual");
            }
            if ("senha não coresponde com confirmação de senha".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("senha não coresponde com confirmação de senha");
            }
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao atualizar senha");
    }

    //Excluir login
    @DeleteMapping
    public ResponseEntity<?> desativarLogin(@RequestBody @Valid Login_Dto_Entrada login_Dto_Entrada){
        boolean retorno = login_Service.desativarLogin(login_Dto_Entrada);
        if (retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Login desativado com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login invalido");
        }
    }


}
