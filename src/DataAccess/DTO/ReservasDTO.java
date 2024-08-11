package DataAccess.DTO;

import java.sql.Date;
import java.sql.Time;

/**
 * Clase que representa una reserva en el sistema.
 * Contiene la información relacionada con una reserva, incluyendo el ID de la reserva, cédula del cliente, ID de la mesa, fecha, hora, estado y monto.
 */
public class ReservasDTO {
    
    private Integer idReserva;
    private String cedula;
    private Integer idMesa;
    private Date fecha;
    private Time hora;
    private String estado;
    private Double monto; 

    /**
     * Constructor vacío de la clase ReservasDTO.
     */
    public ReservasDTO() {}

    /**
     * Constructor de la clase ReservasDTO que inicializa todos los campos.
     * 
     * @param idReserva  El identificador de la reserva.
     * @param cedula     La cédula del cliente.
     * @param idMesa     El identificador de la mesa.
     * @param fecha      La fecha de la reserva.
     * @param hora       La hora de la reserva.
     * @param estado     El estado de la reserva.
     * @param monto      El monto total de la reserva.
     */
    public ReservasDTO(Integer idReserva, String cedula, Integer idMesa, Date fecha, Time hora, String estado, Double monto) {
        this.idReserva  = idReserva;
        this.cedula     = cedula;
        this.idMesa     = idMesa;
        this.fecha      = fecha;
        this.hora       = hora;
        this.estado     = estado;
        this.monto      = monto;
    }

    // Métodos de acceso (getters y setters) para cada atributo de la clase.

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

    public Double getMonto() { 
        return monto;
    }

    public void setMonto(Double monto) { 
        this.monto = monto;
    }

    /**
     * Método que devuelve una representación en formato de cadena de la clase ReservasDTO.
     * 
     * @return Una cadena con los datos de la reserva.
     */
    @Override
    public String toString() {
        return getClass().getName()
                + "\n IDReserva: "      + getIdReserva()
                + "\n Cedula: "         + getCedula()
                + "\n IDMesa: "         + getIdMesa()
                + "\n Fecha: "          + getFecha()
                + "\n Hora: "           + getHora()
                + "\n Estado: "         + getEstado()
                + "\n Monto: "          + getMonto(); 
    }
}
