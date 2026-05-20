package AdminPresentacion;

import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.ReporteEmprendedoresDTO;
import com.mycompany.motoamigopresentacion.controladores.ControlEmprendedor;
import enums.EstatusEmprendedorDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus Omar
 */
public class CatalogoEmprendedoresFORM extends JFrame{

    private JTextField txtBuscar;
    private JComboBox<EstatusEmprendedorDTO> cmbEstatus;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private List<EmprendedorDTO> emprendedores;

    public CatalogoEmprendedoresFORM() {
        initComponents();
        configurarVentana();
        cargarEmprendedores();
        configurarBuscador();
    }

    private void configurarVentana() {
        setTitle("Catálogo de Emprendedores");
        setSize(1008, 738);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(Color.WHITE);

        JPanel pnlHeader = new JPanel(null);
        pnlHeader.setBackground(Color.BLACK);
        pnlHeader.setBounds(0, 0, 1008, 50);

        JLabel lblHeader = new JLabel("MotoAmigo");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBounds(20, 12, 200, 25);
        pnlHeader.add(lblHeader);

        JLabel lblTitulo = new JLabel("Emprendedores");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setBounds(40, 65, 300, 35);

        JLabel lblBuscar = new JLabel("Nombre:");
        lblBuscar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblBuscar.setForeground(Color.GRAY);
        lblBuscar.setBounds(40, 112, 60, 20);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtBuscar.setBounds(105, 108, 300, 32);

        JLabel lblEstatus = new JLabel("Estatus:");
        lblEstatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblEstatus.setForeground(Color.GRAY);
        lblEstatus.setBounds(420, 112, 60, 20);

        cmbEstatus = new JComboBox<>();
        cmbEstatus.addItem(null); // todos
        for (EstatusEmprendedorDTO e : EstatusEmprendedorDTO.values()) {
            cmbEstatus.addItem(e);
        }
        cmbEstatus.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cmbEstatus.setBounds(485, 108, 180, 32);
        cmbEstatus.addActionListener(e -> filtrar());

        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Correo", "RFC", "Teléfono", "Estatus"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.setRowHeight(30);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(230, 81, 0));
        tabla.getTableHeader().setForeground(Color.WHITE);

        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(40, 155, 928, 460);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));

        JButton btnDetalle = new JButton("Ver Detalle");
        btnDetalle.setBackground(new Color(230, 81, 0));
        btnDetalle.setForeground(Color.WHITE);
        btnDetalle.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnDetalle.setBorderPainted(false);
        btnDetalle.setFocusPainted(false);
        btnDetalle.setBounds(718, 630, 250, 40);
        btnDetalle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDetalle.addActionListener(e -> abrirDetalle());

        JButton btnPDF = new JButton("Generar Reporte PDF");
        btnPDF.setBackground(Color.BLACK);
        btnPDF.setForeground(Color.WHITE);
        btnPDF.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnPDF.setBorderPainted(false);
        btnPDF.setFocusPainted(false);
        btnPDF.setBounds(40, 630, 250, 40);
        btnPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPDF.addActionListener(e -> generarPDF());

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnVolver.setBorder(BorderFactory.createLineBorder(
                new Color(200, 200, 200), 1, true));
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(310, 630, 150, 40);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> dispose());

        pnlPrincipal.add(pnlHeader);
        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblBuscar);
        pnlPrincipal.add(txtBuscar);
        pnlPrincipal.add(lblEstatus);
        pnlPrincipal.add(cmbEstatus);
        pnlPrincipal.add(scrollPane);
        pnlPrincipal.add(btnDetalle);
        pnlPrincipal.add(btnPDF);
        pnlPrincipal.add(btnVolver);

        setContentPane(pnlPrincipal);
    }

    private void configurarBuscador() {
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }
        });
    }

    private void filtrar() {
        String nombre = txtBuscar.getText().trim();
        EstatusEmprendedorDTO estatus = (EstatusEmprendedorDTO) cmbEstatus.getSelectedItem();

        emprendedores = ControlEmprendedor.getInstancia()
                .consultarEmprendedores(
                        nombre.isBlank() ? null : nombre,
                        null,
                        estatus
                );
        actualizarTabla();
    }

    public void cargarEmprendedores() {
        emprendedores = ControlEmprendedor.getInstancia()
                .consultarEmprendedores(null, null, null);
        actualizarTabla();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        if (emprendedores == null) {
            return;
        }
        for (EmprendedorDTO e : emprendedores) {
            modeloTabla.addRow(new Object[]{
                e.getIdEmprendedor(),
                e.getNombre(),
                e.getCorreo(),
                e.getRfc(),
                e.getTelefono(),
                e.getEstatus() != null ? e.getEstatus().name() : "—"
            });
        }
    }

    private void abrirDetalle() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un emprendedor de la tabla.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EmprendedorDTO seleccionado = emprendedores.get(fila);
        new DetalleEmprendedorFORM(seleccionado, this).setVisible(true);
    }

    private void generarPDF() {
        try {
            ReporteEmprendedoresDTO reporte = ControlEmprendedor.getInstancia()
                    .generarDatosReportePDF();

            if (reporte == null) {
                JOptionPane.showMessageDialog(this,
                        "Error al generar el reporte.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
            chooser.setSelectedFile(new java.io.File("reporte_emprendedores.pdf"));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String ruta = chooser.getSelectedFile().getAbsolutePath();
                if (!ruta.endsWith(".pdf")) {
                    ruta += ".pdf";
                }

                boolean exitoso = ControlEmprendedor.getInstancia()
                        .generarYDescargarReportePDF(reporte, ruta);

                if (exitoso) {
                    JOptionPane.showMessageDialog(this,
                            "Reporte generado correctamente en:\n" + ruta,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al generar el PDF: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
