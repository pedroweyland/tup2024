package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.service.exception.CuentaEstaDeBajaException;
import ar.edu.utn.frbb.tup.service.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.service.exception.CuentaSinDineroException;
import ar.edu.utn.frbb.tup.service.exception.MismaCuentaException;
import ar.edu.utn.frbb.tup.service.operaciones.baseOperaciones;

import static ar.edu.utn.frbb.tup.presentation.input.BaseInput.ingresarDinero;

public class Transferencia extends baseOperaciones {
    private final String tipoOperacion = "Transferencia a la cuenta ";
    private final String tipoOperacionDestino = "Deposito recibido de la cuenta ";

    public void transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino){

        try {
            if (cuentaOrigen.getCVU() == cuentaDestino.getCVU()){ ////Lanzo excepcion cuando la cuenta destino es igual a la origen
                throw new MismaCuentaException("No se puede transferir a la misma cuenta");
            }
            if (!cuentaDestino.getEstado()){ //Lanzo excepcion cuando la cuenta destino esta dada de baja
                throw new CuentaEstaDeBajaException("La cuenta a transferir esta dada de baja");
            }

            double monto = ingresarDinero("Ingrese el monto a transferir a la cuenta " + cuentaDestino.getNombre() + ": ");

            if (monto > cuentaOrigen.getSaldo()){ //Lanzo excepcion cuando no tiene dinero para trnsferir
                throw new CuentaSinDineroException("No hay suficiente dinero en la cuenta " + cuentaOrigen.getNombre() + ", su saldo es de $" + cuentaOrigen.getSaldo());
            }

            System.out.println("----------------------------------------");
            //Borro la cuentaOrigen ya que va ser modificada
            cuentaDao.deleteCuenta(cuentaOrigen.getCVU());
            cuentaDao.deleteCuenta(cuentaDestino.getCVU());

            //Resto el monto a la cuenta origen y sumo a la que se envia
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

            //Tomo registro de la transferencia en la cuentaOrginen y destino
            Movimiento movimiento = crearMovimiento(tipoOperacion + cuentaDestino.getNombre(), monto, cuentaOrigen.getCVU());
            movimientosDao.saveMovimiento(movimiento);

            Movimiento movimientoDestino = crearMovimiento(tipoOperacionDestino + cuentaOrigen.getNombre(), monto, cuentaDestino.getCVU());
            movimientosDao.saveMovimiento(movimientoDestino);

            //Muestro en pantalla la transferencia
            System.out.println("Se ha transferido " + monto + " a la cuenta " + cuentaDestino.getNombre());

            //Guardo la cuentaOrigen y cuentaDestino modificada
            cuentaDao.saveCuenta(cuentaOrigen);
            cuentaDao.saveCuenta(cuentaDestino);

            System.out.println("----------------------------------------");

        } catch (CuentaSinDineroException | MismaCuentaException | CuentaEstaDeBajaException e) {
            System.out.println("----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------");
        } finally {
            System.out.println("Enter para seguir");
            scanner.nextLine();
            clearScreen();
        }

    }
}
