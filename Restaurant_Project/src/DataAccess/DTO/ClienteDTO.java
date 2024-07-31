package DataAccess.DTO;

public class ClienteDTO {
    private Integer IDCliente;
    private String Nombre;
    private String Apellido;
    private Integer Telefono;
    private String Correo;
    
    
    public ClienteDTO(){}
    
    public ClienteDTO(String nombre){
        this.Nombre = nombre;
    }
    
    public ClienteDTO(Integer IDCliente, String nombre, String apellido, Integer telefono, String correo){
        this.IDCliente = IDCliente;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Telefono = telefono;
        this.Correo = correo;
    }    



    public Integer getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(Integer iDCliente) {
        IDCliente = iDCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Integer getTelefono() {
        return Telefono;
    }

    public void setTelefono(Integer telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IDCliente="   + getIDCliente()
                + "\n nombre=   "   + getNombre()
                + "\n apellido= "   + getApellido()
                + "\n telefono= "   + getTelefono()
                + "\n correo=   "   + getCorreo();

    }

}
