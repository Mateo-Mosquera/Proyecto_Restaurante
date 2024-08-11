package DataAccess.DTO;

public class MesasDTO {
    private Integer idMesa;
    private Integer capacidad;
    private String estado;
    public MesasDTO() {}
    
    public MesasDTO(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    public MesasDTO(Integer capacidad, String estado) {
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Integer getIdMesa() {
        return idMesa;
    }
    
    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    
    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
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
                + "\n IDMesa: "          + getIdMesa()
                + "\n Capacidad: "        + getCapacidad()
                + "\n Estado: "          + getEstado();
    }
}
