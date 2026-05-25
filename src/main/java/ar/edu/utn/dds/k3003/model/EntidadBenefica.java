package ar.edu.utn.dds.k3003.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "entidad_benefica")
public class EntidadBenefica {
    @Id
    private String id;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;

    @PrePersist
    public void generarIdAutomatico() {
        if (this.id == null) {
            this.id = com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId();
        }
    }

    public EntidadBenefica(String razonSocial,
                           String domicilio,
                           String telefono,
                           String correo) {

        this.razonSocial = razonSocial;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correo = correo;
    }
}