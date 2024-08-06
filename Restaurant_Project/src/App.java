import DataAccess.ClienteDAO;
import DataAccess.DTO.ClienteDTO;
import DataAccess.GerenteDAO;
import DataAccess.DTO.GerenteDTO;

public class App {
    public static void main(String[] args) {
        try {
            // Crear instancias de DAO
            ClienteDAO clienteDAO = new ClienteDAO();
            GerenteDAO gerenteDAO = new GerenteDAO();
            
            // Crear un nuevo cliente
            ClienteDTO nuevoCliente = new ClienteDTO(null, "Juan", "Pérez", 123456789, "juanperez@example.com");
            boolean clienteCreado = clienteDAO.create(nuevoCliente);
            System.out.println("Cliente creado: " + clienteCreado);

            // Leer todos los clientes
            System.out.println("Clientes:");
            for (ClienteDTO cliente : clienteDAO.readAll()) {
                System.out.println(cliente);
            }

            // Actualizar un cliente
            ClienteDTO clienteActualizar = clienteDAO.readBy(1);
            if (clienteActualizar != null) {
                clienteActualizar.setNombre("Juan Pablo");
                boolean clienteActualizado = clienteDAO.update(clienteActualizar);
                System.out.println("Cliente actualizado: " + clienteActualizado);
            }

            // Leer cliente por ID
            ClienteDTO clientePorID = clienteDAO.readBy(1);
            System.out.println("Cliente con ID 1: " + clientePorID);

            // Eliminar un cliente
            boolean clienteEliminado = clienteDAO.delete(1);
            System.out.println("Cliente eliminado: " + clienteEliminado);

            // Crear un nuevo gerente
            GerenteDTO nuevoGerente = new GerenteDTO(null, 1, "Ana López", "analopez@example.com");
            boolean gerenteCreado = gerenteDAO.create(nuevoGerente);
            System.out.println("Gerente creado: " + gerenteCreado);

            // Leer todos los gerentes
            System.out.println("Gerentes:");
            for (GerenteDTO gerente : gerenteDAO.readAll()) {
                System.out.println(gerente);
            }

            // Actualizar un gerente
            GerenteDTO gerenteActualizar = gerenteDAO.readBy(1);
            if (gerenteActualizar != null) {
                gerenteActualizar.setNombre("Ana María López");
                boolean gerenteActualizado = gerenteDAO.update(gerenteActualizar);
                System.out.println("Gerente actualizado: " + gerenteActualizado);
            }

            // Leer gerente por ID
            GerenteDTO gerentePorID = gerenteDAO.readBy(1);
            System.out.println("Gerente con ID 1: " + gerentePorID);

            // Eliminar un gerente
            boolean gerenteEliminado = gerenteDAO.delete(1);
            System.out.println("Gerente eliminado: " + gerenteEliminado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
