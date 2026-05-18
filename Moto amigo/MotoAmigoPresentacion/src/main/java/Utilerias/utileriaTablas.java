package Utilerias;

import com.mycompany.paquetesdto.PaqueteDTO;
import com.mycompany.paquetesdto.PaqueteHistorialDTO;
import com.mycompany.productosdto.ProductoDTO;
import com.mycompany.paquetesdto.ProductosPaqueteDTO;
import com.mycompany.paquetesdto.ReporteHistorialPaquetePDFDTO;
import java.awt.Component;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Utilería para configurar y cargar tablas de productos, paquetes e historial.
 *
 * @author joset
 */
public final class utileriaTablas {

    private static final int ANCHO_IMAGEN = 55;
    private static final int ALTO_IMAGEN = 55;
    private static final int LIMITE_PRODUCTOS_PAQUETE = 3;

    private utileriaTablas() {
    }

    public static DefaultTableModel crearModeloProductos() {
        return new DefaultTableModel(
                new Object[]{"Imagen", "Nombre", "Unidad", "Peso", "Precio"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : Object.class;
            }
        };
    }

    public static DefaultTableModel crearModeloPaquetes() {
        return new DefaultTableModel(
                new Object[]{"Imagen", "Nombre", "Tamaño", "Precio", "Peso", "Productos"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : Object.class;
            }
        };
    }

    public static DefaultTableModel crearModeloPaquetesHistorial() {
        return new DefaultTableModel(
                new Object[]{"Nombre", "Número de usos", "Último uso", "Peso"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static void configurarTablaProductos(JTable tabla) {
        tabla.setModel(crearModeloProductos());
        tabla.setRowHeight(65);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);

        tabla.setDefaultRenderer(ImageIcon.class, new RenderImagenTabla());
    }

    public static void configurarTablaPaquetes(JTable tabla, int tamaño) {
        tabla.setModel(crearModeloPaquetes());
        tabla.setRowHeight(tamaño);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(260);

        tabla.setDefaultRenderer(ImageIcon.class, new RenderImagenTabla());
    }

    public static void configurarTablaPaquetesHistorial(JTable tabla) {
        tabla.setModel(crearModeloPaquetesHistorial());
        tabla.setRowHeight(32);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(90);
    }

    public static void cargarProductos(JTable tabla, List<ProductoDTO> productos) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        if (productos == null || productos.isEmpty()) {
            return;
        }

        int tamañoImagen = Math.max(20, tabla.getRowHeight() - 10);

        for (ProductoDTO producto : productos) {
            modelo.addRow(new Object[]{
                crearIcono(producto.getImagen(), tamañoImagen),
                obtenerTextoSeguro(producto.getNombre()),
                producto.getUnidad() != null ? producto.getUnidad().toString() : "",
                formatoPeso(producto.getPeso()),
                formatoMoneda(producto.getPrecio())
            });
        }
    }

    public static void cargarPaquetes(JTable tabla, List<PaqueteDTO> paquetes) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        if (paquetes == null || paquetes.isEmpty()) {
            return;
        }

        int tamañoImagen = Math.max(20, tabla.getRowHeight() - 10);

        for (PaqueteDTO paquete : paquetes) {
            modelo.addRow(new Object[]{
                crearIcono(paquete.getImagen(), tamañoImagen),
                obtenerTextoSeguro(paquete.getNombre()),
                paquete.getTamaño() != null ? paquete.getTamaño().toString() : "",
                formatoMoneda(paquete.getPrecio()),
                formatoPeso(calcularPesoPaquete(paquete.getProductos())),
                obtenerProductosResumen(paquete.getProductos())
            });
        }
    }

    public static void cargarPaquetesHistorial(
            JTable tabla,
            ReporteHistorialPaquetePDFDTO reporte
    ) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        if (reporte == null || reporte.getPaqueteHistorial() == null
                || reporte.getPaqueteHistorial().isEmpty()) {
            return;
        }

        cargarPaquetesHistorial(tabla, reporte.getPaqueteHistorial());
    }

    public static void cargarPaquetesHistorial(
            JTable tabla,
            List<PaqueteHistorialDTO> paquetes
    ) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        if (paquetes == null || paquetes.isEmpty()) {
            return;
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (PaqueteHistorialDTO paquete : paquetes) {
            String ultimoUso = paquete.getUltimoUso() != null
                    ? paquete.getUltimoUso().format(formatoFecha)
                    : "Sin uso";

            modelo.addRow(new Object[]{
                obtenerTextoSeguro(paquete.getNombre()),
                paquete.getNumeroUsos(),
                ultimoUso,
                formatoPeso(paquete.getPesoPaquete())
            });
        }
    }

    private static float calcularPesoPaquete(List<ProductosPaqueteDTO> productos) {
        if (productos == null || productos.isEmpty()) {
            return 0.0f;
        }

        float pesoTotal = 0.0f;

        for (ProductosPaqueteDTO productoPaquete : productos) {
            if (productoPaquete != null) {
                pesoTotal += productoPaquete.getPesoTotal();
            }
        }

        return pesoTotal;
    }

    private static String obtenerProductosResumen(List<ProductosPaqueteDTO> productos) {
        if (productos == null || productos.isEmpty()) {
            return "Sin productos";
        }

        StringBuilder resumen = new StringBuilder();
        int limite = Math.min(productos.size(), LIMITE_PRODUCTOS_PAQUETE);

        for (int i = 0; i < limite; i++) {
            ProductosPaqueteDTO productoPaquete = productos.get(i);

            if (productoPaquete == null || productoPaquete.getProducto() == null) {
                continue;
            }

            if (resumen.length() > 0) {
                resumen.append(", ");
            }

            resumen.append(productoPaquete.getCantidad())
                    .append(" ")
                    .append(productoPaquete.getProducto().getNombre());
        }

        if (resumen.length() == 0) {
            return "Sin productos";
        }

        if (productos.size() > LIMITE_PRODUCTOS_PAQUETE) {
            resumen.append("...");
        }

        return resumen.toString();
    }

    private static ImageIcon crearIcono(byte[] imagenBytes, int tamaño) {
        return UtileriaImagen.bytesAImageIcon(imagenBytes, tamaño, tamaño);
    }

    private static String formatoMoneda(double precio) {
        return String.format("$%.2f", precio);
    }

    private static String formatoPeso(float peso) {
        return String.format("%.2f kg", peso);
    }

    private static String obtenerTextoSeguro(String texto) {
        return texto != null ? texto : "";
    }

    private static class RenderImagenTabla extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column
        ) {
            super.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column
            );

            setText("");

            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
            } else {
                setIcon(null);
                setText("Sin imagen");
            }

            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);

            return this;
        }
    }
}
