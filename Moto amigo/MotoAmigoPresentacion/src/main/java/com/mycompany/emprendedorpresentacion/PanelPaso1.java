package com.mycompany.emprendedorpresentacion;

import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jesus Omar
 */
public class PanelPaso1 extends JPanel{

    private final FrmRegistroEmprendedor frame;

    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JLabel lblFotoNombre;
    private JLabel lblErrorNombre;
    private JLabel lblErrorCorreo;
    private JLabel lblErrorTelefono;
    private JLabel lblErrorContrasenia;
    private JLabel lblErrorConfirmar;

    private byte[] fotoPerfil;

    public PanelPaso1(FrmRegistroEmprendedor frame) {
        this.frame = frame;
        setBackground(new Color(240, 242, 245));
        setLayout(null);
        initComponents();
    }

    private void initComponents() {
  
        JLabel lblNombre = new JLabel("NOMBRE COMPLETO *");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblNombre.setForeground(Color.GRAY);
        lblNombre.setBounds(10, 10, 250, 20);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtNombre.setBounds(10, 32, 560, 38);

        lblErrorNombre = new JLabel("");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorNombre.setBounds(10, 72, 560, 16);

        JLabel lblCorreo = new JLabel("CORREO ELECTRÓNICO *");
        lblCorreo.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblCorreo.setForeground(Color.GRAY);
        lblCorreo.setBounds(10, 92, 250, 20);

        txtCorreo = new JTextField();
        txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtCorreo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtCorreo.setBounds(10, 114, 560, 38);

        lblErrorCorreo = new JLabel("");
        lblErrorCorreo.setForeground(Color.RED);
        lblErrorCorreo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorCorreo.setBounds(10, 154, 560, 16);

        JLabel lblTelefono = new JLabel("INFORMACIÓN DE CONTACTO *");
        lblTelefono.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblTelefono.setForeground(Color.GRAY);
        lblTelefono.setBounds(10, 174, 250, 20);

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtTelefono.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtTelefono.setBounds(10, 196, 560, 38);

        lblErrorTelefono = new JLabel("");
        lblErrorTelefono.setForeground(Color.RED);
        lblErrorTelefono.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorTelefono.setBounds(10, 236, 560, 16);

        JLabel lblContrasenia = new JLabel("CONTRASEÑA *");
        lblContrasenia.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblContrasenia.setForeground(Color.GRAY);
        lblContrasenia.setBounds(10, 256, 250, 20);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtContrasenia.setBounds(10, 278, 560, 38);

        lblErrorContrasenia = new JLabel("");
        lblErrorContrasenia.setForeground(Color.RED);
        lblErrorContrasenia.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorContrasenia.setBounds(10, 318, 560, 16);

        JLabel lblConfirmar = new JLabel("CONFIRMAR CONTRASEÑA *");
        lblConfirmar.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblConfirmar.setForeground(Color.GRAY);
        lblConfirmar.setBounds(10, 338, 250, 20);

        txtConfirmarContrasenia = new JPasswordField();
        txtConfirmarContrasenia.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtConfirmarContrasenia.setBounds(10, 360, 560, 38);

        lblErrorConfirmar = new JLabel("");
        lblErrorConfirmar.setForeground(Color.RED);
        lblErrorConfirmar.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorConfirmar.setBounds(10, 400, 560, 16);

        JLabel lblFoto = new JLabel("FOTO DE PERFIL");
        lblFoto.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblFoto.setForeground(Color.GRAY);
        lblFoto.setBounds(10, 420, 200, 20);

        JButton btnSubirFoto = new JButton("📷  Subir foto");
        btnSubirFoto.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnSubirFoto.setBackground(Color.WHITE);
        btnSubirFoto.setBorder(BorderFactory.createDashedBorder(
                new Color(180, 180, 180), 4, 4));
        btnSubirFoto.setFocusPainted(false);
        btnSubirFoto.setBounds(10, 442, 280, 45);
        btnSubirFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubirFoto.addActionListener(e -> seleccionarFoto());

        lblFotoNombre = new JLabel("Ninguna foto seleccionada");
        lblFotoNombre.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblFotoNombre.setForeground(Color.GRAY);
        lblFotoNombre.setBounds(300, 455, 260, 20);

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBackground(new Color(230, 81, 0));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setBounds(310, 490, 260, 45);
        btnSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSiguiente.addActionListener(e -> siguiente());

        JLabel lblCambiar = new JLabel("Cambiar tipo de usuario");
        lblCambiar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCambiar.setForeground(new Color(100, 100, 180));
        lblCambiar.setHorizontalAlignment(SwingConstants.CENTER);
        lblCambiar.setBounds(10, 495, 280, 20);
        lblCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCambiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlNavegacionEmprendedor.getInstancia().irATipoUsuario();
            }
        });

        add(lblNombre);
        add(txtNombre);
        add(lblErrorNombre);
        add(lblCorreo);
        add(txtCorreo);
        add(lblErrorCorreo);
        add(lblTelefono);
        add(txtTelefono);
        add(lblErrorTelefono);
        add(lblContrasenia);
        add(txtContrasenia);
        add(lblErrorContrasenia);
        add(lblConfirmar);
        add(txtConfirmarContrasenia);
        add(lblErrorConfirmar);
        add(lblFoto);
        add(btnSubirFoto);
        add(lblFotoNombre);
        add(btnSiguiente);
        add(lblCambiar);
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(
                "Imágenes", "jpg", "jpeg", "png"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = chooser.getSelectedFile();
                fotoPerfil = Files.readAllBytes(archivo.toPath());
                lblFotoNombre.setText(archivo.getName());
                lblFotoNombre.setForeground(new Color(0, 150, 0));
            } catch (Exception ex) {
                lblFotoNombre.setText("Error al cargar la foto.");
                lblFotoNombre.setForeground(Color.RED);
            }
        }
    }

    private void siguiente() {
        limpiarErrores();
        boolean valido = true;

        if (txtNombre.getText().isBlank()) {
            lblErrorNombre.setText("Ingresa tu nombre completo.");
            marcarError(txtNombre);
            valido = false;
        }
        if (txtCorreo.getText().isBlank()) {
            lblErrorCorreo.setText("Ingresa un correo válido.");
            marcarError(txtCorreo);
            valido = false;
        }
        if (txtTelefono.getText().isBlank()) {
            lblErrorTelefono.setText("El teléfono debe tener 10 dígitos.");
            marcarError(txtTelefono);
            valido = false;
        }
        String pass = new String(txtContrasenia.getPassword());
        if (pass.isBlank()) {
            lblErrorContrasenia.setText("La contraseña debe tener al menos 8 caracteres.");
            marcarError(txtContrasenia);
            valido = false;
        }
        String confirmar = new String(txtConfirmarContrasenia.getPassword());
        if (!pass.equals(confirmar)) {
            lblErrorConfirmar.setText("Las contraseñas no coinciden.");
            marcarError(txtConfirmarContrasenia);
            valido = false;
        }

        if (valido) {
            frame.irSiguiente();
        }
    }

    private void marcarError(javax.swing.JComponent campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void limpiarErrores() {
        lblErrorNombre.setText("");
        lblErrorCorreo.setText("");
        lblErrorTelefono.setText("");
        lblErrorContrasenia.setText("");
        lblErrorConfirmar.setText("");

        javax.swing.border.Border normal = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        txtNombre.setBorder(normal);
        txtCorreo.setBorder(normal);
        txtTelefono.setBorder(normal);
        txtContrasenia.setBorder(normal);
        txtConfirmarContrasenia.setBorder(normal);
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getCorreo() {
        return txtCorreo.getText().trim();
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public String getContrasenia() {
        return new String(txtContrasenia.getPassword());
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

}
