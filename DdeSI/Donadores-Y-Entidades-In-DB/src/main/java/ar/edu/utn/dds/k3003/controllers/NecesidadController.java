package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.NecesidadMaterialDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/necesidades")
public class NecesidadController {

    private Fachada fachada;

    public NecesidadController(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping
    public ResponseEntity<NecesidadMaterialDTO> postMaterial(@RequestBody NecesidadMaterialDTO necesidadMaterialDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fachada.registrarNecesidad(necesidadMaterialDTO));
    }

    @GetMapping()
    public ResponseEntity<List<NecesidadMaterialDTO>> getAllNecesidades() {
        return ResponseEntity.status(HttpStatus.OK).body(this.fachada.obtenerTodasLasNecesidades());
    }

    @GetMapping("/{productoID}")
        public ResponseEntity<List<NecesidadMaterialDTO>> getAllNecesidadesDeUnProducto(@PathVariable String productoID) {
            return ResponseEntity.status(HttpStatus.OK).body(this.fachada.obtenerNecesidadesInsatisfechasDe(productoID));
        }

    @DeleteMapping("/{necesidadID}")
    public ResponseEntity<Void> deleteEntidad(@PathVariable String necesidadID) {
        this.fachada.quitarNecesidad(necesidadID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{necesidadID}/satisfaccion")
    public ResponseEntity<NecesidadMaterialDTO> postSatisfacerNecesidad(@PathVariable String necesidadID, @RequestBody Map<String, Integer> requestBody) {
        Integer cantidad = requestBody.get("cantidad");
        return ResponseEntity.status(HttpStatus.OK).body(this.fachada.satisfacerNecesidad(necesidadID,cantidad));
    }
}
