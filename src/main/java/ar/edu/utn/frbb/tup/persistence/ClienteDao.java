package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ClienteDao extends BaseDao<Cliente>{
    private final String RUTA_ARCHIVO = "src/main/java/ar/edu/utn/frbb/tup/persistence/data/cliente.txt";

    public void inicializarClientes(){
        String encabezado = "DNI, Nombre, Apellido, Direccion, Fecha nacimiento, Mail, Banco, Tipo Persona, Fecha alta";
        inicializarArchivo(encabezado, RUTA_ARCHIVO);
    }

    public void saveCliente(Cliente cliente) {

        String infoAguardar = cliente.getDni() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getDireccion() + "," + cliente.getFechaNacimiento() + "," + cliente.getMail() + "," + cliente.getBanco() + "," + cliente.getTipoPersona() + "," + cliente.getFechaAlta();

        saveInfo(infoAguardar, RUTA_ARCHIVO);
    }

    public void deleteCliente(Long dni) {

        deleteInfo(dni, RUTA_ARCHIVO);

    }

    public Cliente findCliente(Long dni){

        return findInfo(dni, RUTA_ARCHIVO);
    }

    public List<Cliente> findAllClientes() {

        return findAllInfo(RUTA_ARCHIVO);
    }

    //Funcion para parsear los datos leidos del archivo a un objeto tipo 'Cliente'
    @Override
    public Cliente parseDatosToObjet(String[] datos){
        Cliente cliente = new Cliente();

        cliente.setDni(Long.parseLong(datos[0]));
        cliente.setNombre(datos[1]);
        cliente.setApellido(datos[2]);
        cliente.setDireccion(datos[3]);
        cliente.setFechaNacimiento(LocalDate.parse(datos[4]));
        cliente.setMail(datos[5]);
        cliente.setBanco(datos[6]);
        cliente.setTipoPersona(TipoPersona.valueOf(datos[7]));
        cliente.setFechaAlta(LocalDate.parse(datos[8]));

        //Retorno el cliente leido
        return cliente;
    }
}
