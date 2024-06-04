package ar.edu.utn.frbb.tup.service.administracion.cuentas;

import ar.edu.utn.frbb.tup.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.service.administracion.BaseAdministracion;
import ar.edu.utn.frbb.tup.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.exception.CuentasVaciasException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MostrarCuenta extends BaseAdministracion {
    ClienteDao clienteDao;
    CuentaDao cuentaDao;

    public MostrarCuenta(ClienteDao clienteDao, CuentaDao cuentaDao) {
        this.clienteDao = clienteDao;
        this.cuentaDao = cuentaDao;
    }

    public List<Cuenta> mostrarCuenta(long dni) {

        //Funcion que devuelve el cliente encontrado o vuelve Null si no lo encontro
        Cliente cliente = clienteDao.findCliente(dni);

        try {
            if (cliente == null) {
                //Lanzo excepcion si el cliente no fue encontrado
                throw new ClienteNoEncontradoException("No se encontro el cliente con el DNI: " + dni);
            }

            //Me devuelve toda la lista de cuentas que hay
            List<Cuenta> cuentas = cuentaDao.findAllCuentas();

            List<Cuenta> aux = new ArrayList<>(); //Lista auxiliar para guardar las cuentas del cliente

            boolean encontrada = false;
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getDniTitular() == dni) {
                    aux.add(cuenta);
                    encontrada = true;
                }
            }

            if (!encontrada){
                throw new CuentaNoEncontradaException("No hay cuentas asociadas al cliente con DNI: " + dni);
            }
            return aux;

        } catch (ClienteNoEncontradoException | CuentasVaciasException | CuentaNoEncontradaException e) {
            System.out.println("----------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("----------------------------------------");
        }
        return null;
    }
}