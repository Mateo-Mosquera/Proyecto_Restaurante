/*---------------------------------------------------\
|  Autor: Mateo Mosquera                             |
|  Creacion de BL para Clientes            |
\---------------------------------------------------*/

package BussinesLogic;

import java.util.List;
import java.time.LocalDate;

import DataAccess.DTO.ClientesDTO;
import DataAccess.DAO.ClientesDAO;
import DataAccess.DAO.ReservasDAO;
import DataAccess.DTO.ReservasDTO;
import java.sql.Date;

/**
 * Clase que maneja la lógica de negocio relacionada con los clientes.
 * Proporciona métodos para gestionar clientes y reservas, aplicando descuentos e IVA cuando sea necesario.
 */
public class ClientesBL {
    
    private ClientesDTO cliente;
    private ClientesDAO cDAO = new ClientesDAO();
    private ReservasDAO reservasDAO = new ReservasDAO();
    private DescuentoBL descuentoBL = new DescuentoBL();

    /**
     * Constructor vacío de la clase ClientesBL.
     */
    public ClientesBL(){}

    /**
     * Obtiene una lista de todos los clientes y convierte los nombres a mayúsculas.
     * 
     * @return Una lista de ClientesDTO con todos los clientes.
     * @throws Exception Si ocurre un error durante la lectura de los datos.
     */
    public List<ClientesDTO> getAll() throws Exception {
        List<ClientesDTO> lst = cDAO.readAll();
        for (ClientesDTO ClientesDTO : lst) {
            ClientesDTO.setNombre(ClientesDTO.getNombre().toUpperCase());
        }
        return lst;
    }

    /**
     * Obtiene un cliente a partir de su cédula.
     * 
     * @param cedula La cédula del cliente a buscar.
     * @return Un objeto ClientesDTO que representa al cliente encontrado.
     * @throws Exception Si ocurre un error durante la lectura de los datos.
     */
    public ClientesDTO getBy(String cedula) throws Exception {
        cliente = cDAO.readBy(cedula);
        return cliente;
    }
    
    /**
     * Añade un nuevo cliente al sistema.
     * Aplica IVA y descuento antes de guardar el cliente.
     * 
     * @param clientesDTO Un objeto ClientesDTO que representa al cliente a añadir.
     * @return true si el cliente fue añadido exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la creación del cliente.
     * @throws IllegalArgumentException Si el cliente proporcionado es nulo.
     */
    public boolean add(ClientesDTO clientesDTO) throws Exception {
        if (clientesDTO == null) {
            throw new IllegalArgumentException("ClienteDTO no puede ser nulo");
        }
        descuentoBL.aplicarIvaYDescuento(clientesDTO);
        return cDAO.create(clientesDTO);
    }
    
    /**
     * Actualiza los datos de un cliente existente.
     * Aplica IVA y descuento antes de actualizar el cliente.
     * 
     * @param clientesDTO Un objeto ClientesDTO que representa al cliente a actualizar.
     * @return true si el cliente fue actualizado exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la actualización del cliente.
     */
    public boolean update(ClientesDTO clientesDTO) throws Exception {
        descuentoBL.aplicarIvaYDescuento(clientesDTO);
        return cDAO.update(clientesDTO);
    }

    /**
     * Elimina un cliente del sistema basado en su cédula.
     * 
     * @param cedula La cédula del cliente a eliminar.
     * @return true si el cliente fue eliminado exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la eliminación del cliente.
     */
    public boolean delete(String cedula) throws Exception {
        return cDAO.delete(cedula);
    }
    
    /**
     * Obtiene el número total de filas (clientes) en la base de datos.
     * 
     * @return El número de clientes en la base de datos.
     * @throws Exception Si ocurre un error durante la consulta de las filas.
     */
    public int getRowCount() throws Exception {
        return cDAO.getRowCount();
    }

    /**
     * Registra un nuevo cliente en el sistema si los datos proporcionados son válidos.
     * 
     * @param nombre El nombre del cliente.
     * @param email  El correo electrónico del cliente.
     * @return true si el cliente fue registrado exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la creación del cliente.
     * @throws IllegalArgumentException Si los datos del cliente no son válidos.
     */
    public boolean registrarCliente(String nombre, String email) throws Exception {
        if (nombre != null && !nombre.isEmpty() && email != null && email.contains("@")) {
            ClientesDTO clientesDTO = new ClientesDTO();
            clientesDTO.setNombre(nombre);
            clientesDTO.setCorreo(email); 
            return cDAO.create(clientesDTO);
        } else {
            throw new IllegalArgumentException("Datos de cliente no válidos");
        }
    }
    
    /**
     * Crea una nueva reserva para un cliente si la fecha y la cédula son válidas.
     * 
     * @param fecha         La fecha de la reserva.
     * @param clienteCedula La cédula del cliente que realiza la reserva.
     * @return true si la reserva fue creada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la creación de la reserva.
     * @throws IllegalArgumentException Si los datos de la reserva no son válidos.
     */
    public boolean crearReserva(LocalDate fecha, String clienteCedula) throws Exception {
        if (fecha != null && cDAO.exists(clienteCedula)) {
            ReservasDTO reservaDTO = new ReservasDTO();
            reservaDTO.setFecha(Date.valueOf(fecha)); 
            reservaDTO.setCedula(clienteCedula); 
            descuentoBL.aplicarDescuento(reservaDTO);
            return reservasDAO.create(reservaDTO);
        } else {
            throw new IllegalArgumentException("Datos de reserva no válidos");
        }
    }
    
    /**
     * Actualiza la fecha de una reserva existente si los datos proporcionados son válidos.
     * 
     * @param reservaId  El identificador de la reserva a actualizar.
     * @param nuevaFecha La nueva fecha de la reserva.
     * @return true si la reserva fue actualizada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la actualización de la reserva.
     * @throws IllegalArgumentException Si los datos de la actualización no son válidos.
     */
    public boolean actualizarReserva(Integer reservaId, LocalDate nuevaFecha) throws Exception {
        if (nuevaFecha != null && reservasDAO.exists(reservaId)) {
            ReservasDTO reservaDTO = reservasDAO.readBy(reservaId);
            reservaDTO.setFecha(Date.valueOf(nuevaFecha)); 
            descuentoBL.aplicarDescuento(reservaDTO);
            return reservasDAO.update(reservaDTO);
        } else {
            throw new IllegalArgumentException("Datos de actualización no válidos");
        }
    }
}
