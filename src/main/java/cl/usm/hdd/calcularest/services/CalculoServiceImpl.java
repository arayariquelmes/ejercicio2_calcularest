package cl.usm.hdd.calcularest.services;

import cl.usm.hdd.calcularest.exceptions.OperacionException;
import org.springframework.stereotype.Service;

@Service
public class CalculoServiceImpl implements CalculoService {
    @Override
    public double calcular(int num1, int num2, String operacion) throws OperacionException {
        //TODO: Que pasa con el manejo de errores?
        switch(operacion){
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1*num2;
            case "/":
                if(num2 == 0){
                    throw new OperacionException();
                }
                return (double)num1/(double)num2;
            default: throw new OperacionException();
        }
    }
}
