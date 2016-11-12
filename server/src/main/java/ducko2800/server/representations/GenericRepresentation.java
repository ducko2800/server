package ducko2800.server.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Sets some default properties and annotations for all representations.
 * 
 * @author dion-loetscher
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface GenericRepresentation {

	/**
	 * Used to define the version of this representation. Useful for testing and
	 * knowing when a version has been changed. This should be incremented every
	 * time there is an update to a class implementing this interface.
	 * 
	 * @return any number. 
	 */
	public int version();

}
