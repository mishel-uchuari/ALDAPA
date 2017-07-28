/**
 * 
 */
package es.eurohelp.lod.aldapa;

/**
 * 
 * The catalog URI already exists
 * 
 * @author Mikel Ega�a Aranguren, Eurohelp Consulting S.L.
 *
 */
public class CatalogExistsException extends AldapaException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8838479828879881381L;
	private String message = "The catalog URI already exists in the RDF Store";

	/**
	 * 
	 */
	public CatalogExistsException() {
	}

	/**
	 * @param message
	 */
	public CatalogExistsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CatalogExistsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CatalogExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CatalogExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}