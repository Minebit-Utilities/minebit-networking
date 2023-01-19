package net.minebit.networking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.minebit.networking.exceptions.IllegalConstructionException;
import net.minebit.networking.exceptions.ReferenceLoadingException;

/**
 * This class is used as a container for several static values about the
 * library, but not necessary for it to function. Such values are the library
 * name, version and GIT URL.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class Reference {

	/**
	 * The name of the library. It normally should be <i>Minebit Networking</i>.
	 */
	public static final String NAME;

	/**
	 * The current version of the library.
	 */
	public static final String VERSION;

	/**
	 * The current display name of the library.
	 */
	public static final String DISPLAY;

	/**
	 * The developers of the library. It normally should be <i>Minebit
	 * Utilities</i>.
	 */
	public static final String DEVELOPERS;

	/**
	 * The GIT URL where the online repository can be found.
	 */
	public static final String GIT;

	/**
	 * The location of the info file in the project resources.
	 */
	private static final String INFO_FILE = "/info.xml";

	static {
		Properties infoProperties = new Properties();
		InputStream infoStream = Reference.class.getResourceAsStream(INFO_FILE);
		try {
			infoProperties.loadFromXML(infoStream);
		} catch (IOException exception) {
			throw new ReferenceLoadingException("The properties file " + INFO_FILE + " and/or its properties could not be loaded!", exception);
		}
		NAME = infoProperties.getProperty("info.name", "Minebit Networking");
		VERSION = infoProperties.getProperty("info.version");
		DISPLAY = infoProperties.getProperty("info.display");
		DEVELOPERS = infoProperties.getProperty("info.developers", "Minebit Utilities");
		GIT = infoProperties.getProperty("info.git");
	}

	/**
	 * This constructor throws an {@link IllegalConstructionException} as
	 * {@link Reference} objects can't and shouldn't be constructed
	 */
	private Reference() {
		throw new IllegalConstructionException(Reference.class.getSimpleName() + " objects cannot be constructed!");
	}

}
