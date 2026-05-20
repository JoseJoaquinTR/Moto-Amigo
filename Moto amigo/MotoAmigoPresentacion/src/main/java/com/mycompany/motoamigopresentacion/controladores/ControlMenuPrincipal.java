package com.mycompany.motoamigopresentacion.controladores;

import com.mycompany.curepartidor.BuscarRepartidorPorId;
import com.mycompany.curepartidor.IBuscarRepartidorPorId;
import com.mycompany.cusolicitarentrega.IListarEntregasEmprendedor;
import com.mycompany.cusolicitarentrega.IListarEntregasRepartidor;
import com.mycompany.cusolicitarentrega.ListarEntregasEmprendedor;
import com.mycompany.cusolicitarentrega.ListarEntregasRepartidor;
import com.mycompany.motoamigodto.EntregaDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigodto.SolicitudEntregaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.repartidorespresentacion.FrmPublicarPedidoRepartidor;
import com.mycompany.emprendedorpresentacion.FrmPublicarPedidosEmprendedor;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigopresentacion.FrmSeguimientoEnTiempoRealEmprendedor;
import com.mycompany.repartidorespresentacion.FrmSeguimientoTiempoRealRepartidor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import panelesUtilerias.PanelTarjetaPedido;

/**
 * Controlador del menú principal. Coordina la búsqueda de usuarios y la carga
 * de entregas mostradas en pantalla.
 *
 * @author Jesus Omar
 */
public class ControlMenuPrincipal {

    private static ControlMenuPrincipal instancia;

    private final IBuscarRepartidorPorId cuBuscarRepartidor;
    private final IListarEntregasRepartidor cuEntregasRepartidor;
    private final IListarEntregasEmprendedor cuEntregasEmprendedor;

    private ControlMenuPrincipal() {
        this.cuBuscarRepartidor = BuscarRepartidorPorId.crear();
        this.cuEntregasRepartidor = ListarEntregasRepartidor.crear();
        this.cuEntregasEmprendedor = ListarEntregasEmprendedor.crear();
    }

    /**
     * Obtiene la instancia única del controlador.
     *
     * @return instancia de ControlMenuPrincipal.
     */
    public static ControlMenuPrincipal getInstance() {
        if (instancia == null) {
            instancia = new ControlMenuPrincipal();
        }
        return instancia;
    }

    /**
     * Busca un repartidor por su identificador.
     *
     * @param id identificador del repartidor.
     * @return datos del repartidor; null si no se encuentra o si ocurre un
     * error.
     */
    public RepartidorDTO buscarRepartidorPorId(String id) {
        try {
            return cuBuscarRepartidor.buscarRepartidorPorId(id);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlMenuPrincipal.class.getName()).log(Level.SEVERE, "Error buscando repartidor", ex);
            return null;
        }
    }
    
    /**
     * Obtiene la lista de entregas según el filtro indicado.
     *
     * @param id identificador del repartidor o del emprendedor.
     * @param filtro "repartidor" para entregas asignadas; cualquier otro valor
     * para entregas del emprendedor.
     * @return lista de entregas; lista vacía si ocurre un error.
     */
    private List<EntregaDTO> obtenerEntregas(String id, String filtro) {
        try {
            if ("disponibles".equals(filtro)) {
                return cuEntregasRepartidor.obtenerEntregasDisponibles();
            }
            if ("repartidor".equals(filtro)) {
                return cuEntregasRepartidor.listarEntregasRepartidor(id);
            }
            return cuEntregasEmprendedor.listarEntregasEmprendedor(id);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlMenuPrincipal.class.getName()).log(Level.SEVERE, "Error listando entregas", ex);
            return Collections.emptyList();
        }
    }

    /**
     * Carga las entregas en el panel indicado, generando una tarjeta visual por
     * cada entrega encontrada.
     *
     * @param panel panel donde se renderizarán las tarjetas.
     * @param id identificador del repartidor o del emprendedor.
     * @param filtro "repartidor" o "emprendedor".
     */
    public void cargarEntregasEmprendedor(JPanel panel, String id, String filtro) {
        List<EntregaDTO> entregas = obtenerEntregas(id, filtro);
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        for (EntregaDTO entrega : entregas) {
            PanelTarjetaPedido tarjetaPedido = new PanelTarjetaPedido();
            tarjetaPedido.cargarDatosEnTarjeta(entrega);
            tarjetaPedido.setBackground(new Color(248, 249, 250));
            tarjetaPedido.setOpaque(true);
            Border margenExterior = BorderFactory.createEmptyBorder(0, 10, 15, 10);
            Border lineaSuave = BorderFactory.createLineBorder(new Color(222, 226, 230), 1);
            tarjetaPedido.setBorder(BorderFactory.createCompoundBorder(margenExterior, lineaSuave));
            tarjetaPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, tarjetaPedido.getPreferredSize().height));
            panel.add(tarjetaPedido);
        }
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Carga en el panel las entregas activas del repartidor (en curso) y las
     * entregas disponibles que puede tomar. Las en curso aparecen primero.
     */
    public void cargarEntregasRepartidor(JPanel panel, String idRepartidor) {
        try {
            List<EntregaDTO> enCurso = idRepartidor != null
                    ? cuEntregasRepartidor.listarEntregasRepartidor(idRepartidor)
                    : Collections.emptyList();

            List<EntregaDTO> disponibles = cuEntregasRepartidor.obtenerEntregasDisponibles();

            panel.removeAll();
            panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));

            // Primero las en curso (lo prioritario para el repartidor)
            for (EntregaDTO entrega : enCurso) {
                agregarTarjeta(panel, entrega);
            }
            // Después las disponibles
            for (EntregaDTO entrega : disponibles) {
                agregarTarjeta(panel, entrega);
            }

            panel.revalidate();
            panel.repaint();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlMenuPrincipal.class.getName())
                    .log(Level.SEVERE, "Error listando entregas del repartidor", ex);
        }
    }

    private void agregarTarjeta(JPanel panel, EntregaDTO entrega) {
        PanelTarjetaPedido tarjetaPedido = new PanelTarjetaPedido();
        tarjetaPedido.cargarDatosEnTarjeta(entrega);
        tarjetaPedido.setBackground(new Color(248, 249, 250));
        tarjetaPedido.setOpaque(true);
        Border margenExterior = BorderFactory.createEmptyBorder(0, 10, 15, 10);
        Border lineaSuave = BorderFactory.createLineBorder(new Color(222, 226, 230), 1);
        tarjetaPedido.setBorder(BorderFactory.createCompoundBorder(margenExterior, lineaSuave));
        tarjetaPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, tarjetaPedido.getPreferredSize().height));
        panel.add(tarjetaPedido);
    }

    /**
     * Abre la pantalla de publicación de pedido del emprendedor.
     */
    public void abrirPantallaEnvioEmpr() {
        new FrmPublicarPedidosEmprendedor().setVisible(true);
    }

    /**
     * Abre la pantalla de detalle de un pedido para el repartidor.
     *
     * @param entrega entrega cuyo detalle se mostrará.
     */
    public void mostrarDetallePedido(EntregaDTO entrega) {
        SolicitudEntregaDTO solicitud = new SolicitudEntregaDTO();
        solicitud.setIdSolicitud(entrega.getId());           
        solicitud.setIdEmprendedor(entrega.getIdEmprendedor());
        solicitud.setOrigen(entrega.getDireccionOrigen());
        solicitud.setDestino(entrega.getDireccionDestino());
        solicitud.setTipo(entrega.getTipo());
        solicitud.setPaquetes(entrega.getPaquetes());
        solicitud.setSobre(entrega.getSobre());
        solicitud.setPesoTotal(entrega.getPesoTotal());
        solicitud.setDistancia(entrega.getDistancia());
        solicitud.setCosto(entrega.getCosto());
        solicitud.setEstado(entrega.getEstadoEntrega());
        new FrmPublicarPedidoRepartidor(solicitud).setVisible(true);
    }

    /**
     * Abre directamente el mapa de seguimiento de una entrega en curso, según
     * el rol del usuario activo (repartidor o emprendedor).
     *
     * @param entrega
     */
    public void continuarPedido(EntregaDTO entrega) {
        if (entrega == null) {
            return;
        }

        RutaResponseDTO ruta = ControlSolicitarEntrega.getInstance().obtenerRuta(
                entrega.getDireccionOrigen(),
                entrega.getDireccionDestino()
        );

        if (ruta == null || !ruta.isRutaValida()) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo obtener la ruta de esta entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (SesionActiva.getInstancia().esRepartidor()) {
            new FrmSeguimientoTiempoRealRepartidor(ruta, entrega.getId()).setVisible(true);
        } else {
            new FrmSeguimientoEnTiempoRealEmprendedor(ruta).setVisible(true);
        }
    }
}
