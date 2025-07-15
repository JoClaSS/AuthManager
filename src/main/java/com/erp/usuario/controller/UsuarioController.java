package com.erp.usuario.controller;

import java.util.Optional;

import com.erp.usuario.models.*;
import com.erp.usuario.service.PessoaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

import com.erp.usuario.security.TokenService;
import com.erp.usuario.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private final UsuarioService usuarioService;

    @Autowired
    private PessoaService pessoaService;
	
	@Autowired
    private TokenService tokenService;

	@GetMapping("/{login}")
	public UserDetails findUsuarioByLogin(@RequestParam String login){
		return this.usuarioService.findByLogin(login);
	}
	
	@PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }
//
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistroDTO data){
        if(this.usuarioService.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());
        Pessoa newPessoa = this.pessoaService.salvarPessoa(data.pessoa());
        newUser.setPessoa(newPessoa);
        this.usuarioService.executeSave(newUser);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/me")
    public ResponseEntity<UserDetails> getMe(Authentication auth){
        String username = auth.getName();
        UserDetails user = usuarioService.findByLogin(username);
        return ResponseEntity.ok(user);
    }



}




