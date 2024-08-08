package DataAccess.DTO;

import java.sql.Date;
import java.sql.Time;


public class ReservasDTO {
    private Integer idReserva;
    private String cedula;
    private Integer idMesa;
    private Date fecha;
    private Time hora;
    private String estado;

    public ReservasDTO() {}

    public ReservasDTO(Integer idReserva, String cedula, Integer idMesa, Date fecha, Time hora, String estado) {
        this.idReserva = idReserva;
        this.cedula = cedula;
        this.idMesa = idMesa;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IDReserva: "      + getIdReserva()
                + "\n Cedula: "         + getCedula()
                + "\n IDMesa: "         + getIdMesa()
                + "\n Fecha: "          + getFecha()
                + "\n Hora: "           + getHora()
                + "\n Estado: "         + getEstado();
    }
}