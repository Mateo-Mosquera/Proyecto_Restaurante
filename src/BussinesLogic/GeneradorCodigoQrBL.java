package BussinesLogic;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class GeneradorCodigoQrBL {

    public static void generarImagenCodigoQR(String texto, int ancho, int alto, String rutaArchivo) throws WriterException, IOException {
        QRCodeWriter escritorCodigoQR = new QRCodeWriter();
        BitMatrix matrizBit = escritorCodigoQR.encode(texto, BarcodeFormat.QR_CODE, ancho, alto);

        Path ruta = FileSystems.getDefault().getPath(rutaArchivo);
        MatrixToImageWriter.writeToPath(matrizBit, "PNG", ruta);
    }
}
