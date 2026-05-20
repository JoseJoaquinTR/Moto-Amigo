package Adapter;

import Enums.Banco;
import com.mycompany.Entidades.CuentaBancaria;
import com.mycompany.emprendedoresdto.CuentaBancariaDTO;
import enums.BancoDTO;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Omar
 */
public class AdapterCuentaBancaria {

    public AdapterCuentaBancaria() {
    }

    /**
     * Convierte una DTO de CuentaBancaria a entidad
     *
     * @param cuentaBancariaDTO la DTO que se convertira
     * @return la entidad de la CuentaBancaria
     */
    public static CuentaBancaria dtoACuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setIdCuentaBancaria(new ObjectId().toHexString());
        cuentaBancaria.setNumeroCuenta(cuentaBancariaDTO.getNumeroCuenta());
        cuentaBancaria.setNombreTitular(cuentaBancariaDTO.getNombreTitular());
        cuentaBancaria.setInstitucionBancaria(Banco.SANTANDER);

        return cuentaBancaria;
    }

    /**
     * Convierte una entidad de CuentaBancaria a una DTO
     *
     * @param cuentaBancaria la entidad que se convertira a DTO
     * @return la DTO de la CuentaBancaria que se envio
     */
    public static CuentaBancariaDTO cuentaBancariaADTO(CuentaBancaria cuentaBancaria) {
        return new CuentaBancariaDTO(
                cuentaBancaria.getNumeroCuenta(),
                cuentaBancaria.getNombreTitular(),
                BancoDTO.SANTANDER);
    }

    /**
     * Convierte una DTO de la institucionBancaria a una de persistencia
     *
     * @param bancoDTO la DTO que se convertira
     * @return la institucionBancaria de persistencia
     */
    public static Banco dtoABanco(BancoDTO bancoDTO) {
        if (bancoDTO.toString().equals("BANAMEX")) {
            return Banco.BANAMEX;
        } else if (bancoDTO.toString().equals("BBVA")) {
            return Banco.BBVA;
        } else if (bancoDTO.toString().equals("HSBC")) {
            return Banco.HSBC;
        } else {
            return Banco.SANTANDER;
        }
    }

    /**
     * Convierte una institucionBancaria de persistencia a una de DTO
     *
     * @param banco el banco que se convertira
     * @return la DTO del banco
     */
    public static BancoDTO bancoADTO(Banco banco) {
        if (banco.toString().equals("BANAMEX")) {
            return BancoDTO.BANAMEX;
        } else if (banco.toString().equals("BBVA")) {
            return BancoDTO.BBVA;
        } else if (banco.toString().equals("HSBC")) {
            return BancoDTO.HSBC;
        } else {
            return BancoDTO.SANTANDER;
        }
    }
}
