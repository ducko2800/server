package ducko2800.server.resources;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import ducko2800.server.core.GenericResource;
import ducko2800.server.daos.DaoRegister;

/**
 * @author dion-loetscher
 *
 */
public class ResourceRegisterFactory {

	private static final String CLASS = ResourceRegisterFactory.class.getName();
	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS);

	public ResourceRegister getResourceRegisterByReflection(DaoRegister daos) {
		ResourceRegister resources = null;
		try {
			ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
			ImmutableSet<ClassInfo> classesInfo = classPath
					.getTopLevelClassesRecursive(this.getClass().getPackage().getName());
			resources = new ResourceRegister();
			for (ClassInfo info : classesInfo) {
				try {
					Class<?> clazz = Class.forName(info.getName());
					if (GenericResource.class.isAssignableFrom(clazz)) {
						Constructor<?> constructor;
						try {
							constructor = clazz.getConstructor(DaoRegister.class);
						} catch (NoSuchMethodException | SecurityException e) {
							constructor = null;
						}
						GenericResource resource = (GenericResource) clazz.newInstance();
						if (constructor != null) {
							resource = (GenericResource) constructor.newInstance(daos);
						}

						resources.addResource(resource);
					}
				} catch (ClassNotFoundException exception) {
					LOGGER.warn("Could not find class between finding it with reflection and getting definition",
							exception);
				} catch (IllegalAccessException | InstantiationException exception) {
					LOGGER.warn("Could not instantiate class found with reflection", exception);
				} catch (IllegalArgumentException | InvocationTargetException exception) {
					LOGGER.warn("Could not use found DaoRegister constructor", exception);
				}

			}
		} catch (IOException exception) {
			LOGGER.error("Could not load resources through reflection", exception);
		}
		return resources;
	}

}
