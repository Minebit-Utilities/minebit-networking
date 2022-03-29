package net.minebit.networking.common.keys;

import net.minebit.networking.common.FileData;

/**
 * This class is an {@link ITranslationKey} that defines how files can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class FileTranslationKey implements ITranslationKey<FileData> {

	public static final FileTranslationKey INSTANCE = new FileTranslationKey();

	/**
	 * This constructor constructs a new {@link FileTranslationKey} that translates
	 * a string value from and to a byte array.
	 */
	private FileTranslationKey() {
	}

	/**
	 * This method translates the given file to a byte array.
	 * 
	 * @param The file value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(FileData input) {
		return input.getData();
	}

	/**
	 * This method translates the given byte array to a file.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated file value.
	 */
	@Override
	public FileData translateFromBytes(byte... input) {
		return new FileData(input);
	}

}
