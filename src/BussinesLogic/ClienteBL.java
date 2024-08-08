package BussinesLogic;

import java.util.List;
import java.time.LocalDate;
import DataAccess.ClienteDAO;
import DataAccess.DTO.ClienteDTO;
import DataAccess.ReservaDAO;
import DataAccess.DTO.ReservaDTO;

public class ClienteBL {
    private ClienteDTO cliente;
    private ClienteDAO cDAO = new ClienteDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();
    private DescuentoBL descuentoBL = new DescuentoBL();

    public ClienteBL(){}

    public List<ClienteDTO> getAll() throws Exception {
        List<ClienteDTO> lst = cDAO.readAll();
        return lst;
    }

    public ClienteDTO getBy(int idCliente) throws Exception {
        cliente = cDAO.readBy(idCliente);
        return cliente;
    }

    public boolean add(ClienteDTO clienteDTO) throws Exception {
        // Aplicar IVA y descuento al agregar un nuevo cliente
        descuentoBL.aplicarIvaYDescuento(clienteDTO);
        return cDAO.create(clienteDTO);
    }

    public boolean update(ClienteDTO clienteDTO) throws Exception {
        // Aplicar IVA y descuento al actualizar un cliente existente
        descuentoBL.aplicarIvaYDescuento(clienteDTO);
        return cDAO.update(clienteDTO);
    }

    public boolean delete(int idCliente) throws Exception {
        return cDAO.delete(idCliente);
    }

    public int getRowCount() throws Exception {
        return cDAO.getRowCount();
    }

    public boolean registrarCliente(String nombre, String email) throws Exception {
        if (nombre != null && !nombre.isEmpty() && email != null && email.contains("@")) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setNombre(nombre);
            clienteDTO.setEmail(email);
            return cDAO.create(clienteDTO);
        } else {
            throw new IllegalArgumentException("Datos de cliente no válidos");
        }
    }

    public boolean crearReserva(LocalDate fecha, int clienteId) throws Exception {
        if (fecha != null && cDAO.exists(clienteId)) {
            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setFecha(fecha);
            reservaDTO.setClienteId(clienteId);
            descuentoBL.aplicarDescuento(reservaDTO);
            return reservaDAO.create(reservaDTO);
        } else {
            throw new IllegalArgumentException("Datos de reserva no válidos");
        }
    }

    public boolean actualizarReserva(int reservaId, LocalDate nuevaFecha) throws Exception {
        if (nuevaFecha != null && reservaDAO.exists(reservaId)) {
            ReservaDTO reservaDTO = reservaDAO.readBy(reservaId);
            reservaDTO.setFecha(nuevaFecha);
            descuentoBL.aplicarDescuento(reservaDTO);
            return reservaDAO.update(reservaDTO);
        } else {
            throw new IllegalArgumentException("Datos de actualización no válidos");
        }
    }

    public boolean eliminarReserva(int reservaId) throws Exception {
        if (reservaDAO.exists(reservaId)) {
            return reservaDAO.delete(reservaId);
        } else {
            throw new IllegalArgumentException("La reserva no existe");
        }
    }
}
