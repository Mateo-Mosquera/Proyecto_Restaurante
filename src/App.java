import DataAccess.DTO.ClientesDTO;
import DataAccess.DAO.ClientesDAO;
import DataAccess.DTO.PersonalDTO;
import DataAccess.IDAO.PersonalDAO;
import UserInterface.UIComponent.Diseño.Inicio;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import com.google.zxing.WriterException;
import BussinesLogic.ClientesBL;
import BussinesLogic.GeneradorCodigoQrBL;

public class App {
    private static final String QR_CODE_IMAGE_PATH = "./QRCode.png";

    public static void main(String[] args) {
        try {
            // Iniciar la interfaz gráfica
            Inicio inicioPrograma = new Inicio();
            inicioPrograma.setVisible(true);

            // Generar código QR (descomentado si es necesario)
            /*
            try {
                GeneradorCodigoQrBL.generarImagenCodigoQR(
                    "https://drive.google.com/file/d/1dUuvbiZ79wVbcm7gPoR--oe0KB52tHEH/view?usp=sharing", 
                    350, 350, QR_CODE_IMAGE_PATH
                );
            } catch (WriterException | IOException e) {
                System.err.println("Error al generar el código QR: " + e.getMessage());
            }
            */

            // Crear instancia de ClientesBL
            ClientesBL clientesBL = new ClientesBL();

            // Crear nuevo cliente
            ClientesDTO nuevoCliente = new ClientesDTO("03161564", "JMAdsn", "Pérez", 1234566789, "juanperez@example.com");
            boolean clienteCreado = clientesBL.add(nuevoCliente);
            System.out.println("Cliente creado: " + clienteCreado);

            // Crear reserva para el cliente
            if (clientesBL.crearReserva(LocalDate.now(), "03161564")) {
                System.out.println("Reserva creada con éxito.");
            } else {
                System.out.println("No se pudo crear la reserva.");
            }

            // Leer todos los clientes
            System.out.println("Clientes:");
            for (ClientesDTO cliente : clientesBL.getAll()) {
                System.out.println(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
