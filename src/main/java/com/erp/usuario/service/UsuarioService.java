package com.erp.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.erp.usuario.models.Usuario;
import com.erp.usuario.repository.UsuarioRepository;
import com.erp.usuario.utils.AbstractSaveUpdateTemplate;

@Service
public class UsuarioService extends AbstractSaveUpdateTemplate<Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void executeSave(Usuario usuario) {
		this.usuarioRepository.save(usuario);
		
	}
	
	public UserDetails  findByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public Usuario findByLoginUsuario(String login) {
		return usuarioRepository.findByLoginUsuario(login);
	}
	

}
