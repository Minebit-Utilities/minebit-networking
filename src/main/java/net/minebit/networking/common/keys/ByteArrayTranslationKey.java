package net.minebit.networking.common.keys;

/**
 * This class is an {@link ITranslationKey} that defines how byte arrays can
 * be translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class ByteArrayTranslationKey implements ITranslationKey<Byte[]> {

	public static final ByteArrayTranslationKey INSTANCE = new ByteArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link ByteArrayTranslationKey} that
	 * translates a byte array from and to a byte array.
	 */
	private ByteArrayTranslationKey() {
	}

	/**
	 * This method translates the given byte array to a byte array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Byte[] input) {
		byte[] result = new byte[input.length];
		int counter = 0;
		while (counter < input.length) {
			result[counter] = input[counter];
			counter++;
		}
		return result;
	}

	/**
	 * This method translates the given byte array to a byte array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated byte array.
	 */
	@Override
	public Byte[] translateFromBytes(byte... input) {
		Byte[] result = new Byte[input.length];
		int counter = input.length;
		while (counter > 0) {
			result[input.length - counter] = input[input.length - counter];
			counter--;
		}
		return result;
	}

}
