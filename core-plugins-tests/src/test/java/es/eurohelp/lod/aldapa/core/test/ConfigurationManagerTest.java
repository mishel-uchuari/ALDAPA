/**
 * 
 */
package es.eurohelp.lod.aldapa.core.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import org.junit.Test;

import es.eurohelp.lod.aldapa.core.ConfigurationManager;
import es.eurohelp.lod.aldapa.core.exception.AldapaException;
import es.eurohelp.lod.aldapa.modification.FunctionalRDFQualityValidator;
import es.eurohelp.lod.aldapa.storage.FunctionalFileStore;
import es.eurohelp.lod.aldapa.storage.FunctionalRDFStore;
import es.eurohelp.lod.aldapa.transformation.FunctionalCSV2RDFBatchConverter;

/**
 * @author Mikel Egana Aranguren, Eurohelp Consulting S.L.
 *
 */
public class ConfigurationManagerTest {
	
	private static final String ConfigFile = "configuration.yml";
	private static final String configfile2 = "configuration2.yml";
	private static final String ejiefile = "estaciones.csv";
	private static final String ejiefileurl = "https://raw.githubusercontent.com/opendata-euskadi/LOD-datasets/master/calidad-aire-en-euskadi-2017/estaciones.csv";
	private static final String csvfile = "data/OpenDataEuskadiCalidadDelAire/estaciones.csv";
	
	@Test
	public void testFileStore() throws Exception{
		ConfigurationManager testManager = ConfigurationManager.getInstance(ConfigFile);
		FunctionalFileStore fileStore = testManager.getFileStore();
		fileStore.getFileHTTP(ejiefileurl, ejiefile, true);
		assertEquals("data/",fileStore.getDirectoryPath());
		assertTrue(Files.exists(Paths.get(fileStore.getDirectoryPath() + ejiefile)));
	}
	
	@Test
	public void testMemoryRDFStore () throws Exception {
		ConfigurationManager testManager = ConfigurationManager.getInstance(ConfigFile);
		FunctionalRDFStore rdfStore = testManager.getRDFStore();
		ModelBuilder builder = new ModelBuilder();
		builder.setNamespace("ex", "http://example.org/").subject("ex:Picasso").add(RDF.TYPE, "ex:Artist").add(FOAF.FIRST_NAME, "Pablo");
		Model model = builder.build();
		rdfStore.saveModel(model);
	}
	
	@Test
	public void testRESTRDFStore () throws Exception {
		ConfigurationManager testManager = ConfigurationManager.getInstance(configfile2);
		FunctionalRDFStore rdfStore = testManager.getRDFStore();
		ModelBuilder builder = new ModelBuilder();
		builder.setNamespace("ex", "http://example.com/").subject("ex:Mikel").add(RDF.TYPE, "ex:Developer").add(FOAF.FIRST_NAME, "Mikel");
		Model model = builder.build();
		rdfStore.saveModel(model);
//		FunctionalDBRDFStore dbRDFStore = (FunctionalDBRDFStore)rdfStore; 
//		dbRDFStore.deleteDB(testManager.getConfigPropertyValue(tripleStoreConfigFile, dbNameToken));
	}
	
	@Test
	public void testTransformer () throws Exception {
		ConfigurationManager testManager = ConfigurationManager.getInstance(ConfigFile);
		FunctionalCSV2RDFBatchConverter converter = testManager.getTransformer();
		Model model = new TreeModel();
		converter.setDataSource(csvfile);
		converter.setModel(model);
		Model new_model = converter.getTransformedModel("http://euskadi.eus/graph/calidad-aire");
		assertNotNull(new_model);
	}
	
	@Test
	public void testValidator () throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, AldapaException{
		ConfigurationManager testManager = ConfigurationManager.getInstance(ConfigFile);
		FunctionalRDFQualityValidator validator = testManager.getRDFQualityValidator();
	}
}
