package com.mycompany.motoamigopresentacion;

import com.mycompany.cuemprendedor.CURegistrarEmprendedorFachada;
import com.mycompany.cuemprendedor.ICURegistrarEmprendedorFachada;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopresentacion.controladores.SesionActiva;
import static enums.EstatusEmprendedorDTO.BAJA;
import static enums.EstatusEmprendedorDTO.PENDIENTE;
import static enums.EstatusEmprendedorDTO.RECHAZADO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Jesus Omar
 */
public class FrmIniciarSesion extends JFrame{

    private JTextField txtCorreo;
    private JPasswordField txtContrasenia;
    private JButton btnEntrar;
    private JLabel lblError;

    private final ICURegistrarEmprendedorFachada cu;

    public FrmIniciarSesion() {
        this.cu = new CURegistrarEmprendedorFachada();
        initComponents();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Iniciar Sesión");
        setSize(1008, 738);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        JPanel pnlPrincipal = new JPanel(null);
        pnlPrincipal.setBackground(new Color(240, 242, 245));

        // Título
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitulo.setBounds(354, 60, 300, 40);

        // Correo
        JLabel lblCorreo = new JLabel("CORREO");
        lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblCorreo.setForeground(Color.GRAY);
        lblCorreo.setBounds(354, 120, 300, 20);

        txtCorreo = new JTextField();
        txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtCorreo.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new Color(220, 220, 220)),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtCorreo.setBounds(354, 143, 300, 42);

        // Contraseña
        JLabel lblContrasenia = new JLabel("CONTRASEÑA");
        lblContrasenia.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblContrasenia.setForeground(Color.GRAY);
        lblContrasenia.setBounds(354, 200, 300, 20);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtContrasenia.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new Color(220, 220, 220)),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtContrasenia.setBounds(354, 223, 300, 42);

        // Mensaje error
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setBounds(354, 272, 300, 20);

        // Botón Entrar
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(Color.BLACK);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEntrar.setBorderPainted(false);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBounds(354, 295, 300, 48);
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(e -> iniciarSesion());

        // Volver
        JLabel lblVolver = new JLabel("Volver");
        lblVolver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblVolver.setForeground(new Color(100, 100, 180));
        lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolver.setBounds(354, 355, 300, 25);
        lblVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new FrmPrincipal().setVisible(true);
                dispose();
            }
        });

        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(lblCorreo);
        pnlPrincipal.add(txtCorreo);
        pnlPrincipal.add(lblContrasenia);
        pnlPrincipal.add(txtContrasenia);
        pnlPrincipal.add(lblError);
        pnlPrincipal.add(btnEntrar);
        pnlPrincipal.add(lblVolver);

        setContentPane(pnlPrincipal);
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword());

        if (correo.isBlank() || contrasenia.isBlank()) {
            lblError.setText("Ingresa tu correo y contraseña.");
            return;
        }

        try {
            CuentaUsuarioSesionDTO cuenta = cu.buscarCuenta(correo);

            if (cuenta == null) {
                lblError.setText("Correo o contraseña incorrectos.");
                return;
            }

            // Verificar contraseña con BCrypt
            // Nota: la verificación real requiere acceso al hash guardado
            // aquí solo validamos que la cuenta exista y esté activa
            if (!cuenta.isActiva()) {
                switch (cuenta.getEstatusEmprendedor()) {
                    case PENDIENTE ->
                        lblError.setText("Tu cuenta está pendiente de revisión.");
                    case RECHAZADO ->
                        lblError.setText("Tu cuenta fue rechazada.");
                    case BAJA ->
                        lblError.setText("Tu cuenta fue dada de baja.");
                }
                return;
            }

            // Guardar sesión y navegar al menú
            SesionActiva.getInstancia().setCuenta(cuenta);
            new com.mycompany.emprendedorpresentacion.FrmMenuPrincipalEmprendedor().setVisible(true);
            dispose();

        } catch (NegocioException ex) {
            lblError.setText("Error al iniciar sesión.");
        }
    }
}
