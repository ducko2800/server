package ducko2800.server;

import org.eclipse.jetty.server.Server;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ducko2800.server.core.ServerConfiguration;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * The core of the server. Contains the main function which is run.
 * 
 * @author dloetscher
 *
 */
public class CoreServer extends Application<ServerConfiguration> {

	private static final String CLASS = CoreServer.class.getName();
	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS);

	/**
	 * Starts the server.
	 * 
	 * @param args
	 *            The arguments that are passed to the server. Refer to {@link #run(String...)}
	 * @throws Exception
	 *             Something went wrong.
	 */
	public static void main(String[] args) throws Exception {
		CoreServer server = new CoreServer();
		server.run(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.dropwizard.Application#initialize(io.dropwizard.setup.Bootstrap)
	 */
	@Override
	public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
		bootstrap.addBundle(new DBIExceptionsBundle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.dropwizard.Application#run(io.dropwizard.Configuration, io.dropwizard.setup.Environment)
	 */
	@Override
	public void run(ServerConfiguration configuration, Environment environment) {
		// Setup JDBI
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "derby");
	}

}
