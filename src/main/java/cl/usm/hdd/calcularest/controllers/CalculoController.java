package cl.usm.hdd.calcularest.controllers;

import cl.usm.hdd.calcularest.entities.CalculoRequest;
import cl.usm.hdd.calcularest.entities.CalculoResponse;
import cl.usm.hdd.calcularest.exceptions.OperacionException;
import cl.usm.hdd.calcularest.services.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculoController {
    @Autowired
    private CalculoService calculoService;

    @PostMapping("/calcular")
    public ResponseEntity<CalculoResponse> calcular(@RequestBody CalculoRequest calculoRequest){
        try {
            double resultado = calculoService.calcular(calculoRequest.getNum1()
                    , calculoRequest.getNum2(), calculoRequest.getOperacion());
            CalculoResponse calculoResponse = new CalculoResponse();
            calculoResponse.setResultado(resultado);

            return ResponseEntity.ok(calculoResponse);
        }catch(OperacionException ex){
            return ResponseEntity.badRequest().body(null);
        }catch(Exception diego){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
