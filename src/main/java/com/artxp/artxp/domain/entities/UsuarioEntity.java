package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
//public class UsuarioEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(name = "nombre")
//    private String name;
//    @Column(name = "apellido")
//    private String lastname;
//    @Column(unique = true)
//    private String email;
//    @Column(name = "contrasena")
//    private String password;
//    @Column(name = "esta_activa")
//    private boolean isEnabled;
//    @Column(name = "cuenta_no_expiro")
//    private boolean accountNoExpired;
//    @Column(name = "cuenta_no_bloqueada")
//    private boolean accountNoLocked;
//    @Column(name = "credenciales_no_expirada")
//    private boolean credentialNoExpired;
//}

public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String lastname;
    @Column(unique = true)
    private String email;
    @Column(name = "contrasena")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role rol;

    //Configurar permisos o roles que va a tener el usurio
    //Con Granted autority los roles y los permisos viene mezclado
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

