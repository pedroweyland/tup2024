package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.presentation.BasePresentation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;


@Component
public class CuentaInput extends BasePresentation {

    public Cuenta creacionCuenta(long dniTitular){
        Random r = new Random();
        Cuenta cuenta = new Cuenta();
        //Creacion de cuenta para el cliente

        clearScreen();

        cuenta.setNombre(ingresarNombre());

        //Inicializo los valores de la cuenta NUEVA
        cuenta.setEstado(true);
        cuenta.setSaldo(0);

        cuenta.setCVU(generarCVU(r)); //Creo un CVU random
        cuenta.setDniTitular(dniTitular);

        cuenta.setFechaCreacion(LocalDate.now());
        cuenta.setTipoCuenta(ingresarTipoCuenta());
        return cuenta;

    }

    public String ingresarNombre() {
        System.out.println("Ingrese el nombre de la cuenta: ");
        return scanner.nextLine();
    }

    public TipoCuenta ingresarTipoCuenta(){
        String tipoCuentaStr = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("Ingrese el tipo de cuenta Corriente(C) o Ahorro(A): ");
                tipoCuentaStr = scanner.nextLine().toUpperCase();
                return TipoCuenta.fromString(tipoCuentaStr); //Retorno el tipo persona
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, ingrese un valor valido (C o A).");
            }
        }
        return null;
    }

    public long generarCVU(Random r) {
        return r.nextInt(900000) + 100000; //Genera numero aleatorio entre 100000 y 999999
    }
}
