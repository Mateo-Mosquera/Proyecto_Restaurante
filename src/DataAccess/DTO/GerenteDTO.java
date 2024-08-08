package DataAccess.DTO;

public class GerenteDTO {
    private Integer IDGerente;
    private Integer IDRestaurante;
    private String Nombre;
    private String Correo;

    public GerenteDTO() {}

    public GerenteDTO(Integer IDGerente, Integer IDRestaurante, String Nombre, String Correo) {
        this.IDGerente = IDGerente;
        this.IDRestaurante = IDRestaurante;
        this.Nombre = Nombre;
        this.Correo = Correo;
    }

    public Integer getIDGerente() {
        return IDGerente;
    }

    public void setIDGerente(Integer IDGerente) {
        this.IDGerente = IDGerente;
    }

    public Integer getIDRestaurante() {
        return IDRestaurante;
    }

    public void setIDRestaurante(Integer IDRestaurante) {
        this.IDRestaurante = IDRestaurante;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IDGerente=" + getIDGerente()
                + "\n IDRestaurante=" + getIDRestaurante()
                + "\n Nombre=" + getNombre()
                + "\n Correo=" + getCorreo();
    }
}
