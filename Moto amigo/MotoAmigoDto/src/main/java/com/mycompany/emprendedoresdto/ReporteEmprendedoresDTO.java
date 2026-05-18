package com.mycompany.emprendedoresdto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Jesus Omar
 */
public class ReporteEmprendedoresDTO {
    
    private String titulo;
    private Date fechaReporte;
    private List<EmprendedorDTO> listaEmprendedores;
    private int totalEmprendedores;
    private int totalActivos;
    private int totalPendientes;
    private int totalRechazados;
    private int totalBaja;

    public ReporteEmprendedoresDTO() {
    }

    public ReporteEmprendedoresDTO(String titulo, 
            Date fechaReporte, 
            List<EmprendedorDTO> listaEmprendedores, 
            int totalEmprendedores, 
            int totalActivos, 
            int totalPendientes, 
            int totalRechazados, 
            int totalBaja) {
        this.titulo = titulo;
        this.fechaReporte = fechaReporte;
        this.listaEmprendedores = listaEmprendedores;
        this.totalEmprendedores = totalEmprendedores;
        this.totalActivos = totalActivos;
        this.totalPendientes = totalPendientes;
        this.totalRechazados = totalRechazados;
        this.totalBaja = totalBaja;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public List<EmprendedorDTO> getListaEmprendedores() {
        return listaEmprendedores;
    }

    public int getTotalEmprendedores() {
        return totalEmprendedores;
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public int getTotalPendientes() {
        return totalPendientes;
    }

    public int getTotalRechazados() {
        return totalRechazados;
    }

    public int getTotalBaja() {
        return totalBaja;
    }
    
}
