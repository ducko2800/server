package ducko2800.server.daos;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import ducko2800.server.core.GenericDao;

public class DaoRegisterFactory {
	private static final String CLASS = DaoRegisterFactory.class.getName();
	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS);

	public DaoRegister getDaoRegisterByReflection(DBI jdbi) {
		DaoRegister daos = null;
		try {
			ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
			ImmutableSet<ClassInfo> classesInfo = classPath
					.getTopLevelClassesRecursive(this.getClass().getPackage().getName());
			daos = new DaoRegister();
			for (ClassInfo info : classesInfo) {
				try {
					Class<?> clazz = Class.forName(info.getName());
					if (GenericDao.class.isAssignableFrom(clazz)) {
						daos.addDao((GenericDao) jdbi.onDemand(clazz));
					}
				} catch (ClassNotFoundException exception) {
					LOGGER.warn("Could not find class between finding it with reflection and getting definition",
							exception);
				}
			}
		} catch (IOException exception) {
			LOGGER.error("Could not load resources through reflection", exception);
		}
		return daos;
	}
}
