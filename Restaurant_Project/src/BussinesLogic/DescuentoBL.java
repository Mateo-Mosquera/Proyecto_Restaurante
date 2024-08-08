package BussinesLogic;

import DataAccess.DTO.ClientesDTO;
import DataAccess.DTO.ReservasDTO;

public class DescuentoBL {

    public void aplicarIvaYDescuento(ClientesDTO clienteDTO) {
        double iva = clienteDTO.getTotalReservas() * 0.15;
        double descuento = clienteDTO.getTotalReservas() * 0.10;

        clienteDTO.setIva(iva);
        clienteDTO.setDescuento(descuento);
    }

    public void aplicarDescuento(ReservasDTO reservaDTO) {
        double descuento = reservaDTO.getMonto() * 0.10;
        reservaDTO.setMonto(reservaDTO.getMonto() - descuento);
    }
}
    

