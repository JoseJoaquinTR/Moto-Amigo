package Adapter;

import com.mycompany.Entidades.CuentaUsuario;
import com.mycompany.Entidades.Emprendedor;
import com.mycompany.emprendedoresdto.CuentaUsuarioDTO;
import com.mycompany.emprendedoresdto.CuentaUsuarioSesionDTO;
import enums.EstatusEmprendedorDTO;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class AdapterCuentaUsuario {

    public AdapterCuentaUsuario() {
    }

    /**
     * Metodo para adaptar una dto de CuentaUsuario a entidad para despues
     * guardarla en la bd
     *
     * @param cuentaUsuarioDTO la dto de la cuenta que se adaptara
     * @param contraHasheada la contrasenia ya hasheada
     * @return la entidad de la CuentaUsuario
     */
    public static CuentaUsuario dtoACuentaUsuario(CuentaUsuarioDTO cuentaUsuarioDTO, String contraHasheada) {
        CuentaUsuario cuentaUsuario = new CuentaUsuario();
        cuentaUsuario.setIdCuenta(new ObjectId().toHexString());
        cuentaUsuario.setCorreo(cuentaUsuarioDTO.getCorreo());
        cuentaUsuario.setContrasenia(contraHasheada);
        cuentaUsuario.setActiva(false);

        return cuentaUsuario;
    }

    /**
     * Metodo para adaptar la cuenta de un Emprendero a la
     * CuentaUsuarioSesionDTO, este metodo regresa un emprendedor por que la DTO
     * lleva el id y estatus del emprendedor, y de ahi se obtiene el correo y se
     * ve si la cuenta esta activa o no
     *
     * @param emprendedor
     * @return
     */
    public static CuentaUsuarioSesionDTO cuentaUsuarioADTO(Emprendedor emprendedor) {
        return new CuentaUsuarioSesionDTO(
                emprendedor.getCuentaUsuario().getIdCuenta(),
                emprendedor.getCuentaUsuario().getCorreo(),
                emprendedor.getCuentaUsuario().isActiva(),
                AdapterEmprendedor.estatusEmprendedorAEstatusEmprendedorDTO(emprendedor.getEstatus()),
                emprendedor.getIdEmprendedor());
    }
}
