package ducko2800.server.resources;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import ducko2800.server.core.GenericResource;

/**
 * @author dion-loetscher
 *
 */
public class ResourceRegister {
	
	private ConcurrentHashMap<String, GenericResource> resources = new ConcurrentHashMap<>();
	
	public <T> T getTypedResource(String name, Class<T> type) {
		GenericResource resource = resources.get(name);
		if (type != null && type.isInstance(resource)) {
			return type.cast(resource);
		}
		return null;
	}
	
	public void addResource(GenericResource resource) {
		resources.put(resource.getRootResource(), resource);
	}

	public void forEachResource(Consumer<? super GenericResource> consumer) {
		resources.forEachValue(Long.MAX_VALUE, consumer);
	}
}
