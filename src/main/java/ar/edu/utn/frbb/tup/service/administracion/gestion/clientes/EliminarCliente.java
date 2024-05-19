package ar.edu.utn.frbb.tup.service.administracion.gestion.clientes;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.service.administracion.gestion.BaseGestion;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.utn.frbb.tup.presentation.input.BaseInput.pedirDni;


public class EliminarCliente extends BaseGestion {

    //Eliminar cliente
    public void eliminarCliente(){
        List<Long> CvuEliminar = new ArrayList<>();

        boolean seguir = true;

        while (seguir) {
            long dni = pedirDni("Escriba el DNI al cliente que quiere eliminar: (0 para salir) ");

            if (dni == 0) break; //Si escribe 0 termina con el bucle

            //Funcion que devuelve el cliente encontrado o vuelve Null si no lo encontro
            Cliente cliente = clienteDao.findCliente(dni);

            if (cliente == null) {
                System.out.println("No existe ningun cliente con el DNI ingresado");
            } else {

                System.out.println("------------ Cliente eliminado -----------");
                System.out.println(toString(cliente)); //Muestro en pantalla el cliente eliminado

                //Elimino el cliente, las relaciones que tienen esas cuentas, las cuentas, y movimientos que tiene en los archivos.txt
                clienteDao.deleteCliente(cliente.getDni());
                CvuEliminar = cuentasDeClientes.getRelacionesDni(cliente.getDni()); //Obtengo lista de los cvu que estan relacionados con el CVU

                for (Long cvu : CvuEliminar){
                    cuentaDao.deleteCuenta(cvu);
                    cuentasDeClientes.deleteRelacion(cvu);
                    movimientosDao.deleteMovimiento(cvu);
                }

                seguir = false;
            }

            System.out.println("Enter para seguir");
            scanner.nextLine();
            clearScreen();
        }
    }
}
