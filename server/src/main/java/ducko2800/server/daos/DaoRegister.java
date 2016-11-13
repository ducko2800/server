package ducko2800.server.daos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import ducko2800.server.core.GenericDao;

/**
 * @author dion-loetscher
 *
 */
public class DaoRegister {
	private ConcurrentHashMap<String, GenericDao> daos = new ConcurrentHashMap<>();

	/**
	 * Retrieves a DAO of the specified type given a name.
	 * 
	 * @param name
	 *            The name of the resource often accessible by
	 *            {@link GenericDao#getClass()#getName()}. Should not be null
	 *            or empty.
	 * @param type
	 *            The type that the resource is expected to be. Should not be
	 *            null.
	 * @return An instantiated DAO if one is stored in this register, else
	 *         null.
	 */
	public <T extends GenericDao> T getTypedDao(String name, Class<T> type) {
		GenericDao dao = daos.get(name);
		if (type != null && type.isInstance(dao)) {
			return type.cast(dao);
		}
		return null;
	}

	/**
	 * Adds a DAO to the class using it's
	 * {@link GenericDao#getClasss()#getRootResource()} method.
	 * 
	 * @param dao
	 *            An instantiated DAO. Must not be null.
	 */
	public void addDao(GenericDao dao) {
		daos.put(dao.getClass().getName(), dao);
	}

	/**
	 * Convenience method which allows a consumer to perform an action for each
	 * DAO within. Useful for registering resources with a Jersey instance.
	 * 
	 * @param consumer
	 *            A consumer to handle each resource. Must not be null.
	 */
	public void forEachDao(Consumer<? super GenericDao> consumer) {
		daos.forEachValue(Long.MAX_VALUE, consumer);
	}
}
