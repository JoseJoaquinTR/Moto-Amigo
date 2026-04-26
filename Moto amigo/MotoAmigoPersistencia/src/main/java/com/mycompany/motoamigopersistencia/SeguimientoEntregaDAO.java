/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Ubicacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joset
 */
public class SeguimientoEntregaDAO implements ISeguimientoEntregaDAO {

    private List<Ubicacion> ruta = new ArrayList<>();
    private int indice = 0;
    private static SeguimientoEntregaDAO instancia;

    private SeguimientoEntregaDAO() {
        // Constructor vacío: ya no simula datos fijos
    }

    public static SeguimientoEntregaDAO getInstance() {
        if (instancia == null) {
            instancia = new SeguimientoEntregaDAO();
        }
        return instancia;
    }

    /**
     * Reemplaza la simulación por la ruta real calculada.
     */
    public void setRutaReal(List<Ubicacion> nuevaRuta) {
        this.ruta = nuevaRuta;
        this.indice = 0; // Reiniciar seguimiento
    }

    @Override
    public Ubicacion obtenerSiguiente() {
        if (ruta == null || ruta.isEmpty()) return null;

        if (indice < ruta.size()) {
            return ruta.get(indice++);
        }
        return ruta.get(ruta.size() - 1);
    }

    @Override
    public boolean esUltimoPunto() {
        return indice >= ruta.size();
    }
}
