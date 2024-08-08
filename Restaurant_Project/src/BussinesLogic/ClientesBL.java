package BussinesLogic;

import java.util.List;

import DataAccess.ClientesDAO;
import DataAccess.DTO.ClientesDTO;

public class ClientesBL {
    private ClientesDTO cliente;
    private ClientesDAO cDAO = new ClientesDAO();

    public ClientesBL(){}

    public List<ClientesDTO> getAll() throws Exception{
        List<ClientesDTO> lst = cDAO.readAll();
        for (ClientesDTO clienteDTO : lst) {
            clienteDTO.setNombre(clienteDTO.getNombre().toUpperCase());
        }
        return lst;
    }

    public ClientesDTO getBy(String cedula) throws Exception{
        cliente = cDAO.readBy(cedula);
        return cliente;
    }

    public boolean add(ClientesDTO clienteDTO) throws Exception{
        // Aplicar IVA y descuento al agregar un nuevo cliente
        aplicarIvaYDescuento(clienteDTO);
        return cDAO.create(clienteDTO);
    }

    public boolean update(ClientesDTO clienteDTO) throws Exception{
        // Aplicar IVA y descuento al actualizar un cliente existente
        aplicarIvaYDescuento(clienteDTO);
        return cDAO.update(clienteDTO);
    }

    public boolean delete(String cedula) throws Exception{
        return cDAO.delete(cedula);
    }

    public int getRowCount() throws Exception{
        return cDAO.getRowCount();
    }

    private void aplicarIvaYDescuento(ClientesDTO clienteDTO) {
        // Suponiendo un IVA del 12% y un descuento del 10%
        double iva = clienteDTO.getTotalReservas() * 0.12;
        double descuento = clienteDTO.getTotalReservas() * 0.10;

        clienteDTO.setIva(iva);
        clienteDTO.setDescuento(descuento);
    }
}
