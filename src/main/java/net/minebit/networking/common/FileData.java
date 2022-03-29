package net.minebit.networking.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.minebit.networking.exceptions.InvalidFileException;

/**
 * <i>FileData</i> is a representation of a file which is can be written
 * later to a specific location.
 * 
 * @author Aggelowe
 *
 */
public final class FileData {
 
	private volatile byte[] data;

	/**
	 * This constructor constructs a new {@link FileData} that contains the
	 * bytes of a file and can write them to a given location.
	 * 
	 * @param data The bytes of the file
	 */
	public FileData(byte... data) {
		this.data = data;
	}

	/**
	 * This method writes the file's bytes to the given path.
	 * 
	 * @param path Where the bytes are going to get saved.
	 * @throws IOException
	 */
	public void writeTo(String path) throws IOException {
		this.writeTo(new File(path));
	}

	/**
	 * This method writes the file's bytes to the given path.
	 * 
	 * @param path Where the bytes are going to get saved.
	 * @throws IOException
	 */
	public void writeTo(File path) throws IOException {
		OutputStream output = new FileOutputStream(path);
		output.write(data);
		output.close();
	}

	/**
	 * This method returns the data of the temporary file as a byte array.
	 * 
	 * @return The data of the file.
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * This method reads the file's bytes from the given path. <br>
	 * 
	 * <b>Warning! </b> The file size cannon surpass the limit of 2G.
	 * 
	 * @param path Where the bytes are going to be read from.
	 * @throws IOException
	 */
	public static FileData readFrom(String path) throws IOException {
		return readFrom(new File(path));
	}

	/**
	 * This method reads the file's bytes from the given path. <br>
	 * 
	 * <b>Warning! </b> The file size cannon surpass the limit of 2G.
	 * 
	 * @param path Where the bytes are going to be read from.
	 * @throws IOException
	 */
	public static FileData readFrom(File path) throws IOException {
		if (path.length() < 0 || path.length() > 2147483647) {
			throw new InvalidFileException("The size of the given file cannot surpass the limit of 2G!");
		}
		FileInputStream input = new FileInputStream(path);
		byte[] data = new byte[(int) path.length()];
		input.read(data);
		input.close();
		return new FileData(data);
	}

}
