package ar.edu.utn.frbb.tup.presentation;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static ar.edu.utn.frbb.tup.presentation.validator.Validaciones.esDouble;
import static ar.edu.utn.frbb.tup.presentation.validator.Validaciones.esNumeroLong;

@Component
public class BasePresentation {

    protected static Scanner scanner = new Scanner(System.in);

    //Metodo limpiar consola
    protected static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static long pedirDni(String texto){
        //Funcion para que el usuario ingrese dni, valido y devuelve en tipo 'long'
        long dni;
        while (true){
            try {
                System.out.println(texto);
                String dniStr = scanner.nextLine();

                dni = esNumeroLong(dniStr);
                //Cuando ingrese 0 no queremos que tire exception ya que el usuario va querer salir
                if (dni != 0 && (dni < 10000000 || dni > 99999999)) {
                    throw new NumberFormatException("El dni debe tener 8 digitos");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("---------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("---------------------------------------");
            }
        }
        return dni;
    }

    public static long pedirCvu(String texto){
        //Funcion para que el usuario ingrese Cvu de cuenta, valido y devuelve en tipo 'long';

        long cvu;
        while (true){
            try {
                System.out.println(texto);
                String dniStr = scanner.nextLine();

                cvu = esNumeroLong(dniStr);

                if (cvu != 0 && (cvu < 100000 || cvu > 999999)) {
                    throw new NumberFormatException("El dni debe tener 6 digitos");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("---------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("---------------------------------------");
            }
        }
        return cvu;
    }

    public static boolean pedirOpcion(String texto){
        //Pido opcion para ver si quiere dar de baja o de alta

        System.out.println(texto);
        String opcion = scanner.nextLine();

        //Si ingresa otro tipo de letra vuelve error
        while (!opcion.equalsIgnoreCase("a") && !opcion.equalsIgnoreCase("b")){
            System.out.println("Error, ingrese A (Alta) o B (Baja)");
            opcion = scanner.nextLine();
        }

        return opcion.equalsIgnoreCase("a");
    }

    public static double ingresarDinero(String mensaje) {

        while (true) {

            try {
                System.out.println(mensaje); //Usuario ingresado la cantidad de dinero
                String dineroStr = scanner.nextLine();

                double dinero = esDouble(dineroStr); //Valido que sea un numero

                if (dinero <= 0) { //Si el dinero es menor a 0, vuelve a pedir
                    System.out.println("El valor ingresado debe ser mayor a 0");
                } else {
                    return dinero;
                }

            } catch (NumberFormatException e) {
                System.out.println("---------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("---------------------------------------");
            }
        }
    }

    //Dependiendo que tipo de parametro se le pase usa una funcion o la otra
    public String toString(Cliente cliente, String tipoMuestra) {
        return  tipoMuestra + "\n" +
                "Nombre: " + cliente.getNombre() + "\n" +
                "Apellido: " + cliente.getApellido() + "\n" +
                "Direccion: " + cliente.getDireccion() + "\n" +
                "Dni: " + cliente.getDni() + "\n" +
                "Fecha de nacimiento: " + cliente.getFechaNacimiento() + "\n" +
                "Tipo de persona: " + cliente.getTipoPersona() + "\n" +
                "Banco: " + cliente.getBanco() + "\n" +
                "Mail: " + cliente.getMail() + "\n" +
                "Fecha de alta: " + cliente.getFechaAlta() + "\n" +
                "----------------------------------------";
    }

    public String toString(Cuenta cuenta, String tipoMuestra){
        return  tipoMuestra + "\n" +
                "Nombre de la cuenta: " + cuenta.getNombre() + "\n" +
                "Estado: " + cuenta.getEstado() + "\n" +
                "Saldo: " + cuenta.getSaldo() + "\n" +
                "CVU: " + cuenta.getCVU() + "\n" +
                "DNI titular: " + cuenta.getDniTitular() + "\n" +
                "Fecha de Creacion: " + cuenta.getFechaCreacion() + "\n" +
                "Tipo de cuenta: " + cuenta.getTipoCuenta() + "\n" +
                "Tipo de Moneda: " + cuenta.getTipoMoneda() + "\n" +
                "----------------------------------------";
    }

    public String toString(Movimiento movimiento){
        return "Fecha de operacion: " + movimiento.getFechaOperacion() + "\n" +
                "Hora de operacion: " + movimiento.getHoraOperacion() + "\n" +
                "Tipo de movimiento: " + movimiento.getTipoOperacion() + "\n" +
                "Monto del movimiento: " + movimiento.getMonto() + "\n" +
                "----------------------------------------";
    }
}
