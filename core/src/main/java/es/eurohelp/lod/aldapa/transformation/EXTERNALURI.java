/**
 * 
 */
package es.eurohelp.lod.aldapa.transformation;


/**
 * @author Mikel Egana Aranguren, Eurohelp Consulting S.L.
 *
 */
enum EXTERNALURI {
	lat_wgs84("http://www.w3.org/2003/01/geo/wgs84_pos#lat"),
	long_wgs84("http://www.w3.org/2003/01/geo/wgs84_pos#long"),
	time_minutes("http://www.w3.org/2006/time#minutes"),
	dbo_province("http://dbpedia.org/ontology/province"),
	schema_location("http://schema.org/location"),
	schema_address("http://schema.org/address"),
	;
	
	public final String external_uri;
	
	private EXTERNALURI(String external_uri) {
		this.external_uri = external_uri;
	}

	public String getValue() {
		return external_uri;
	}

}