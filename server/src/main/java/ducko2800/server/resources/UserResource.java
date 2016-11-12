package ducko2800.server.resources;

import javax.ws.rs.Path;

import ducko2800.server.core.GenericResource;

/**
 * @author dion-loetscher
 *
 */
@Path(UserResource.ROOT)
public class UserResource extends GenericResource {

	public static final String ROOT = "user";

	@Override
	public String getRootResource() {
		return ROOT;
	}

}
