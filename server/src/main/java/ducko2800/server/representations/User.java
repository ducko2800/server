package ducko2800.server.representations;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements GenericRepresentation {

	@NotEmpty
	private String username;
	private String password;
	private String salt;

	/**
	 * Returns the user's username as known by the system.
	 * 
	 * @return A non empty string.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user's username in the system. Also removes all leading and
	 * trailing whitespace and lowercases the entire string.
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new NullPointerException("username cannot be null or empty");
		}
		this.username = username.toLowerCase().trim();
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int version() {
		return 0;
	}

}
