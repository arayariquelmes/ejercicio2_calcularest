package cl.usm.hdd.calcularest.controllers;

import cl.usm.hdd.calcularest.entities.CalculoRequest;
import cl.usm.hdd.calcularest.services.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculoController {
    @Autowired
    private CalculoService calculoService;

    @PostMapping("/calcular")
    public double calcular(@RequestBody CalculoRequest calculoRequest){
        double resultado = calculoService.calcular(calculoRequest.getNum1()
                , calculoRequest.getNum2(), calculoRequest.getOperacion());
        return resultado;
    }
}
