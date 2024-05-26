package ar.edu.utn.frbb.tup.service.administracion.gestion.clientes;

import ar.edu.utn.frbb.tup.service.exception.ClienteExistenteException;
import ar.edu.utn.frbb.tup.service.administracion.gestion.BaseGestion;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.presentation.input.ClienteInput;

public class CrearCliente extends BaseGestion {
    ClienteInput clienteInput = new ClienteInput();

    // Creacion Cliente - la creacion del cliente y el guardado de este mismo
    public void crearCliente(){
        //Usuario ingresa los datos y se guarda en la variable cliente
        Cliente cliente = clienteInput.ingresoCliente();

        crearCliente(cliente);
    }


    public void crearCliente(Cliente cliente) {

        try {
            //Primero busco si el cliente ya existia previamente, si existia lanzo una excepcion
            Cliente existente = clienteDao.findCliente(cliente.getDni());

            if (existente != null){
                throw new ClienteExistenteException("El cliente ya existe");
            }

            //Guardo el cliente ingresado, si ya existe se lanza la excepcion por ende no se guarda y el catch agarra la excepcion
            clienteDao.saveCliente(cliente);
            System.out.println("------- Cliente creado con exito -------");
            System.out.println(toString(cliente));

        } catch (ClienteExistenteException ex) {
            System.out.println("----------------------------------------");
            System.out.println(ex.getMessage());
            System.out.println("----------------------------------------");
        }
        finally {
            System.out.println("Enter para seguir");
            scanner.nextLine();
            clearScreen();
        }

    }
}
