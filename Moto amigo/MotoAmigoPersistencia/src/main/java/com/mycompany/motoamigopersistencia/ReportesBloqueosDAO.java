/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.motoamigopersistencia;

import Adapter.AdapterMotivo;
import Adapter.AdapterRepartidor;
import builder.ReporteFiltroBuilder;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mycompany.Entidades.Motivo;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.motoamigodto.FiltrosDTO;
import com.mycompany.motoamigodto.NuevoReporteBloqueoDTO;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesBloqueosDAO implements IReportesBloqueosDAO {

    private static final String NOMBRE_COLECCION = "reportesBloqueo";

    @Override
    public ReporteBloqueo guardarReporte(NuevoReporteBloqueoDTO dto) throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteBloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteBloqueo.class);

            // Adaptar DTOs a entidades
            Motivo motivo = AdapterMotivo.toEntity(dto.getMotivo());
            Repartidor repartidor = AdapterRepartidor.toEntity(dto.getRepartidor());

            ReporteBloqueo reporte = new ReporteBloqueo();
            reporte.setMotivo(motivo);
            reporte.setRepartidor(repartidor);
            reporte.setDetalles(dto.getDetalles());
            reporte.setFechaHora(dto.getFechaHora());
            reporte.setImagenEvidencia(dto.getImagenEvidencia());

            InsertOneResult resultado = coleccion.insertOne(reporte);
            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo insertar el reporte de bloqueo.");
            }

            return reporte;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al crear reporte de bloqueo en MongoDB.", ex);
        }
    }

    @Override
    public List<ReporteBloqueo> consultarTodos() throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteBloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteBloqueo.class);

            List<ReporteBloqueo> reportes = new LinkedList<>();
            coleccion.find().into(reportes);
            return reportes;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los reportes de bloqueo.", ex);
        }
    }

    @Override
    public List<ReporteBloqueo> consultarConFiltros(FiltrosDTO filtrosDTO) throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteBloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteBloqueo.class);

            Document filtro = new ReporteFiltroBuilder()
                    .porFechas(filtrosDTO.getFechaDesde(), filtrosDTO.getFechaHasta())
                    .porMotivo(filtrosDTO.getMotivo())
                    .porNumBloqueos(filtrosDTO.getNumBloqueos())
                    .build();

            List<ReporteBloqueo> resultados = new LinkedList<>();
            coleccion.find(filtro).into(resultados);
            return resultados;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar reportes de bloqueo con filtros.", ex);
        }
    }
}

