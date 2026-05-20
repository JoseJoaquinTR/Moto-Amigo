package com.mycompany.emprendedorpresentacion;

import com.mycompany.motoamigopresentacion.controladores.ControlEmprendedor;
import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;
import enums.BancoDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jesus Omar
 */
public class PanelPaso3 extends JPanel{

    private final FrmRegistroEmprendedor frame;

    private JTextField txtRfc;
    private JTextField txtNombreTitular;
    private JComboBox<BancoDTO> cmbBanco;
    private JTextField txtNumeroCuenta;
    private JLabel lblIneNombre;
    private JLabel lblErrorIne;
    private JLabel lblErrorRfc;
    private JLabel lblErrorNombreTitular;
    private JLabel lblErrorBanco;
    private JLabel lblErrorCuenta;

    private byte[] documentoIne;

    public PanelPaso3(FrmRegistroEmprendedor frame) {
        this.frame = frame;
        setBackground(new Color(240, 242, 245));
        setLayout(null);
        initComponents();
    }

    private void initComponents() {

        JLabel lblIne = new JLabel("INE *");
        lblIne.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblIne.setForeground(Color.GRAY);
        lblIne.setBounds(10, 10, 200, 20);

        JButton btnSubirIne = new JButton("Toca para subir archivo");
        btnSubirIne.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnSubirIne.setBackground(Color.WHITE);
        btnSubirIne.setBorder(BorderFactory.createDashedBorder(
                new Color(180, 180, 180), 4, 4));
        btnSubirIne.setFocusPainted(false);
        btnSubirIne.setBounds(10, 32, 560, 50);
        btnSubirIne.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubirIne.addActionListener(e -> seleccionarIne());

        lblIneNombre = new JLabel("Ningun archivo seleccionado");
        lblIneNombre.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblIneNombre.setForeground(Color.GRAY);
        lblIneNombre.setBounds(10, 84, 560, 16);

        lblErrorIne = new JLabel("");
        lblErrorIne.setForeground(Color.RED);
        lblErrorIne.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorIne.setBounds(10, 102, 560, 16);

        JLabel lblRfc = new JLabel("RFC *");
        lblRfc.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblRfc.setForeground(Color.GRAY);
        lblRfc.setBounds(10, 122, 200, 20);

        txtRfc = new JTextField();
        txtRfc.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtRfc.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtRfc.setBounds(10, 144, 560, 38);

        lblErrorRfc = new JLabel("");
        lblErrorRfc.setForeground(Color.RED);
        lblErrorRfc.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorRfc.setBounds(10, 184, 560, 16);

        JLabel lblNombreTitular = new JLabel("NOMBRE DEL TITULAR DE LA CUENTA *");
        lblNombreTitular.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblNombreTitular.setForeground(Color.GRAY);
        lblNombreTitular.setBounds(10, 204, 350, 20);

        txtNombreTitular = new JTextField();
        txtNombreTitular.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtNombreTitular.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtNombreTitular.setBounds(10, 226, 560, 38);

        lblErrorNombreTitular = new JLabel("");
        lblErrorNombreTitular.setForeground(Color.RED);
        lblErrorNombreTitular.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorNombreTitular.setBounds(10, 266, 560, 16);

        JLabel lblBanco = new JLabel("INSTITUCION BANCARIA *");
        lblBanco.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblBanco.setForeground(Color.GRAY);
        lblBanco.setBounds(10, 286, 250, 20);

        cmbBanco = new JComboBox<>(BancoDTO.values());
        cmbBanco.insertItemAt(null, 0);
        cmbBanco.setSelectedIndex(0);
        cmbBanco.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cmbBanco.setBounds(10, 308, 560, 38);

        lblErrorBanco = new JLabel("");
        lblErrorBanco.setForeground(Color.RED);
        lblErrorBanco.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorBanco.setBounds(10, 348, 560, 16);

        JLabel lblCuenta = new JLabel("NUMERO DE CUENTA BANCARIA");
        lblCuenta.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblCuenta.setForeground(Color.GRAY);
        lblCuenta.setBounds(10, 368, 300, 20);

        txtNumeroCuenta = new JTextField();
        txtNumeroCuenta.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtNumeroCuenta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtNumeroCuenta.setBounds(10, 390, 560, 38);

        lblErrorCuenta = new JLabel("");
        lblErrorCuenta.setForeground(Color.RED);
        lblErrorCuenta.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorCuenta.setBounds(10, 430, 560, 16);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setForeground(Color.BLACK);
        btnAtras.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAtras.setBorder(BorderFactory.createLineBorder(
                new Color(200, 200, 200), 1, true));
        btnAtras.setFocusPainted(false);
        btnAtras.setBounds(10, 450, 260, 45);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtras.addActionListener(e -> frame.irAtras());

        JButton btnRegistrar = new JButton("Registrarme");
        btnRegistrar.setBackground(new Color(230, 81, 0));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBounds(290, 450, 280, 45);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(e -> registrar());

        JLabel lblCambiar = new JLabel("Cambiar tipo de usuario");
        lblCambiar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCambiar.setForeground(new Color(100, 100, 180));
        lblCambiar.setHorizontalAlignment(SwingConstants.CENTER);
        lblCambiar.setBounds(10, 505, 560, 20);
        lblCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCambiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlNavegacionEmprendedor.getInstancia().irATipoUsuario();
            }
        });

        add(lblIne);
        add(btnSubirIne);
        add(lblIneNombre);
        add(lblErrorIne);
        add(lblRfc);
        add(txtRfc);
        add(lblErrorRfc);
        add(lblNombreTitular);
        add(txtNombreTitular);
        add(lblErrorNombreTitular);
        add(lblBanco);
        add(cmbBanco);
        add(lblErrorBanco);
        add(lblCuenta);
        add(txtNumeroCuenta);
        add(lblErrorCuenta);
        add(btnAtras);
        add(btnRegistrar);
        add(lblCambiar);
    }

    private void seleccionarIne() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(
                "PDF e imágenes", "pdf", "jpg", "jpeg", "png"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = chooser.getSelectedFile();
                documentoIne = Files.readAllBytes(archivo.toPath());
                lblIneNombre.setText(archivo.getName());
                lblIneNombre.setForeground(new Color(0, 150, 0));
                lblErrorIne.setText("");
            } catch (Exception ex) {
                lblIneNombre.setText("Error al cargar el archivo");
                lblIneNombre.setForeground(Color.RED);
            }
        }
    }

    private void registrar() {
        limpiarErrores();
        boolean valido = true;

        if (documentoIne == null) {
            lblErrorIne.setText("Debes subir tu INE");
            valido = false;
        }
        if (txtRfc.getText().isBlank()) {
            lblErrorRfc.setText("Ingresa un RFC valido");
            marcarError(txtRfc);
            valido = false;
        }
        if (txtNombreTitular.getText().isBlank()) {
            lblErrorNombreTitular.setText("Ingresa el nombre del titular de la cuenta");
            marcarError(txtNombreTitular);
            valido = false;
        }
        if (cmbBanco.getSelectedItem() == null) {
            lblErrorBanco.setText("Selecciona tu institucion bancaria");
            valido = false;
        }

        if (!valido) {
            return;
        }

        String idRegistrado = ControlEmprendedor.getInstancia().registrarEmprendedor(
                frame.getPaso1(), frame.getPaso2(), frame.getPaso3()
        );

        if (idRegistrado != null) {
            ControlNavegacionEmprendedor.getInstancia().irAEsperaValidacion(idRegistrado);
        }
    }

    private void marcarError(JTextField campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void limpiarErrores() {
        lblErrorIne.setText("");
        lblErrorRfc.setText("");
        lblErrorNombreTitular.setText("");
        lblErrorBanco.setText("");
        lblErrorCuenta.setText("");

        javax.swing.border.Border normal = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        txtRfc.setBorder(normal);
        txtNombreTitular.setBorder(normal);
        txtNumeroCuenta.setBorder(normal);
    }

    public String getRfc() {
        return txtRfc.getText().trim();
    }

    public String getNombreTitular() {
        return txtNombreTitular.getText().trim();
    }

    public BancoDTO getInstitucionBancaria() {
        return (BancoDTO) cmbBanco.getSelectedItem();
    }

    public String getNumeroCuenta() {
        return txtNumeroCuenta.getText().trim();
    }

    public byte[] getDocumentoIne() {
        return documentoIne;
    }
}
