
package Producto;

/**
 *
 * @author joset
 */
public class ProductoException extends Exception{

    public ProductoException() {
    }

    public ProductoException(String message) {
        super(message);
    }

    public ProductoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductoException(Throwable cause) {
        super(cause);
    }

    public ProductoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
