package com.artxp.artxp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
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
    @Column(name = "esta_activa")
    private boolean isEnabled;
    @Column(name = "cuenta_no_expiro")
    private boolean accountNoExpired;
    @Column(name = "cuenta_no_bloqueada")
    private boolean accountNoLocked;
    @Column(name = "credenciales_no_expirada")
    private boolean credentialNoExpired;
}
