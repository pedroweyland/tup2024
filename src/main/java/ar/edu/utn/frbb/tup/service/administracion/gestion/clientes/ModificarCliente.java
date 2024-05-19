package ar.edu.utn.frbb.tup.service.administracion.gestion.clientes;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.service.administracion.gestion.BaseGestion;
import ar.edu.utn.frbb.tup.presentation.input.ClienteInput;

import static ar.edu.utn.frbb.tup.presentation.input.BaseInput.pedirDni;
import static ar.edu.utn.frbb.tup.presentation.input.Menus.menuModificacion;

public class ModificarCliente extends BaseGestion {

    // Modificacion Cliente
    public void modificarCliente() {
        boolean salir = false;

        while (!salir) {
            long dni = pedirDni("Escriba el DNI para el cliente que quiere modificar: (0 para salir)");

            if (dni == 0) break;

            clearScreen();

            //Funcion que devuelve el cliente encontrado o vuelve Null si no lo encontro
            Cliente cliente = clienteDao.findCliente(dni);

            if (cliente == null) {
                System.out.println("No existe ningun cliente con el DNI ingresado");
            } else {

                clienteDao.deleteCliente(dni); //Elimino el cliente Viejo del archivo


                while (!salir) {
                    int opcion = menuModificacion();  //Usuario ingresa que quiere modificar

                    //Creo intancia de cliente Input para que el usuario modifique lo que eligio
                    ClienteInput mod = new ClienteInput();

                    //Switch para modifcar los datos del cliente
                    switch (opcion) {
                        case 1: //Nombre
                            cliente.setNombre(mod.ingresarNombre());
                            System.out.println("Nombre modificado correctamente");
                            break;
                        case 2: //Apellido
                            cliente.setApellido(mod.ingresarApellido());
                            System.out.println("Apellido modificado correctamente");
                            break;
                        case 3: //Direccion
                            cliente.setDireccion(mod.ingresarDireccion());
                            System.out.println("Direccion modificado correctamente");
                            break;
                        case 4: //Tipo de persona
                            cliente.setTipoPersona(mod.ingresarTipoPersona());
                            System.out.println("Tipo persona modificado correctamente");
                            break;
                        case 5: //Banco
                            cliente.setBanco(mod.ingresarBanco());
                            System.out.println("Banco modificado correctamente");
                            break;
                        case 6: //Mail
                            cliente.setMail(mod.ingresarMail());
                            System.out.println("Mail modificado correctamente");
                            break;
                        case 0:
                            //Guardo el cliente modificado en el archivo
                            clienteDao.saveCliente(cliente);
                            salir = true;
                            break;
                    }
                }
            }
        }
    }

}
