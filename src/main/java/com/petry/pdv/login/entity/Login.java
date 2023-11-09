package com.petry.pdv.login.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petry.pdv.cliente.entity.Cliente;
import com.petry.pdv.funcionario.entity.Funcionario;
import com.petry.pdv.login.UserTypes;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(schema = "pdv", name = "login")
public class Login implements UserDetails{
	

	@Id
	@Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    
    @Column(name = "acessos")
    private String acessos;

    @Column(name = "userType")
    @Enumerated(EnumType.STRING)
    private UserTypes userType ;
    
    @Column(name = "idUser")
    private Long idUser; 
    
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
//    private Cliente cliente;
//
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
//    private Funcionario funcionario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.userType == UserTypes.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CLIENTE"), new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
		else if(this.userType == UserTypes.CLIENTE) return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"), new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
		else if(this.userType == UserTypes.FUNCIONARIO) return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
		else  return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
   

}