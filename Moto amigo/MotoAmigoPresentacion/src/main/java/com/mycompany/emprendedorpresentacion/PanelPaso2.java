package com.mycompany.emprendedorpresentacion;

import enums.TipoNegocioDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.mycompany.emprendedorpresentacion.FrmTipoUsuario;
import com.mycompany.motoamigopresentacion.controladores.ControlNavegacionEmprendedor;

/**
 * Panel del segundo paso del registro del emprendedor
 *
 * @author Jesus Omar
 */
public class PanelPaso2 extends JPanel {

    private final FrmRegistroEmprendedor frame;

    private JTextField txtNombreNegocio;
    private JComboBox<TipoNegocioDTO> cmbTipoNegocio;
    private JTextField txtCalle;
    private JTextField txtNumero;
    private JTextField txtColonia;
    private JTextField txtCodigoPostal;

    private JLabel lblErrorNombre;
    private JLabel lblErrorTipo;
    private JLabel lblErrorCalle;
    private JLabel lblErrorNumero;
    private JLabel lblErrorColonia;
    private JLabel lblErrorCP;

    public PanelPaso2(FrmRegistroEmprendedor frame) {
        this.frame = frame;
        setBackground(new Color(240, 242, 245));
        setLayout(null);
        initComponents();
    }

    private void initComponents() {

        JLabel lblNombre = new JLabel("NOMBRE DEL NEGOCIO");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblNombre.setForeground(Color.GRAY);
        lblNombre.setBounds(10, 10, 250, 20);

        txtNombreNegocio = new JTextField();
        txtNombreNegocio.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtNombreNegocio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtNombreNegocio.setBounds(10, 32, 560, 38);

        lblErrorNombre = new JLabel("");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorNombre.setBounds(10, 72, 560, 16);

        JLabel lblTipo = new JLabel("TIPO DE NEGOCIO *");
        lblTipo.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblTipo.setForeground(Color.GRAY);
        lblTipo.setBounds(10, 92, 250, 20);

        cmbTipoNegocio = new JComboBox<>(TipoNegocioDTO.values());
        cmbTipoNegocio.insertItemAt(null, 0);
        cmbTipoNegocio.setSelectedIndex(0);
        cmbTipoNegocio.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cmbTipoNegocio.setBounds(10, 114, 560, 38);

        lblErrorTipo = new JLabel("");
        lblErrorTipo.setForeground(Color.RED);
        lblErrorTipo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorTipo.setBounds(10, 154, 560, 16);

        JLabel lblCalle = new JLabel("CALLE *");
        lblCalle.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblCalle.setForeground(Color.GRAY);
        lblCalle.setBounds(10, 174, 250, 20);

        txtCalle = new JTextField();
        txtCalle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtCalle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtCalle.setBounds(10, 196, 560, 38);

        lblErrorCalle = new JLabel("");
        lblErrorCalle.setForeground(Color.RED);
        lblErrorCalle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorCalle.setBounds(10, 236, 560, 16);

        JLabel lblNumero = new JLabel("NÚMERO *");
        lblNumero.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblNumero.setForeground(Color.GRAY);
        lblNumero.setBounds(10, 256, 150, 20);

        JLabel lblColonia = new JLabel("COLONIA *");
        lblColonia.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblColonia.setForeground(Color.GRAY);
        lblColonia.setBounds(290, 256, 150, 20);

        txtNumero = new JTextField();
        txtNumero.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtNumero.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtNumero.setBounds(10, 278, 260, 38);

        txtColonia = new JTextField();
        txtColonia.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtColonia.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtColonia.setBounds(290, 278, 280, 38);

        lblErrorNumero = new JLabel("");
        lblErrorNumero.setForeground(Color.RED);
        lblErrorNumero.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorNumero.setBounds(10, 318, 260, 16);

        lblErrorColonia = new JLabel("");
        lblErrorColonia.setForeground(Color.RED);
        lblErrorColonia.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorColonia.setBounds(290, 318, 280, 16);

        JLabel lblCP = new JLabel("CÓDIGO POSTAL *");
        lblCP.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblCP.setForeground(Color.GRAY);
        lblCP.setBounds(10, 338, 200, 20);

        txtCodigoPostal = new JTextField();
        txtCodigoPostal.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtCodigoPostal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtCodigoPostal.setBounds(10, 360, 260, 38);

        lblErrorCP = new JLabel("");
        lblErrorCP.setForeground(Color.RED);
        lblErrorCP.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblErrorCP.setBounds(10, 400, 260, 16);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setForeground(Color.BLACK);
        btnAtras.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAtras.setBorder(BorderFactory.createLineBorder(
                new Color(200, 200, 200), 1, true));
        btnAtras.setFocusPainted(false);
        btnAtras.setBounds(10, 460, 260, 45);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtras.addActionListener(e -> frame.irAtras());

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBackground(new Color(230, 81, 0));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setBounds(290, 460, 280, 45);
        btnSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSiguiente.addActionListener(e -> siguiente());

        JLabel lblCambiar = new JLabel("Cambiar tipo de usuario");
        lblCambiar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCambiar.setForeground(new Color(100, 100, 180));
        lblCambiar.setHorizontalAlignment(SwingConstants.CENTER);
        lblCambiar.setBounds(10, 515, 560, 20);
        lblCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCambiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlNavegacionEmprendedor.getInstancia().irATipoUsuario();
            }
        });

        add(lblNombre);
        add(txtNombreNegocio);
        add(lblErrorNombre);
        add(lblTipo);
        add(cmbTipoNegocio);
        add(lblErrorTipo);
        add(lblCalle);
        add(txtCalle);
        add(lblErrorCalle);
        add(lblNumero);
        add(txtNumero);
        add(lblErrorNumero);
        add(lblColonia);
        add(txtColonia);
        add(lblErrorColonia);
        add(lblCP);
        add(txtCodigoPostal);
        add(lblErrorCP);
        add(btnAtras);
        add(btnSiguiente);
        add(lblCambiar);
    }

    private void siguiente() {
        limpiarErrores();
        boolean valido = true;

        if (txtNombreNegocio.getText().isBlank()) {
            lblErrorNombre.setText("El nombre del negocio es obligatorio");
            marcarError(txtNombreNegocio);
            valido = false;
        } else if (!txtNombreNegocio.getText().matches("^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ0-9\\s]+$")) {
            lblErrorNombre.setText("El nombre del negocio solo puede tener letras, numeros y espacios");
            marcarError(txtNombreNegocio);
            valido = false;
        }

        if (cmbTipoNegocio.getSelectedItem() == null) {
            lblErrorTipo.setText("Selecciona el tipo de negocio");
            valido = false;
        }

        if (txtCalle.getText().isBlank()) {
            lblErrorCalle.setText("La calle es obligatoria");
            marcarError(txtCalle);
            valido = false;
        }
        if (txtNumero.getText().isBlank()) {
            lblErrorNumero.setText("El numero es obligatorio");
            marcarError(txtNumero);
            valido = false;
        } else if (txtNumero.getText().matches("^\\d{3,6}$")) {
            lblErrorNumero.setText("El numero de la direccion solo puede tener digitos");
            marcarError(txtNumero);
            valido = false;
        }

        if (txtColonia.getText().isBlank()) {
            lblErrorColonia.setText("La colonia es obligatoria");
            marcarError(txtColonia);
            valido = false;
        } else if (!txtColonia.getText().matches("^[a-záéíóúüñA-ZÁÉÍÓÚÜÑ\\s]+$")) {
            lblErrorColonia.setText("El nombre de la colonia solo puede tener letras y espacios");
            marcarError(txtColonia);
            valido = false;
        }

        if (txtCodigoPostal.getText().isBlank()) {
            lblErrorCP.setText("El codigo postal es obligatorio");
            marcarError(txtCodigoPostal);
            valido = false;
        } else if (!txtCodigoPostal.getText().matches("^\\d{5}$")) {
            lblErrorCP.setText("El codigo postal debe tener exactamente 5 digitos");
            marcarError(txtCodigoPostal);
            valido = false;
        }

        if (valido) {
            frame.irSiguiente();
        }
    }

    private void marcarError(JTextField campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void limpiarErrores() {
        lblErrorNombre.setText("");
        lblErrorTipo.setText("");
        lblErrorCalle.setText("");
        lblErrorNumero.setText("");
        lblErrorColonia.setText("");
        lblErrorCP.setText("");

        javax.swing.border.Border normal = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        txtNombreNegocio.setBorder(normal);
        txtCalle.setBorder(normal);
        txtNumero.setBorder(normal);
        txtColonia.setBorder(normal);
        txtCodigoPostal.setBorder(normal);
    }

    /**
     * Regresa el nombre del negocio que ingreso el emprendedor
     *
     * @return nombre del negocio
     */
    public String getNombreNegocio() {
        return txtNombreNegocio.getText().trim();
    }

    /**
     * Regresa el tipo de negocio que selecciono el emprendedor
     *
     * @return tipo de negocio
     */
    public TipoNegocioDTO getTipoNegocio() {
        return (TipoNegocioDTO) cmbTipoNegocio.getSelectedItem();
    }

    /**
     * Regresa la calle que ingreso el emprendedor
     *
     * @return calle
     */
    public String getCalle() {
        return txtCalle.getText().trim();
    }

    /**
     * Regresa el numero que ingreso el emprendedor
     *
     * @return numero de la direccion
     */
    public String getNumero() {
        return txtNumero.getText().trim();
    }

    /**
     * Regresa la colonia que ingreso el emprendedor
     *
     * @return colonia
     */
    public String getColonia() {
        return txtColonia.getText().trim();
    }

    /**
     * Regresa el codigo postal que ingreso el emprendedor
     *
     * @return codigo postal
     */
    public String getCodigoPostal() {
        return txtCodigoPostal.getText().trim();
    }
}
