package es.eurohelp.lod.aldapa.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.eurohelp.lod.aldapa.core.exception.ConfigurationException;
import es.eurohelp.lod.aldapa.core.exception.ConfigurationFileIOException;
import es.eurohelp.lod.aldapa.util.FileUtils;
import es.eurohelp.lod.aldapa.util.YAMLUtils;

/**
 * 
 * A configuration manager holds the configuration properties. The main file should contain pointers to other 
 * files, each file having the configuration of each module. See configuration.yml and the folder configuration for details
 * 
 * @author Mikel Egana Aranguren, Eurohelp consulting S.L.
 * 
 * @author Arkaitz Carbajo, Eurohelp consulting S.L.
 *
 */
public class ConfigurationManager {

	private static final Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);
	
	/**
	 * The configuration is stored in a HashTable:
	 * 
	 * file name - file content
	 * 
	 * The file file content is a HashMap, containing the actual configuration values:
	 * 
	 * config property - config value
	 * 
	 * See 'configuration.yml' for examples
	 * 
	 */
	
	private Hashtable<String,HashMap<String,String>> main_config_file;

	/**
	 * INSTANCE of ConfigurationManager (type: {@link ConfigurationManager})
	 */
	private static ConfigurationManager INSTANCE = null;

	/**
	 * 
	 * Private constructor for ConfigurationManager.
	 * 
	 * @param the main config file name
	 * @throws IOException 
	 * 
	 * @throws ConfigurationFileIOException
	 *             if it is unable to load app and ALDAPA config properties
	 * 
	 */
	private ConfigurationManager(String config_file_name) throws ConfigurationFileIOException, IOException {
		this.loadProperties(config_file_name);
	}

	/**
	 * 
	 * Retrieves the only instance of this Singleton class.
	 * 
	 * @param configuration_file_name the main config file name
	 * @return the only instance of ConfigurationManager.
	 * @author acarbajo
	 * @throws ConfigurationFileIOException an I/O exception pertaining to the confgi file
	 * @throws IOException a general I/O exception
	 */
	public synchronized static ConfigurationManager getInstance(String configuration_file_name) throws ConfigurationFileIOException, IOException {
		if (null == INSTANCE) {
			INSTANCE = new ConfigurationManager(configuration_file_name);
		}
		return INSTANCE;
	}

	/**
	 * Loads config properties from the specified file.
	 * If not specified, it will load configuration.yml
	 *
	 * @param the main config file name
	 * @author acarbajo
	 * @throws ConfigurationFileIOException
	 *             expection will occur when any of the configuration properties' file is not successfully loaded.
	 */
	private void loadProperties(String configuration_file_name) throws ConfigurationFileIOException, IOException {
		InputStream configInStream = FileUtils.getInstance().getInputStream(configuration_file_name);

		try {
			
			main_config_file = new Hashtable<String, HashMap<String,String>>();
			YAMLUtils yaml_utils = new YAMLUtils();
			HashMap<String,String> provisional_main_config_file = yaml_utils.parseSimpleYAML(configInStream);
			
			for (Map.Entry<String, String> entry : provisional_main_config_file.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
				
				LOGGER.info("Key = " + key + ", Value = " + value);
				InputStream config2InStream = FileUtils.getInstance().getInputStream(value);
			    main_config_file.put(key, yaml_utils.parseSimpleYAML(config2InStream));
			}
		} finally {
			try {
				if (configInStream != null) {
					configInStream.close();
				}
			} catch (IOException ignore) {
				// close quietly
			}
		}
	}

	/**
	 * 
	 * Retrieves a configuration value of a given property (eg "pluginClassName") in a file (eg "TRIPLE_STORE_CONFIG_FILE") 
	 * 
	 * @param module (file) name
	 * 
	 * @param property
	 *            the configuration property name.
	 * 
	 * @return the configuration value for that property key. <em> null</em> if the property is not found.
	 * @throws ConfigurationException a configuration exception
	 */
	public String getConfigPropertyValue(String module,String property) throws ConfigurationException {
		String prop_value = (main_config_file.get(module)).get(property);
		if(prop_value == null){
			throw new ConfigurationException("Property or value not found");
		}
		else{
			return prop_value;
		}
	}
}