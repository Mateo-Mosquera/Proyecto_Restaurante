package BussinesLogic;

import java.util.List;
import java.time.LocalDate;
import DataAccess.ClientesDAO;
import DataAccess.DTO.ClientesDTO;
import DataAccess.ReservasDAO;
import DataAccess.DTO.ReservasDTO;
import java.sql.Date;

public class ClientesBL {
    private ClientesDTO cliente;
    private ClientesDAO cDAO = new ClientesDAO();
    private ReservasDAO reservasDAO = new ReservasDAO();
    private DescuentoBL descuentoBL = new DescuentoBL();

    public ClientesBL(){}

    public List<ClientesDTO> getAll() throws Exception {
        List<ClientesDTO> lst = cDAO.readAll();
        return lst;
    }

    public ClientesDTO getBy(String cedula) throws Exception {
        cliente = cDAO.readBy(cedula);
        return cliente;
    }
    

    public boolean add(ClientesDTO clientesDTO) throws Exception {
        if (clientesDTO == null) {
            throw new IllegalArgumentException("ClienteDTO no puede ser nulo");
        }
        descuentoBL.aplicarIvaYDescuento(clientesDTO);
        return cDAO.create(clientesDTO);
    }
    

    public boolean update(ClientesDTO clientesDTO) throws Exception {
        // Aplicar IVA y descuento al actualizar un cliente existente
        descuentoBL.aplicarIvaYDescuento(clientesDTO);
        return cDAO.update(clientesDTO);
    }

    public boolean delete(String cedula) throws Exception {
        return cDAO.delete(cedula);
    }
    
    public int getRowCount() throws Exception {
        return cDAO.getRowCount();
    }

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
