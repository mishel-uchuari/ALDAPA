/**
 * 
 */
package es.eurohelp.lod.aldapa.core.exception;

/**
 * @author Mikel Egana Aranguren, Eurohelp Consulting S.L.
 *
 */
public class NamedGraphExistsException extends AldapaException {

	private static final long serialVersionUID = 5643998639202681881L;
    private static String message = "The Named Graph URI already exists in the RDF Store";
	
	public NamedGraphExistsException() {
	}

	/**
	 * @param graph_uri the Named Graph URI
	 */
	public NamedGraphExistsException(String graph_uri) {
		super(message +": " + graph_uri);
	}

	public NamedGraphExistsException(Throwable cause) {
		super(cause);
	}

	public NamedGraphExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NamedGraphExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
