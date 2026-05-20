package AdminPresentacion;

import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.motoamigopresentacion.controladores.ControlEmprendedor;
import enums.EstatusEmprendedorDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Omar
 */
public class DetalleEmprendedorFORM extends JFrame {

    private final EmprendedorDTO emprendedor;
    private final CatalogoEmprendedoresFORM catalogo;
    private JComboBox<EstatusEmprendedorDTO> cmbEstatus;

    public DetalleEmprendedorFORM(EmprendedorDTO emprendedor,
            CatalogoEmprendedoresFORM catalogo) {
        this.emprendedor = emprendedor;
        this.catalogo = catalogo;
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Detalle Emprendedor");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(new Color(240, 242, 245));

        JLabel lblTitulo = new JLabel("Detalle del Emprendedor");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 500, 30);

        JLabel lblNombreLabel = new JLabel("Nombre:");
        lblNombreLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblNombreLabel.setForeground(Color.GRAY);
        lblNombreLabel.setBounds(40, 65, 100, 20);

        JLabel lblNombre = new JLabel(emprendedor.getNombre() != null
                ? emprendedor.getNombre() : "—");
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblNombre.setBounds(150, 65, 380, 20);

        JLabel lblCorreoLabel = new JLabel("Correo:");
        lblCorreoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblCorreoLabel.setForeground(Color.GRAY);
        lblCorreoLabel.setBounds(40, 95, 100, 20);

        JLabel lblCorreo = new JLabel(emprendedor.getCorreo() != null
                ? emprendedor.getCorreo() : "—");
        lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblCorreo.setBounds(150, 95, 380, 20);

        JLabel lblTelefonoLabel = new JLabel("Teléfono:");
        lblTelefonoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTelefonoLabel.setForeground(Color.GRAY);
        lblTelefonoLabel.setBounds(40, 125, 100, 20);

        JLabel lblTelefono = new JLabel(emprendedor.getTelefono() != null
                ? emprendedor.getTelefono() : "—");
        lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTelefono.setBounds(150, 125, 380, 20);

        JLabel lblRfcLabel = new JLabel("RFC:");
        lblRfcLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblRfcLabel.setForeground(Color.GRAY);
        lblRfcLabel.setBounds(40, 155, 100, 20);

        JLabel lblRfc = new JLabel(emprendedor.getRfc() != null
                ? emprendedor.getRfc() : "—");
        lblRfc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblRfc.setBounds(150, 155, 380, 20);

        JLabel lblEstatusLabel = new JLabel("Estatus:");
        lblEstatusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblEstatusLabel.setForeground(Color.GRAY);
        lblEstatusLabel.setBounds(40, 185, 100, 20);

        JLabel lblEstatusActual = new JLabel(emprendedor.getEstatus() != null
                ? emprendedor.getEstatus().name() : "—");
        lblEstatusActual.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblEstatusActual.setForeground(new Color(230, 81, 0));
        lblEstatusActual.setBounds(150, 185, 380, 20);

        JPanel separador = new JPanel();
        separador.setBackground(new Color(210, 210, 210));
        separador.setBounds(40, 215, 520, 1);

        JLabel lblIneLabel = new JLabel("Documento INE:");
        lblIneLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblIneLabel.setForeground(Color.GRAY);
        lblIneLabel.setBounds(40, 225, 150, 20);

        JLabel lblDocumento = new JLabel();
        lblDocumento.setBorder(BorderFactory.createLineBorder(
                new Color(210, 210, 210), 1));
        lblDocumento.setHorizontalAlignment(SwingConstants.CENTER);
        lblDocumento.setBounds(40, 250, 520, 170);
        lblDocumento.setBackground(new Color(245, 245, 245));
        lblDocumento.setOpaque(true);

        try {
            com.mycompany.emprendedoresdto.DocumentoDTO doc
                    = ControlEmprendedor.getInstancia()
                            .obtenerDocumentoEmprendedor(emprendedor.getIdEmprendedor());

            if (doc != null && doc.getDocumento() != null) {
                BufferedImage img = ImageIO.read(
                        new ByteArrayInputStream(doc.getDocumento()));
                if (img != null) {
                    Image scaled = img.getScaledInstance(
                            510, 160, Image.SCALE_SMOOTH);
                    lblDocumento.setIcon(new ImageIcon(scaled));
                } else {
                    lblDocumento.setText("Documento PDF — no se puede previsualizar");
                    lblDocumento.setFont(new Font("SansSerif", Font.PLAIN, 12));
                    lblDocumento.setForeground(Color.GRAY);
                }
            } else {
                lblDocumento.setText("Sin documento");
                lblDocumento.setFont(new Font("SansSerif", Font.PLAIN, 12));
                lblDocumento.setForeground(Color.GRAY);
            }
        } catch (Exception ex) {
            lblDocumento.setText("No se pudo cargar el documento.");
            lblDocumento.setFont(new Font("SansSerif", Font.PLAIN, 12));
            lblDocumento.setForeground(Color.GRAY);
        }

        JLabel lblNuevoEstatus = new JLabel("CAMBIAR ESTATUS:");
        lblNuevoEstatus.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblNuevoEstatus.setForeground(Color.GRAY);
        lblNuevoEstatus.setBounds(40, 435, 150, 20);

        cmbEstatus = new JComboBox<>(EstatusEmprendedorDTO.values());
        cmbEstatus.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cmbEstatus.setBounds(200, 431, 200, 32);
        if (emprendedor.getEstatus() != null) {
            cmbEstatus.setSelectedItem(emprendedor.getEstatus());
        }

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(230, 81, 0));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(415, 431, 145, 32);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(e -> guardar());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setForeground(Color.BLACK);
        btnCerrar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCerrar.setBorder(BorderFactory.createLineBorder(
                new Color(200, 200, 200), 1, true));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBounds(40, 510, 520, 40);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());

        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblNombreLabel);
        pnlPrincipal.add(lblNombre);
        pnlPrincipal.add(lblCorreoLabel);
        pnlPrincipal.add(lblCorreo);
        pnlPrincipal.add(lblTelefonoLabel);
        pnlPrincipal.add(lblTelefono);
        pnlPrincipal.add(lblRfcLabel);
        pnlPrincipal.add(lblRfc);
        pnlPrincipal.add(lblEstatusLabel);
        pnlPrincipal.add(lblEstatusActual);
        pnlPrincipal.add(separador);
        pnlPrincipal.add(lblIneLabel);
        pnlPrincipal.add(lblDocumento);
        pnlPrincipal.add(lblNuevoEstatus);
        pnlPrincipal.add(cmbEstatus);
        pnlPrincipal.add(btnGuardar);
        pnlPrincipal.add(btnCerrar);

        setContentPane(pnlPrincipal);
    }

    private void guardar() {
        EstatusEmprendedorDTO nuevoEstatus
                = (EstatusEmprendedorDTO) cmbEstatus.getSelectedItem();

        boolean exitoso = ControlEmprendedor.getInstancia()
                .actualizarEstatusEmprendedor(
                        emprendedor.getIdEmprendedor(), nuevoEstatus);

        if (exitoso) {
            JOptionPane.showMessageDialog(this,
                    "Estatus actualizado correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            catalogo.cargarEmprendedores();
            dispose();
        }
    }

}
