package DataAccess.DTO;

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
    
    public ClientesDTO() {}
    
    public ClientesDTO(String nombre) {
        this.nombre = nombre;
    }
    
    public ClientesDTO(String cedula, String nombre, String apellido, Integer telefono, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }
    
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
