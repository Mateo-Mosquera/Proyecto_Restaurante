package DataAccess.DTO;

public class PersonalDTO {
    private Integer idPersonal;
    private String  cedula;
    private String  nombre;
    private String  apellido;
    private Integer telefono;
    private String  correo;
    
    public PersonalDTO() {}
    
    public PersonalDTO(String cedula, String nombre, String apellido, Integer telefono, String correo) {
        this.cedula         = cedula;
        this.nombre         = nombre;
        this.apellido       = apellido;
        this.telefono       = telefono;
        this.correo         = correo;
    }

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    @Override
    public String toString() {
        return getClass().getName()
                + "\n cedula: "          + getCedula()
                + "\n nombre: "          + getNombre()
                + "\n apellido: "        + getApellido()
                + "\n telefono: "        + getTelefono()
                + "\n correo: "          + getCorreo();
    }
}
