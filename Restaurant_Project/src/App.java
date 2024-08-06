import DataAccess.ClientesDAO;
import DataAccess.DTO.ClientesDTO;
import DataAccess.PersonalDAO;
import DataAccess.DTO.PersonalDTO;

public class App {
    public static void main(String[] args) {
        try {
            // Crear instancias de DAO
            ClientesDAO clienteDAO = new ClientesDAO();
            PersonalDAO empleadoDAO = new PersonalDAO();
            
            // Crear un nuevo cliente
            ClientesDTO nuevoCliente = new ClientesDTO("0322158529","Juan", "Pérez", 123456789, "juanperez@example.com");
            boolean clienteCreado = clienteDAO.create(nuevoCliente);
            System.out.println("Cliente creado: " + clienteCreado);
/*
            // Leer todos los clientes
            System.out.println("Clientes:");
            for (ClientesDTO cliente : clienteDAO.readAll()) {
                System.out.println(cliente);
            }

            // Actualizar un cliente
            ClientesDTO clienteActualizar = clienteDAO.readBy(1);
            if (clienteActualizar != null) {
                clienteActualizar.setNombre("Juan Pablo");
                boolean clienteActualizado = clienteDAO.update(clienteActualizar);
                System.out.println("Cliente actualizado: " + clienteActualizado);
            }

            // Leer cliente por ID
            ClientesDTO clientePorID = clienteDAO.readBy(1);
            System.out.println("Cliente con ID 1: " + clientePorID);

            // Eliminar un cliente
            boolean clienteEliminado = clienteDAO.delete("025064");
            System.out.println("Cliente eliminado: " + clienteEliminado);

            // Crear un nuevo empleado
            PersonalDTO nuevoempleado = new PersonalDTO("15413", "Ana", "López", 15356153, "analopez@example.com");
            boolean empleadoCreado = empleadoDAO.create(nuevoempleado);
            System.out.println("empleado creado: " + empleadoCreado);

            // Leer todos los empleados
            System.out.println("empleados:");
            for (PersonalDTO empleado : empleadoDAO.readAll()) {
                System.out.println(empleado);
            }

            // Actualizar un empleado
            PersonalDTO empleadoActualizar = empleadoDAO.readBy(1);
            if (empleadoActualizar != null) {
                empleadoActualizar.setNombre("Ana María López");
                boolean empleadoActualizado = empleadoDAO.update(empleadoActualizar);
                System.out.println("empleado actualizado: " + empleadoActualizado);
            }

            // Leer empleado por ID
            PersonalDTO empleadoPorID = empleadoDAO.readBy(1);
            System.out.println("empleado con ID 1: " + empleadoPorID);

            // Eliminar un empleado
            boolean empleadoEliminado = empleadoDAO.delete("1");
            System.out.println("empleado eliminado: " + empleadoEliminado);
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
