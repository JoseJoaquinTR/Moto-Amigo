package com.mycompany.emprendedoresbo;

import Adapter.AdapterDireccion;
import Adapter.AdapterNegocio;
import com.mycompany.Entidades.Direccion;
import com.mycompany.Entidades.Negocio;
import com.mycompany.emprendedoresdto.DireccionDTO;
import com.mycompany.emprendedoresdto.NegocioDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigopersistencia.PersistenciaException;
import fachada.FachadaPersistencia;
import fachada.IFachadaPersistencia;

/**
 *
 * @author Jesus Omar
 */
public class NegociosBO implements INegociosBO {

    private static NegociosBO instancia;
    private final IFachadaPersistencia persistencia;

    /**
     * Constructor privado para usarlo solo dentro de la clase y para que haya
     * una sola instancia
     */
    private NegociosBO() {
        this.persistencia = FachadaPersistencia.getInstancia();
    }

    /**
     * Metodo para obtener la instancia de NegociosBO, si no existe llama al
     * constructor privado para crear una instancia y la regresa
     *
     * @return
     */
    public static NegociosBO getInstancia() {
        if (instancia == null) {
            instancia = new NegociosBO();
        }
        return instancia;
    }

    /**
     * Metodo para registrar un nuevo negocio
     *
     * @param idEmprendedor el id del emprendedor que esta asociado al negocio
     * @param nuevoNegocio los datos del negocio
     * @return la DTO del nuevo negocio
     * @throws NegocioException
     */
    @Override
    public NegocioDTO registrarNegocio(String idEmprendedor, NegocioDTO nuevoNegocio) throws NegocioException {
        try {
            Negocio negocio = AdapterNegocio.negocioDTOANegocio(nuevoNegocio, idEmprendedor);
            persistencia.registrarNegocio(negocio);
            return AdapterNegocio.negocioANegocioDTO(negocio);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el negocio", ex);
        }
    }

    /**
     * Metodo para obtener un negocio por el id del emprendedor que tenga
     * asociado
     *
     * @param idEmprendedor id del emprendedor qu esta asociado al negocio
     * @return el negocio que se encontro
     * @throws NegocioException
     */
    @Override
    public NegocioDTO obtenerNegocioPorIdEmprendedor(String idEmprendedor) throws NegocioException {
        try {
            Negocio negocio = persistencia.obtenerNegocioPorIdEmprendedor(idEmprendedor);
            NegocioDTO negocioObtenido = AdapterNegocio.negocioANegocioDTO(negocio);
            return negocioObtenido;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener el negocio", ex);
        }
    }

    /**
     * Metodo para obtener la direccion de un negocio por su id
     *
     * @param idNegocio el id del negocio del que se quiere obtener la direccion
     * @return la direccion del negocio
     * @throws NegocioException
     */
    @Override
    public DireccionDTO obtenerDireccionPorIdNegocio(String idNegocio) throws NegocioException {
        try {
            Direccion direccion = persistencia.obtenerDireccionPorIdNegocio(idNegocio);
            DireccionDTO direccionObtenida = AdapterDireccion.direccionADTO(direccion);
            return direccionObtenida;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener la direccion", ex);
        }
    }

    /**
     * Metodo para actualizar la direccion de un negocio
     *
     * @param idNegocio el id del negocio al que se le actualizara la direccion
     * @param direccion los datos de la nueva direccion
     * @return la direccion actualizada
     * @throws NegocioException
     */
    @Override
    public DireccionDTO actualizarDireccion(String idNegocio, DireccionDTO direccion) throws NegocioException {
        try {
            Direccion nuevaDireccion = persistencia.actualizarDireccion(idNegocio, AdapterDireccion.dtoADireccion(direccion));
            DireccionDTO direccionActualizada = AdapterDireccion.direccionADTO(nuevaDireccion);
            return direccionActualizada;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la direccion", ex);
        }
    }

}
