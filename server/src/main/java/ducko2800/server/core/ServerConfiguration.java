package ducko2800.server.core;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * An extension of the {@link Configuration} class provided by Dropwizard. It
 * adds extra configuration options needed for the server.
 * 
 * @author dion-loetscher
 *
 */
public class ServerConfiguration extends Configuration {

	/**
	 * Used to store the configuration for the database and creating new
	 * datasources.
	 */
	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();

	/**
	 * Sets the data source factory which is used to store the database
	 * configuration
	 * 
	 * @param factory
	 *            the DataSourceFactory to be stored.
	 */
	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory factory) {
		this.database = factory;
	}

	/**
	 * Retrieves the currently stored data source factory.
	 * 
	 * @return The data source factory stored in the configuration. Should not
	 *         be null.
	 */
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

}
