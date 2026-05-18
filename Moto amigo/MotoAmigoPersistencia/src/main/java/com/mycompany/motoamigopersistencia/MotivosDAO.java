/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motoamigopersistencia;

import com.mycompany.Entidades.Motivo;
import enums.Tipo;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class MotivosDAO implements IMotivosDAO {

    private static MotivosDAO instancia;

    private MotivosDAO() {
    }

    public static synchronized MotivosDAO getInstancia() {
        if (instancia == null) {
            instancia = new MotivosDAO();
        }
        return instancia;
    }

    @Override
    public List<Motivo> obtenerMotivos(Tipo tipo) {

        List<Motivo> motivos = new LinkedList<>();

        if (tipo == Tipo.BLOQUEO) {
            motivos.add(crearMotivo("Fraude en pedidos", Tipo.BLOQUEO));
            motivos.add(crearMotivo("Conducta inapropiada con clientes", Tipo.BLOQUEO));
            motivos.add(crearMotivo("Cancelaciones excesivas", Tipo.BLOQUEO));
            motivos.add(crearMotivo("Manipulación de ubicación GPS", Tipo.BLOQUEO));
            motivos.add(crearMotivo("Uso de vehículo no registrado", Tipo.BLOQUEO));
            motivos.add(crearMotivo("Acumulación de reportes negativos", Tipo.BLOQUEO));
        }

        if (tipo == Tipo.DESBLOQUEO) {
            motivos.add(crearMotivo("Aclaración aprobada", Tipo.DESBLOQUEO));
            motivos.add(crearMotivo("Error en el bloqueo", Tipo.DESBLOQUEO));
            motivos.add(crearMotivo("Cumplimiento de sanción temporal", Tipo.DESBLOQUEO));
            motivos.add(crearMotivo("Actualización de documentación", Tipo.DESBLOQUEO));
            motivos.add(crearMotivo("Revisión manual autorizada", Tipo.DESBLOQUEO));
        }

        return motivos;
    }

    private Motivo crearMotivo(String descripcion, Tipo tipo) {

        Motivo motivo = new Motivo();

        motivo.setMotivo(descripcion);
        
        if(tipo == Tipo.BLOQUEO){
            motivo.setTipo(com.mycompany.Entidades.Tipo.BLOQUEO);
        }else if (tipo == tipo.DESBLOQUEO ){
            motivo.setTipo(com.mycompany.Entidades.Tipo.DESBLOQUEO);
        }
        

        return motivo;
    }
}
