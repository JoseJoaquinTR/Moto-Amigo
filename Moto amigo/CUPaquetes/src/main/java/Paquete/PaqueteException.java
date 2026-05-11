
package Paquete;

/**
 *
 * @author joset
 */
public class PaqueteException extends Exception{

    public PaqueteException() {
    }

    public PaqueteException(String message) {
        super(message);
    }

    public PaqueteException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaqueteException(Throwable cause) {
        super(cause);
    }

    public PaqueteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
