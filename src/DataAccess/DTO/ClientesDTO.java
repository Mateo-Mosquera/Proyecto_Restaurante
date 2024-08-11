/*---------------------------------------------------\
|  Autor: Mateo Mosquera                             |
|  DTO: Creacion de los DTO para Clientes          |
\---------------------------------------------------*/

package DataAccess.DTO;

/**
 * Clase que representa un cliente en el sistema.
 * Contiene la información básica de un cliente, incluyendo su cédula, nombre, apellido, teléfono, correo,
 * total de reservas, descuento y IVA.
 */
public class ClientesDTO {
    
    
    private Integer idCliente;
    private String cedula;
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String correo;
    private double totalReservas;
    private double descuento;
    private double iva;

    /**
     * Constructor vacío de la clase ClientesDTO.
     */
    public ClientesDTO() {}

    /**
     * Constructor de la clase ClientesDTO que inicializa el nombre del cliente.
     * 
     * @param nombre El nombre del cliente.
     */
    public ClientesDTO(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor de la clase ClientesDTO que inicializa todos los campos excepto totalReservas, descuento e IVA.
     * 
     * @param cedula   La cédula del cliente.
     * @param nombre   El nombre del cliente.
     * @param apellido El apellido del cliente.
     * @param telefono El teléfono del cliente.
     * @param correo   El correo electrónico del cliente.
     */
    public ClientesDTO(String cedula, String nombre, String apellido, Integer telefono, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Constructor de la clase ClientesDTO que inicializa todos los campos.
     * 
     * @param cedula        La cédula del cliente.
     * @param nombre        El nombre del cliente.
     * @param apellido      El apellido del cliente.
     * @param telefono      El teléfono del cliente.
     * @param correo        El correo electrónico del cliente.
     * @param totalReservas El total de reservas realizadas por el cliente.
     * @param descuento     El descuento aplicado al cliente.
     * @param iva           El IVA aplicable a las reservas del cliente.
     */
    public ClientesDTO(String cedula, String nombre, String apellido, Integer telefono, String correo, double totalReservas, double descuento, double iva) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.totalReservas = totalReservas;
        this.descuento = descuento;
        this.iva = iva;
    }

    // Métodos de acceso (getters y setters) para cada atributo de la clase.

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public double getTotalReservas() {
        return totalReservas;
    }

    public void setTotalReservas(double totalReservas) {
        this.totalReservas = totalReservas;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Método que devuelve una representación en formato de cadena de la clase ClientesDTO.
     * 
     * @return Una cadena con los datos del cliente.
     */
    @Override
    public String toString() {
        return getClass().getName()
                + "\n idCliente: "       + getIdCliente()
                + "\n cedula: "          + getCedula()
                + "\n nombre: "          + getNombre()
                + "\n apellido: "        + getApellido()
                + "\n telefono: "        + getTelefono()
                + "\n correo: "          + getCorreo()
                + "\n totalReservas: "   + getTotalReservas()
                + "\n descuento: "       + getDescuento()
                + "\n iva: "             + getIva();
    }
}
