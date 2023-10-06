package cl.usm.hdd.calcularest.services;

import cl.usm.hdd.calcularest.exceptions.OperacionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoServiceImplTest {
    CalculoService cs;
    @BeforeEach
    void init(){
        cs = new CalculoServiceImpl();
    }
    @Test
    void calcularSuma() throws OperacionException {

        double res =cs.calcular(1,2,"+");
        assertEquals(3,res);
    }

    @Test
    void calcularResta() throws OperacionException {

        double res = cs.calcular(2,2,"-");
        assertEquals(0,res);
    }

    @Test
    void calcularMultiplicacion() throws OperacionException{
        double  res = cs.calcular(4,2,"*");
        assertEquals(8,res);
    }

    @Test
    void calcularDivisionOk() throws OperacionException{
        double res = cs.calcular(4,2,"/");
        assertEquals(2,res);
    }

    @Test
    void calcularDivisionNok(){

        boolean ok = true;
        try{
            double res =cs.calcular(1,0,"/");
        }catch(Exception ex){
            ok = false;
        }

        assertFalse(ok);
    }

    @Test
    void calcularOperacionNok(){

        boolean ok = true;
        try{
            cs.calcular(1,1,"falsa");
        }catch(Exception ex){
            ok = false;
        }

        assertFalse(ok);

    }


}