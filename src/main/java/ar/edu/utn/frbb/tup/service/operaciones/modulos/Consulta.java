package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.persistence.MovimientosDao;
import ar.edu.utn.frbb.tup.service.operaciones.baseOperaciones;
import org.springframework.stereotype.Service;

@Service
public class Consulta extends baseOperaciones {
    MovimientosDao movimientosDao;

    public Consulta(MovimientosDao movimientosDao) {
        this.movimientosDao = movimientosDao;
    }

    private final String tipoOperacion = "Consulta";

    public void consulta(Cuenta cuenta){

        System.out.println("----------------------------------------");
        System.out.println("Su saldo es de la cuenta " + cuenta.getNombre() + " es de $" + cuenta.getSaldo());
        System.out.println("----------------------------------------");

        System.out.println("Enter para seguir");
        scanner.nextLine();
        clearScreen();

        //Tomo registro de la operacion que se hizo
        Movimiento movimiento = crearMovimiento(tipoOperacion, 0, cuenta.getCVU());
        movimientosDao.saveMovimiento(movimiento);
    }
}
