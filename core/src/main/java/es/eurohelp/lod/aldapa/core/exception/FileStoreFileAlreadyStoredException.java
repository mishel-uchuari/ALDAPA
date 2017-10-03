/**
 * 
 */
package es.eurohelp.lod.aldapa.core.exception;

/**
 * 
 * The project URI already exists
 * 
 * @author Mikel Egana Aranguren, Eurohelp Consulting S.L.
 *
 */
public class FileStoreFileAlreadyStoredException extends AldapaException {

	private static final long serialVersionUID = 2989681335539498574L;
	private final static String message = "The file has already been saved";

	public FileStoreFileAlreadyStoredException() {
		super(message);
	}

	public FileStoreFileAlreadyStoredException(Throwable cause) {
		super(cause);
	}

	public FileStoreFileAlreadyStoredException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStoreFileAlreadyStoredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
