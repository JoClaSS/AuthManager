package com.erp.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.erp.usuario.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Query(value = "SELECT * FROM usuario u WHERE (u.login= :login)", nativeQuery = true)
	Usuario findByLoginUsuario(@Param("login") String login);
	
	UserDetails findByLogin(String login);


	
}
