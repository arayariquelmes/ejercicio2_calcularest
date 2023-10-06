package cl.usm.hdd.calcularest.controllers;

import cl.usm.hdd.calcularest.entities.CalculoRequest;
import cl.usm.hdd.calcularest.entities.CalculoResponse;
import cl.usm.hdd.calcularest.exceptions.OperacionException;
import cl.usm.hdd.calcularest.services.CalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculoControllerTest {

    @Mock
    private CalculoService calculoService;


    private CalculoController calculoController;
    /**
     * Este metodo se ejecuta antes de cada prueba
     * (Las pruebas como los metodos que tienen @Test)
     */
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.calculoController = new CalculoController(calculoService);
    }

    /**
     * Esta prueba va a evaluar que si el controlador se llama con parametros
     * validos, responda ResponseEntity.ok
     */
    @Test
    void calcularOk() throws OperacionException {
        when(calculoService
                .calcular(anyInt(),anyInt(),anyString())).thenReturn(1.0);
        CalculoRequest requestTest = new CalculoRequest();
        requestTest.setNum1(5);
        requestTest.setNum2(7);
        requestTest.setOperacion("*");
        ResponseEntity<CalculoResponse> res
                = calculoController.calcular(requestTest);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(1.0, res.getBody().getResultado());
    }

    /**
     * Esto prueba que cuando el servicio lanse un OperationException
     * el controlador responda una bad request
     * @throws OperacionException
     */
    @Test
    void calcularOperacionExceptionFail()throws OperacionException{
        when(calculoService
                .calcular(anyInt(), anyInt(),anyString()))
                .thenThrow(new OperacionException());
        CalculoRequest request = new CalculoRequest();
        request.setNum1(5);
        request.setNum2(7);
        request.setOperacion("*");
        ResponseEntity<CalculoResponse> res
                = calculoController.calcular(request);
        assertEquals(HttpStatus.BAD_REQUEST
                , res.getStatusCode());
    }

    /**
     * Esto va a evaluar que cuando el calculoService
     * se caiga por algo no controlado, el controlador
     * debe retornar una InternalServerError
     */
    @Test
    void calcularOtherExceptionsFail() throws OperacionException{
        when(calculoService
                .calcular(anyInt(), anyInt(), anyString()))
                .thenThrow(new NullPointerException());
        CalculoRequest request = new CalculoRequest();
        request.setNum1(2);
        request.setNum2(4);
        request.setOperacion("/");
        ResponseEntity<CalculoResponse> res = calculoController
                .calcular(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR
                , res.getStatusCode());
    }

}