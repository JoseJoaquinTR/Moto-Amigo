package Utilerias;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author joset
 */
public class UtileriaImagen {

    private static final long TAMANIO_MAXIMO_BYTES = 2L * 1024L * 1024L;

    private UtileriaImagen() {
    }

    /**
     * Abre un JFileChooser para que el usuario seleccione una imagen (.jpg,
     * .jpeg, .png), valida que no exceda los 2 MB y carga el preview en el
     * JLabel indicado.Devuelve los bytes del archivo, o null si el usuario
     * cancela o ocurre un error.
     *
     * @param padre
     * @param lblPreview
     * @return
     */
    public static byte[] seleccionarYPrevisualizar(Component padre, JLabel lblPreview) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona una imagen");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter(
                "Imágenes (jpg, jpeg, png)", "jpg", "jpeg", "png"));

        int resultado = chooser.showOpenDialog(padre);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File archivo = chooser.getSelectedFile();

        if (archivo.length() > TAMANIO_MAXIMO_BYTES) {
            JOptionPane.showMessageDialog(padre,
                    "La imagen excede el tamaño máximo permitido de 2 MB.",
                    "Imagen demasiado grande",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            byte[] datos = Files.readAllBytes(archivo.toPath());

            if (lblPreview != null) {
                ImageIcon icono = bytesAImageIcon(datos,
                        lblPreview.getWidth(), lblPreview.getHeight());
                lblPreview.setIcon(icono);
                lblPreview.setText("");
            }

            return datos;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(padre,
                    "No se pudo leer la imagen: " + ex.getMessage(),
                    "Error al leer archivo",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Convierte un arreglo de bytes a un ImageIcon escalado al ancho y alto
     * indicados, manteniendo la proporción original de la imagen.Devuelve null
     * si los bytes son nulos o no se pueden interpretar como imagen.
     *
     * @param datos
     * @param ancho
     * @param alto
     * @return
     */
    public static ImageIcon bytesAImageIcon(byte[] datos, int ancho, int alto) {
        if (datos == null || datos.length == 0) {
            return null;
        }
        if (ancho <= 0 || alto <= 0) {
            return new ImageIcon(datos);
        }

        ImageIcon original = new ImageIcon(datos);
        if (original.getIconWidth() <= 0 || original.getIconHeight() <= 0) {
            return null;
        }

        int anchoOrig = original.getIconWidth();
        int altoOrig = original.getIconHeight();

        double escalaAncho = (double) ancho / anchoOrig;
        double escalaAlto = (double) alto / altoOrig;
        double escala = Math.min(escalaAncho, escalaAlto);

        int nuevoAncho = (int) Math.round(anchoOrig * escala)*2;
        int nuevoAlto = (int) Math.round(altoOrig * escala)*2;

        
        BufferedImage recortada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = recortada.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int offsetX = (ancho - nuevoAncho) / 2;
        int offsetY = (alto - nuevoAlto) / 2;
        g.drawImage(original.getImage(), offsetX, offsetY, nuevoAncho, nuevoAlto, null);
        g.dispose();
        
        
        return new ImageIcon(recortada);
    }

}
