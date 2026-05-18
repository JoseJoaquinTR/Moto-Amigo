/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Entidades;

/**
 *
 * @author Jesus Omar
 */
public class Documento {

    private String idDocumento;
    private byte[] documento;

    public Documento(String idDocumento, byte[] documento) {
        this.idDocumento = idDocumento;
        this.documento = documento;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

}
