package BussinesLogic;

import java.util.List;

import DataAccess.ClienteDAO;
import DataAccess.DTO.ClienteDTO;

public class ClienteBL {
    private ClienteDTO cliente;
    private ClienteDAO cDAO = new ClienteDAO();

    public ClienteBL(){}

    public List<ClienteDTO> getAll() throws Exception{
        List<ClienteDTO> lst = cDAO.readAll();
        for (ClienteDTO clienteDTO : lst) {
            clienteDTO.setNombre(clienteDTO.getNombre().toUpperCase());
        }
        return lst;
    }

    public ClienteDTO getBy(int idCliente) throws Exception{
        cliente = cDAO.readBy(idCliente);
        return cliente;
    }

    public boolean add(ClienteDTO clienteDTO) throws Exception{
        // Aplicar IVA y descuento al agregar un nuevo cliente
        aplicarIvaYDescuento(clienteDTO);
        return cDAO.create(clienteDTO);
    }

    public boolean update(ClienteDTO clienteDTO) throws Exception{
        // Aplicar IVA y descuento al actualizar un cliente existente
        aplicarIvaYDescuento(clienteDTO);
        return cDAO.update(clienteDTO);
    }

    public boolean delete(int idCliente) throws Exception{
        return cDAO.delete(idCliente);
    }

    public int getRowCount() throws Exception{
        return cDAO.getRowCount();
    }

    private void aplicarIvaYDescuento(ClienteDTO clienteDTO) {
        // Suponiendo un IVA del 12% y un descuento del 10%
        double iva = clienteDTO.getTotalReservas() * 0.12;
        double descuento = clienteDTO.getTotalReservas() * 0.10;

        clienteDTO.setIva(iva);
        clienteDTO.setDescuento(descuento);
    }
}
