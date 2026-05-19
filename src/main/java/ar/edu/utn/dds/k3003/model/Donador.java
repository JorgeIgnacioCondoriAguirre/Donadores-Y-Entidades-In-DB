package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EstadoDonadorEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "donador")
public class Donador {
  @Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "apellido")
  private String apellido;
  @Column(name = "edad")
  private Integer edad;
  @Column(name = "email")
  private String email;
  @Column(name = "nro_documento")
  private String nroDocumento;
  @Column(name = "domicilio")
  private String domicilio;
  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private EstadoDonadorEnum estado;
  @Column(name = "categoria")
  private String categoria;

  public Donador(
      String nombre,
      String apellido,
      Integer edad,
      String email,
      String nroDocumento,
      String domicilio) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.email = email;
    this.nroDocumento = nroDocumento;
    this.domicilio = domicilio;
    this.estado = EstadoDonadorEnum.VERIFICADO;
    this.categoria = "Ocasional";
  }
}
