package ar.edu.utn.dds.k3003.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "queja")
public class Queja{
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;
    @Column(name = "donacion_id")
        private String donacionID;
    @Column(name = "donador_id")
        private String donadorID;
    @Column(name = "fecha")
        private LocalDate fecha;
    @Column(name = "descripcion")
        private String descripcion;

    public Queja(String donacionID, String donadorID, LocalDate fecha, String descripcion) {
        this.donacionID = donacionID;
        this.donadorID = donadorID;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
}
