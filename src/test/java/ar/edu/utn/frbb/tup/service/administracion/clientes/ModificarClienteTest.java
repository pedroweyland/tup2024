package ar.edu.utn.frbb.tup.service.administracion.clientes;

import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModificarClienteTest {
    @Mock
    ClienteDao clienteDao;

    @InjectMocks
    ModificarCliente modificarCliente;

    /*@BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testModificarNombreClienteSuccess() throws ClienteNoEncontradoException {
        Cliente pepo = getCliente("Pepo", 12345678L);

        when(clienteDao.findCliente(pepo.getDni())).thenReturn(pepo);
        when(clienteInput.ingresarNombre()).thenReturn("Peperino");

        String resultado = modificarCliente.modificarCliente(pepo.getDni(), 1);

        assertEquals("----------------------------------------\nNombre modificado correctamente\n----------------------------------------", resultado);

        verify(clienteDao, times(1)).findCliente(pepo.getDni());
        verify(clienteDao, times(1)).deleteCliente(pepo.getDni());
        verify(clienteDao, times(1)).saveCliente(pepo);
    }

    @Test
    public void testModificarApellidoCliente() throws ClienteNoEncontradoException {
        Cliente pepo = getCliente("Pepo", 12345678L);

        when(clienteDao.findCliente(pepo.getDni())).thenReturn(pepo);
        when(clienteInput.ingresarApellido()).thenReturn("Pomoro");

        String resultado = modificarCliente.modificarCliente(pepo.getDni(), 2);

        assertEquals("----------------------------------------\nApellido modificado correctamente\n----------------------------------------", resultado);

        verify(clienteDao, times(1)).findCliente(pepo.getDni());
        verify(clienteDao, times(1)).deleteCliente(pepo.getDni());
        verify(clienteDao, times(1)).saveCliente(pepo);
    }

    @Test
    public void testModificarClienteNoEncontrado(){
        Cliente pepo = getCliente("Pepo", 12345678L);

        when(clienteDao.findCliente(pepo.getDni())).thenReturn(null);

        assertThrows(ClienteNoEncontradoException.class, () -> modificarCliente.modificarCliente(pepo.getDni(), 1));
    }
    */
}
