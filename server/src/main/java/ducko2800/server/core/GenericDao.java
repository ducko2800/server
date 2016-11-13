package ducko2800.server.core;

import ducko2800.server.daos.DaoRegister;
import ducko2800.server.daos.DaoRegisterFactory;

/**
 * A generic Data Access Object to possibly set defaults. Also used by
 * {@link DaoRegisterFactory} to filter which classes to give to JDBI and by
 * extension to the {@link DaoRegister}
 * 
 * @author dion-loetscher
 *
 */
public interface GenericDao {
	
}
