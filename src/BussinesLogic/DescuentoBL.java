package BussinesLogic;

import DataAccess.DTO.ClienteDTO;
import DataAccess.DTO.ReservaDTO;

public class DescuentoBL {

    public void aplicarIvaYDescuento(ClienteDTO clienteDTO) {
        // Suponiendo un IVA del 15% y un descuento del 10%
        double iva = clienteDTO.getTotalReservas() * 0.15;
        double descuento = clienteDTO.getTotalReservas() * 0.10;

        clienteDTO.setIva(iva);
        clienteDTO.setDescuento(descuento);
    }

    public void aplicarDescuento(ReservaDTO reservaDTO) {
        // Suponiendo un descuento del 10%
        double descuento = reservaDTO.getMonto() * 0.10;
        reservaDTO.setMonto(reservaDTO.getMonto() - descuento);
    }
}
