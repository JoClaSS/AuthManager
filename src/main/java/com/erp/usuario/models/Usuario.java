package com.erp.usuario.models;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "log")
	private Integer logRegister;

	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "role")
	private UserRole role;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) 
		   return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else return List.of(new SimpleGrantedAuthority("ROL_USER"));
	}
	
	  public Usuario(String login, String password, UserRole role){
	        this.login = login;
	        this.password = password;
	        this.role = role;
	    }

	@Override
	public String getUsername() {
		return login;
	}
	
	

}
