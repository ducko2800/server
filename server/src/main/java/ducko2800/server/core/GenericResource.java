package ducko2800.server.core;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ducko2800.server.daos.DaoRegister;

/**
 * A generic resource class for all other resources to extend. Used to ease
 * resource management and registration in the main Jersey server. Do not make
 * resources inter-dependent as the order of construction is not guaranteed. If
 * data is needed, either, 1. construct a client to target the other resources,
 * or 2. use the underlying DAO of each resource directly. If a DAO is needed,
 * create a constructor which uses the {@link DaoRegister} as the only parameter
 * and use the {@link DaoRegister} to retrieve Daos. Otherwise do not bother
 * including any other constructors as they will not be used.
 * 
 * @author dion-loetscher
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class GenericResource {

	/**
	 * Defines the base of this resource's URL. <br>
	 * e.g. If this resource server results to http://foo.bar/example/resource
	 * then this should return "example/resource". <br>
	 * <br>
	 * In order to ensure good naming convention, the resource should begin with
	 * a lowercase and be camel-cased if it uses multiple words. <br>
	 * e.g. "messagingChannels"
	 * 
	 * @return A non-null String of length at least 1. Text is camel-cased,
	 *         alphanumeric except for forward slashes used to define the root
	 *         of resources within resource. Will not start with a forward slash
	 *         ("/").
	 */
	public abstract String getRootResource();

}
