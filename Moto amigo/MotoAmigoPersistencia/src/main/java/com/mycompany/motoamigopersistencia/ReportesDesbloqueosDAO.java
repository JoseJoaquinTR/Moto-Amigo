/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.bloqueorepartidores.NuevoReporteDesbloqueoDTO;
import Adapter.AdapterMotivo;
import builder.ReporteFiltroBuilder;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import com.mycompany.Entidades.*;
import com.mycompany.motoamigodto.*;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import com.mycompany.bloqueorepartidores.*;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesDesbloqueosDAO implements IReportesDesbloqueosDAO {

    private static final String NOMBRE_COLECCION = "reportesDesbloqueo";

    private static ReportesDesbloqueosDAO instancia;

    private ReportesDesbloqueosDAO() {
    }

    public static synchronized ReportesDesbloqueosDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReportesDesbloqueosDAO();
        }
        return instancia;
    }

    @Override
    public ReporteDesbloqueo guardarReporte(NuevoReporteDesbloqueoDTO dto) throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteDesbloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteDesbloqueo.class);

            Motivo motivo = AdapterMotivo.toEntity(dto.getMotivo());

            ReporteDesbloqueo reporte = new ReporteDesbloqueo();
            reporte.setMotivo(motivo);
            reporte.setDetalles(dto.getDetalles());
            reporte.setFechaHora(dto.getFechaHora());
            reporte.setNumRepartidoresDesbloqueados(dto.getNumRepartidoresDesbloqueados());

            InsertOneResult resultado = coleccion.insertOne(reporte);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo insertar el reporte de desbloqueo.");
            }

            return reporte;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al crear reporte de desbloqueo en MongoDB.", ex);
        }
    }

    @Override
    public List<ReporteDesbloqueo> consultarTodos() throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteDesbloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteDesbloqueo.class);

            List<ReporteDesbloqueo> reportes = new LinkedList<>();
            coleccion.find().into(reportes);
            return reportes;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los reportes de desbloqueo.", ex);
        }
    }

    @Override
    public List<ReporteDesbloqueo> consultarConFiltros(FiltrosDTO filtrosDTO) throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteDesbloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteDesbloqueo.class);

            Document filtro = new ReporteFiltroBuilder()
                    .porFechas(filtrosDTO.getFechaDesde(), filtrosDTO.getFechaHasta())
                    .porMotivo(filtrosDTO.getMotivo())
                    .porNumBloqueos(filtrosDTO.getNumBloqueos())
                    .build();

            List<ReporteDesbloqueo> resultados = new LinkedList<>();
            coleccion.find(filtro).into(resultados);
            return resultados;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar reportes de desbloqueo con filtros.", ex);
        }
    }
}
