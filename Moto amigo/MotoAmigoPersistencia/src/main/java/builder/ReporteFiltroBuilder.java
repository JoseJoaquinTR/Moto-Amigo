/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import com.mycompany.bloqueorepartidores.MotivoDTO;
import org.bson.Document;
import java.time.LocalDateTime;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */

public class ReporteFiltroBuilder {

    private final Document filtro = new Document();

    public ReporteFiltroBuilder porFechas(LocalDateTime desde, LocalDateTime hasta) {
        Document rango = new Document();
        if (desde != null) {
            rango.append("$gte", desde);
        }
        if (hasta != null) {
            rango.append("$lte", hasta);
        }
        if (!rango.isEmpty()) {
            filtro.append("fechaHora", rango);
        }
        return this;
    }

    public ReporteFiltroBuilder porMotivo(MotivoDTO motivo) {
        if (motivo != null) {
            if (motivo.getMotivo() != null) {
                filtro.append("motivo.motivo", motivo.getMotivo());
            }
            if (motivo.getTipo() != null) {
                filtro.append("motivo.tipo", motivo.getTipo().toString());
            }
        }
        return this;
    }

    public ReporteFiltroBuilder porNumBloqueos(Integer numBloqueos) {
        if (numBloqueos != null && numBloqueos > 0) {
            filtro.append("numBloqueos", numBloqueos);
        }
        return this;
    }

    public Document build() {
        return filtro;
    }
}
