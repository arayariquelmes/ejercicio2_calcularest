package cl.usm.hdd.calcularest.services;

import org.springframework.stereotype.Service;

@Service
public class CalculoServiceImpl implements CalculoService {
    @Override
    public double calcular(int num1, int num2, String operacion) {
        //TODO: Que pasa con el manejo de errores?
        switch(operacion){
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1*num2;
            case "/": return (double)num1/(double)num2;
            default: return 0;
        }
    }
}
