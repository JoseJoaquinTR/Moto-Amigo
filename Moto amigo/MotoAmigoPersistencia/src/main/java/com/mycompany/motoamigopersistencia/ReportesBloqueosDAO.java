/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import Adapter.AdapterMotivo;
import Adapter.AdapterRepartidor;
import builder.ReporteFiltroBuilder;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mycompany.Entidades.Estado;
import com.mycompany.Entidades.Motivo;
import com.mycompany.Entidades.Repartidor;
import com.mycompany.Entidades.ReporteBloqueo;
import com.mycompany.bloqueorepartidores.FiltrosDTO;
import com.mycompany.bloqueorepartidores.NuevoReporteBloqueoDTO;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class ReportesBloqueosDAO implements IReportesBloqueosDAO {

    private static final String NOMBRE_COLECCION = "reportesBloqueo";

    private static ReportesBloqueosDAO instancia;

    private ReportesBloqueosDAO() {
    }

    public static synchronized ReportesBloqueosDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReportesBloqueosDAO();
        }
        return instancia;
    }

    @Override
    public ReporteBloqueo guardarReporte(NuevoReporteBloqueoDTO dto) throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteBloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteBloqueo.class);

            Motivo motivo = AdapterMotivo.toEntity(dto.getMotivo());

            ReporteBloqueo reporte = new ReporteBloqueo();
            reporte.setMotivo(motivo);
            reporte.setIdRepartidor(dto.getRepartidor().getId());
            reporte.setDetalles(dto.getDetalles());
            reporte.setFechaHora(dto.getFechaHora());
            reporte.setImagenEvidencia(dto.getImagenEvidencia());

            InsertOneResult resultado = coleccion.insertOne(reporte);

            if (!resultado.wasAcknowledged()) {
                throw new PersistenciaException("No se pudo insertar el reporte de bloqueo.");
            }

            if (resultado.getInsertedId() != null && resultado.getInsertedId().isObjectId()) {
                reporte.setIdReporte(resultado.getInsertedId().asObjectId().getValue().toHexString());
            }

            return reporte;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al crear reporte de bloqueo", ex);
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

    @Override
    public List<Repartidor> obtenerRepartidoresParaDesbloqueoMasivo(FiltrosDTO filtros) throws PersistenciaException {
        try {
            MongoDatabase bd = ManejadorConexiones.getInstancia().obtenerBaseDatos();
            MongoCollection<ReporteBloqueo> coleccion = bd.getCollection(NOMBRE_COLECCION, ReporteBloqueo.class);

            List<Bson> pipeline = new LinkedList<>();
            List<Bson> filtrosReporteBloqueo = new LinkedList<>();

            if (filtros != null) {
                if (filtros.getFechaDesde() != null) {
                    filtrosReporteBloqueo.add(Filters.gte("fechaHora", filtros.getFechaDesde()));
                }

                if (filtros.getFechaHasta() != null) {
                    filtrosReporteBloqueo.add(Filters.lte("fechaHora", filtros.getFechaHasta()));
                }

                if (filtros.getMotivo() != null && filtros.getMotivo().getMotivo() != null && !filtros.getMotivo().getMotivo().isBlank()) {
                    filtrosReporteBloqueo.add(Filters.eq("motivo.motivo", filtros.getMotivo().getMotivo()));
                }
            }

            if (!filtrosReporteBloqueo.isEmpty()) {
                pipeline.add(Aggregates.match(Filters.and(filtrosReporteBloqueo)));
            }

            pipeline.add(Aggregates.group("$idRepartidor"));

            pipeline.add(Aggregates.lookup("repartidores", "_id", "_id", "repartidor"));

            pipeline.add(Aggregates.unwind("$repartidor"));

            List<Bson> filtrosRepartidor = new LinkedList<>();
            filtrosRepartidor.add(Filters.eq("repartidor.estado", Estado.BLOQUEADO));

            if (filtros != null && filtros.getNumBloqueos() != null && filtros.getNumBloqueos() > 0) {
                filtrosRepartidor.add(Filters.gte("repartidor.numBloqueos", filtros.getNumBloqueos()));
            }

            pipeline.add(Aggregates.match(Filters.and(filtrosRepartidor)));

            pipeline.add(Aggregates.replaceRoot("$repartidor"));

            List<Repartidor> repartidores = new LinkedList<>();
            AggregateIterable<Repartidor> resultado = coleccion.aggregate(pipeline, Repartidor.class);

            for (Repartidor repartidor : resultado) {
                repartidores.add(repartidor);
            }

            return repartidores;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al obtener repartidores para desbloqueo masivo.", ex);
        }
    }
}
