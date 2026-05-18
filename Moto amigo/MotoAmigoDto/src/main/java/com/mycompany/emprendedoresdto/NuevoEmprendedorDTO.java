package com.mycompany.emprendedoresdto;

/**
 *
 * @author Jesus Omar
 */
public class NuevoEmprendedorDTO {

    private String nombre;
    private String correo;
    private String telefono;
    private String rfc;
    private ImagenDTO fotoPerfil;
    private DocumentoDTO documentoINE;
    private CuentaBancariaDTO cuentaBancaria;
    private CuentaUsuarioDTO cuentaUsuario;

    public NuevoEmprendedorDTO() {
    }

    public NuevoEmprendedorDTO(String nombre, String correo, String telefono, String rfc, ImagenDTO fotoPerfil, DocumentoDTO documentoINE, CuentaBancariaDTO cuentaBancaria, CuentaUsuarioDTO cuentaUsuario) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.fotoPerfil = fotoPerfil;
        this.documentoINE = documentoINE;
        this.cuentaBancaria = cuentaBancaria;
        this.cuentaUsuario = cuentaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public ImagenDTO getFotoPerfil() {
        return fotoPerfil;
    }

    public DocumentoDTO getDocumentoINE() {
        return documentoINE;
    }

    public CuentaBancariaDTO getCuentaBancaria() {
        return cuentaBancaria;
    }

    public CuentaUsuarioDTO getCuentaUsuario() {
        return cuentaUsuario;
    }

}
