package cl.usm.hdd.calcularest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CalculoRequest {

    private int num1;
    private int num2;
    private String operacion;
}
