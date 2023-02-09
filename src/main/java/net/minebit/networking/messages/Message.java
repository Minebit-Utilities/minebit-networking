package net.minebit.networking.messages;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.IBytable;
import net.minebit.networking.util.converters.EConverterContainer;
import net.minebit.networking.util.converters.IConverter;
import net.minebit.networking.util.converters.StringConverter;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class contains all the data necessary to form a message sent from a
 * client to a server and vice versa. A complete message contains:
 * 
 * <ol>
 * <li>A {@link String} title to represent the type of message</li>
 * <li>A {@link Map} containing with {@link String} keys which link to
 * {@link Object}s represented by that {@link String}</li>
 * </ol>
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class Message implements IBytable {

	private final String title;
	private final Map<String, Object> map;

	/**
	 * This constructs a new {@link Message} that represents a message sent from a
	 * server to a client and the opposite.
	 * 
	 * @param title The "title" of the message
	 * @param map   The {@link Object}s contained in the message along with the keys
	 *              accompanying them as a {@link Map}
	 */
	Message(String title, Map<String, Object> map) {
		this.title = new String(title);
		this.map = new HashMap<>(map);
	}

	/**
	 * This method returns the {@link Message}'s title that also represents the type
	 * of the message.
	 * 
	 * @return The message's title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * One specific value contained in the message that is linked to the given key.
	 * If the element is not found or it's value is NULL then an empty
	 * {@link Optional} will be returned.
	 * 
	 * @param key The key associated with the value
	 * @return The resulted value
	 */
	public Optional<Object> getValue(String key) {
		return Optional.ofNullable(this.map.get(key));
	}

	/**
	 * The {@link Message} converted and returned as an array of raw bytes. The
	 * array may be decoded using a {@link MessageBuilder}.
	 * 
	 * @return The message as a byte array
	 */
	public Optional<byte[]> bytes() {
		byte[] titleBytes = StringConverter.INSTANCE.sourceToBytes(this.title).orElse(new byte[0]);
		int titleSize = titleBytes.length, valueNumber = map.size();
		byte[] result = new byte[8 + titleSize];
		byte[] titleSizeBytes = IntegerConverter.INSTANCE.sourceToBytes(titleSize).get();
		ByteUtils.overwrite(result, titleSizeBytes, 0);
		ByteUtils.overwrite(result, titleBytes, 4);
		for (Entry<String, Object> entry : this.map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Optional<byte[]> keyBytesOptional = StringConverter.INSTANCE.sourceToBytes(key);
			Optional<EConverterContainer> converterContainerOptional = EConverterContainer.getOptimal(value);
			if (!keyBytesOptional.isPresent() || !converterContainerOptional.isPresent()) {
				valueNumber--;
				continue;
			}
			byte[] keyBytes = keyBytesOptional.get();
			EConverterContainer converterContainer = converterContainerOptional.get();
			@SuppressWarnings("rawtypes")
			IConverter valueConverter = converterContainer.getConverter();
			@SuppressWarnings("unchecked")
			Optional<byte[]> valueBytesOptional = valueConverter.sourceToBytes(value);
			if (!valueBytesOptional.isPresent()) {
				valueNumber--;
				continue;
			}
			byte[] converterIdBytes = { converterContainer.getId() };
			byte[] valueBytes = valueBytesOptional.get();
			int keySize = keyBytes.length, valueSize = valueBytes.length;
			byte[] keySizeBytes = IntegerConverter.INSTANCE.sourceToBytes(keySize).get();
			byte[] valueSizeBytes = IntegerConverter.INSTANCE.sourceToBytes(valueSize).get();
			byte[] data = new byte[valueSize + keySize + 9];
			ByteUtils.overwrite(data, keySizeBytes, 0);
			ByteUtils.overwrite(data, keyBytes, 4);
			ByteUtils.overwrite(data, valueSizeBytes, 4 + keySize);
			ByteUtils.overwrite(data, valueBytes, 8 + keySize);
			ByteUtils.overwrite(data, converterIdBytes, 8 + keySize + valueSize);
			result = ByteUtils.merge(result, data).get();
		}
		byte[] valueNumberBytes = IntegerConverter.INSTANCE.sourceToBytes(valueNumber).get();
		ByteUtils.overwrite(result, valueNumberBytes, titleSize + 4);
		return Optional.of(result);
	}

}
