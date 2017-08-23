/**
 * 
 */
package es.eurohelp.lod.aldapa.core;

/**
 * 
 * ALDAPA uses RDF to store information about execution and metadata about projects, datasets and the like.
 * Since the templates storing the "backbone" RDF can be complex, they are codified in RDF in "Method files" that
 * can be executed by ALDAPA by simply parametrising their inner tokens (PROJECT_NAME, ...) so that less code is written
 * (hopefully). Also, that means that new RDF is not necessarily translated into more code.
 * 
 * @author Mikel Egana Aranguren, Eurohelp Consulting S.L.
 *
 */
public enum MethodRDFFile {
	addProject("model/addProject.ttl"), 
	getProjects("model/getProjects.sparql"), 
	projectExists("model/projectExists.sparql"),
	deleteProject("model/deleteProject.sparql"),
	addCatalog("model/addCatalog.ttl"),
	getCatalogs("model/getCatalogs.sparql "),
	catalogExists("model/catalogExists.sparql"),
	deleteCatalog("model/deleteCatalog.sparql"),
	addDataset("model/addDataset.ttl"),
	getDatasets("model/getDatasets.sparql"),
	datasetExists("model/datasetExists.sparql"),
	deleteDataset("model/deleteDataset.sparql"),	
	addNamedGraph("model/addNamedGraph.ttl"),
	getNamedGraphs("model/getNamedGraphs.sparql"),
	namedGraphExists("model/namedGraphExists.sparql"),
	deleteNamedGraph("model/deleteNamedGraph.sparql"),
	;

	public final String methodFileName;

	private MethodRDFFile(String methodFileName) {
		this.methodFileName = methodFileName;
	}

	public String getValue() {
		return methodFileName;
	}
}
