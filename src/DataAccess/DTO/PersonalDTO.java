package DataAccess.DTO;

/**
 * Clase que representa un miembro del personal en el sistema.
 * Contiene la información básica del personal, incluyendo su cédula, nombre, apellido, teléfono y correo.
 */
public class PersonalDTO {
    
    /**
     * Identificador único del personal.
     */
    private Integer idPersonal;

    /**
     * Cédula del personal.
     */
    private String cedula;

    /**
     * Nombre del personal.
     */
    private String nombre;

    /**
     * Apellido del personal.
     */
    private String apellido;

    /**
     * Teléfono del personal.
     */
    private Integer telefono;

    /**
     * Correo electrónico del personal.
     */
    private String correo;

    /**
     * Constructor vacío de la clase PersonalDTO.
     */
    public PersonalDTO() {}

    /**
     * Constructor de la clase PersonalDTO que inicializa todos los campos.
     * 
     * @param cedula   La cédula del personal.
     * @param nombre   El nombre del personal.
     * @param apellido El apellido del personal.
     * @param telefono El teléfono del personal.
     * @param correo   El correo electrónico del personal.
     */
    public PersonalDTO(String cedula, String nombre, String apellido, Integer telefono, String correo) {
        this.cedula    = cedula;
        this.nombre    = nombre;
        this.apellido  = apellido;
        this.telefono  = telefono;
        this.correo    = correo;
    }

    // Métodos de acceso (getters y setters) para cada atributo de la clase.

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

    /**
     * Método que devuelve una representación en formato de cadena de la clase PersonalDTO.
     * 
     * @return Una cadena con los datos del personal.
     */
    @Override
    public String toString() {
        return getClass().getName()
                + "\n cedula:   "       + getCedula()
                + "\n nombre:   "       + getNombre()
                + "\n apellido: "       + getApellido()
                + "\n telefono: "       + getTelefono()
                + "\n correo:   "       + getCorreo();
    }
}
