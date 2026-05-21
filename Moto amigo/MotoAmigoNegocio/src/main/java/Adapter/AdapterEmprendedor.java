package Adapter;

import Enums.EstatusEmprendedor;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdto.EmprendedorDTO;
import com.mycompany.emprendedoresdto.NuevoEmprendedorDTO;
import enums.EstatusEmprendedorDTO;

/**
 *
 * @author Jesus Omar
 */
public class AdapterEmprendedor {

    public AdapterEmprendedor() {
    }

    /**
     * Convierte un NuevoEmprendedorDTO a la entidad de Emprendedor
     *
     * @param nuevoEmprendedor la DTO que se convertira
     * @param contraHasheada la contrasenia ya hasheada para guardarla asi
     * @return una entidad de Emprendedor que se guardadra en la bd
     */
    public static Emprendedor emprendedorDTOAEmprendedor(
            NuevoEmprendedorDTO nuevoEmprendedor,
            String contraHasheada) {
        if(nuevoEmprendedor == null){
            return null;
        }
        Emprendedor emprendedor = new Emprendedor();
        emprendedor.setNombre(nuevoEmprendedor.getNombre());
        emprendedor.setCorreo(nuevoEmprendedor.getCorreo());
        emprendedor.setTelefono(nuevoEmprendedor.getTelefono());
        emprendedor.setRfc(nuevoEmprendedor.getRfc());
        emprendedor.setEstatus(EstatusEmprendedor.PENDIENTE);
        emprendedor.setImagen(AdapterImagen.dtoAImagen(nuevoEmprendedor.getFotoPerfil()));
        emprendedor.setDocumento(AdapterDocumento.dtoADocumento(nuevoEmprendedor.getDocumentoINE()));
        emprendedor.setCuentaBancaria(
                AdapterCuentaBancaria.dtoACuentaBancaria(nuevoEmprendedor.getCuentaBancaria())
        );
        emprendedor.setCuentaUsuario(
                AdapterCuentaUsuario.dtoACuentaUsuario(nuevoEmprendedor.getCuentaUsuario(), contraHasheada)
        );
        return emprendedor;
    }

    /**
     * Convierte un Emprendedor a un EmprendedorDTO, esta DTO es para consultar
     * la informacion del emprendedor, para mostrar los datos en pantallas o
     * para la parte del administrador
     *
     * @param emprendedor el emprendedor del que se obtendran los datos
     * @return un EmprendedorDTO
     */
    public static EmprendedorDTO emprendedorAEmprendedorDTO(Emprendedor emprendedor) {
        if(emprendedor == null){
            return null;
        }
        return new EmprendedorDTO(
                emprendedor.getIdEmprendedor(),
                emprendedor.getNombre(),
                emprendedor.getCorreo(),
                emprendedor.getTelefono(),
                emprendedor.getRfc(),
                AdapterEmprendedor.estatusEmprendedorAEstatusEmprendedorDTO(emprendedor.getEstatus())
        );
    }

    /**
     * Este metodo convierte un EstatusEmprendedorDTO a un EstatusEmprendedor
     * que es el enum que esta en persistencia
     *
     * @param estatus la DTO del enum del estatus
     * @return el estatus de persistencia
     */
    public static EstatusEmprendedor estatusDTOAEstatus(EstatusEmprendedorDTO estatus) {
        if(estatus == null){
            return null;
        }
        if (estatus.toString().equals("PENDIENTE")) {
            return EstatusEmprendedor.PENDIENTE;
        } else if (estatus.toString().equals("ACTIVO")) {
            return EstatusEmprendedor.ACTIVO;
        } else if (estatus.toString().equals("BAJA")) {
            return EstatusEmprendedor.BAJA;
        } else {
            return EstatusEmprendedor.RECHAZADO;
        }
    }

    /**
     * Este metodo convierte un EstatusEmprendedor a un EstatusEmprendedorDTO
     * que es el enum que esta en la capa de negocio para arriba
     *
     * @param estatus el estatus de persistencia que se converitra a DTO
     * @return el estatusDTO
     */
    public static EstatusEmprendedorDTO estatusEmprendedorAEstatusEmprendedorDTO(EstatusEmprendedor estatus) {
        if(estatus == null){
            return null;
        }
        if (estatus.toString().equals("PENDIENTE")) {
            return EstatusEmprendedorDTO.PENDIENTE;
        } else if (estatus.toString().equals("ACTIVO")) {
            return EstatusEmprendedorDTO.ACTIVO;
        } else if (estatus.toString().equals("BAJA")) {
            return EstatusEmprendedorDTO.BAJA;
        } else {
            return EstatusEmprendedorDTO.RECHAZADO;
        }
    }
}
