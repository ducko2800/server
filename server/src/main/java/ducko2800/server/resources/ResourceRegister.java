package ducko2800.server.resources;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import ducko2800.server.core.GenericResource;

/**
 * A simple caching mechanism for all the resources. Mainly to be used by Jersey
 * to easily find and apply actions to each resource.
 * 
 * @author dion-loetscher
 *
 */
public class ResourceRegister {

	private ConcurrentHashMap<String, GenericResource> resources = new ConcurrentHashMap<>();

	/**
	 * Retrieves a resource of the specified type given a name.
	 * 
	 * @param name
	 *            The name of the resource often accessible by
	 *            {@link GenericResource#getRootResource()}. Should not be null
	 *            or empty.
	 * @param type
	 *            The type that the resource is expected to be. Should not be
	 *            null.
	 * @return An instantiated resource if one is stored in this register, else
	 *         null.
	 */
	public <T extends GenericResource> T getTypedResource(String name, Class<T> type) {
		GenericResource resource = resources.get(name);
		if (type != null && type.isInstance(resource)) {
			return type.cast(resource);
		}
		return null;
	}

	/**
	 * Adds a resource to the class using it's
	 * {@link GenericResource#getRootResource()} method.
	 * 
	 * @param resource
	 *            An instantiated resource. Must not be null.
	 */
	public void addResource(GenericResource resource) {
		resources.put(resource.getRootResource(), resource);
	}

	/**
	 * Convenience method which allows a consumer to perform an action for each
	 * resource within. Useful for registering resources with a Jersey instance.
	 * 
	 * @param consumer
	 *            A consumer to handle each resource. Must not be null.
	 */
	public void forEachResource(Consumer<? super GenericResource> consumer) {
		resources.forEachValue(Long.MAX_VALUE, consumer);
	}
}
