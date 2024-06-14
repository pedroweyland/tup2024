package ar.edu.utn.frbb.tup.service.operaciones.modulos;

import ar.edu.utn.frbb.tup.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.persistence.MovimientosDao;
import ar.edu.utn.frbb.tup.service.operaciones.baseOperaciones;
import org.springframework.stereotype.Service;

import static ar.edu.utn.frbb.tup.presentation.BasePresentation.ingresarDinero;

@Service
public class Deposito extends baseOperaciones {
    private final CuentaDao cuentaDao;
    private final MovimientosDao movimientosDao;
    private final String tipoOperacion = "Deposito";

    public Deposito(CuentaDao cuentaDao, MovimientosDao movimientosDao) {
        this.cuentaDao = cuentaDao;
        this.movimientosDao = movimientosDao;
    }

    public double deposito(long cvu , double monto) throws CuentaNoEncontradaException {

        Cuenta cuenta = cuentaDao.findCuenta(cvu);

        if (cuenta == null){
            throw new CuentaNoEncontradaException("No se encontro ninguna cuenta con el CVU dado " + cvu);
        }

        cuentaDao.deleteCuenta(cuenta.getCVU()); //Borro la cuenta ya que va ser modificada

        //Sumo el monto al saldo que tenia la cuenta
        cuenta.setSaldo(cuenta.getSaldo() + monto); 

        //Tomo registro de la operacion que se hizo
        Movimiento movimiento = crearMovimiento(tipoOperacion, monto, cuenta.getCVU());
        movimientosDao.saveMovimiento(movimiento);

        cuentaDao.saveCuenta(cuenta); //Guardo la cuenta modificada

        return cuenta.getSaldo();

    }
}
