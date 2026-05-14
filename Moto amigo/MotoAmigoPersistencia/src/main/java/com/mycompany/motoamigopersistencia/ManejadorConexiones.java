/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

/**
 *
 * @author joset
 */
public class ManejadorConexiones {

    private static final String CADENA_CONEXION = "mongodb://localhost:27017";
    public static final String BASE_DATOS = "MotoAmigoDB";

    private static ManejadorConexiones instancia;

    private MongoClient cliente;

    private ManejadorConexiones() {
    }

    public static synchronized ManejadorConexiones getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorConexiones();
        }
        return instancia;
    }

    private CodecRegistry construirRegistroCodecs() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        return fromRegistries(getDefaultCodecRegistry(), pojoCodecRegistry);
    }

    public MongoDatabase obtenerBaseDatos() {
        if (cliente == null) {
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(CADENA_CONEXION))
                    .codecRegistry(construirRegistroCodecs())
                    .build();
            cliente = MongoClients.create(settings);
        }
        return cliente.getDatabase(BASE_DATOS);
    }

    public void cerrarConexion() {
        if (cliente != null) {
            cliente.close();
            cliente = null;
        }
    }

}
