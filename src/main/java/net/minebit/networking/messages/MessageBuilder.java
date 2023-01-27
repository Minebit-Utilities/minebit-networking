package net.minebit.networking.messages;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.ILoadableBuilder;
import net.minebit.networking.util.converters.EConverterContainer;
import net.minebit.networking.util.converters.IConverter;
import net.minebit.networking.util.converters.StringConverter;
import net.minebit.networking.util.converters.primitives.ByteConverter;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents a builder used to build {@link Message}s by taking the
 * provided information and parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageBuilder implements ILoadableBuilder<Message> {

	private final Object mutex = new Object();

	private String title = null;
	private final Map<String, Object> map = new HashMap<>();

	/**
	 * This method returns a newly constructed empty {@link MessageBuilder} builder.
	 * 
	 * @return The new {@link MessageBuilder}
	 * @see #MessageBuilder()
	 */
	public static MessageBuilder empty() {
		return new MessageBuilder();
	}

	/**
	 * This method constructs a new {@link MessageBuilder} used to build new
	 * {@link Message} objects with the data provided and return them.
	 */
	private MessageBuilder() {
	}

	/**
	 * This method changes the current title of the to-build message to the given
	 * one
	 * 
	 * @param title The new title
	 */
	public void setTitle(String title) {
		synchronized (this.mutex) {
			this.title = title;
		}
	}

	/**
	 * This method puts the given entry along with the other entries. If the key of
	 * the given entry is already contained then the old entry with the same key
	 * will be replaced with the given one.
	 * 
	 * @param key    The key of the entry
	 * @param object The value of the entry
	 */
	public void setEntry(String key, Object object) {
		synchronized (this.mutex) {
			this.map.put(key, object);
		}
	}

	/**
	 * This method finalizes the building process of the {@link Message} and returns
	 * the final result contained in an {@link Optional}. If the {@link Message}
	 * could not be constructed an empty {@link Optional} will be returned.
	 */
	@Override
	public Optional<Message> build() {
		synchronized (this.mutex) {
			if (this.title == null) {
				return Optional.empty();
			}
			return Optional.of(new Message(this.title, this.map));
		}
	}

	/**
	 * This method decodes the given byte array and replaces the current
	 * {@link Message} title and entries with the ones obtained from the given byte
	 * array.
	 */
	@Override
	public boolean load(byte[] data) {
		synchronized (this.mutex) {
			Optional<byte[]> titleSizeBytes = ByteUtils.rip(data, 0, 3);
			if (!titleSizeBytes.isPresent()) {
				return false;
			}
			int titleSize = IntegerConverter.INSTANCE.bytesToSource(titleSizeBytes.get()).get(), position = 8 + titleSize;
			byte[] titleBytes = ByteUtils.rip(data, 4, titleSize + 3).orElse(new byte[0]);
			Optional<byte[]> valueNumberBytes = ByteUtils.rip(data, titleSize + 4, titleSize + 7);
			if (!valueNumberBytes.isPresent()) {
				return false;
			}
			String title = StringConverter.INSTANCE.bytesToSource(titleBytes).get();
			int valueNumber = IntegerConverter.INSTANCE.bytesToSource(valueNumberBytes.get()).get();
			for (int counter = 1; counter <= valueNumber; counter++) {
				Optional<byte[]> keySizeBytes = ByteUtils.rip(data, position, position + 3);
				if (!keySizeBytes.isPresent()) {
					return false;
				}
				position += 4;
				int keySize = IntegerConverter.INSTANCE.bytesToSource(keySizeBytes.get()).get();
				byte[] keyBytes = ByteUtils.rip(data, position, position + keySize - 1).orElse(new byte[0]);
				position += keySize;
				String key = StringConverter.INSTANCE.bytesToSource(keyBytes).get();
				Optional<byte[]> valueSizeBytes = ByteUtils.rip(data, position, position + 3);
				if (!valueSizeBytes.isPresent()) {
					return false;
				}
				position += 4;
				int valueSize = IntegerConverter.INSTANCE.bytesToSource(valueSizeBytes.get()).get();
				byte[] valueBytes = ByteUtils.rip(data, position, position + valueSize - 1).orElse(new byte[0]);
				position += valueSize;
				Optional<byte[]> converterIdBytes = ByteUtils.rip(data, position, position);
				if (!converterIdBytes.isPresent()) {
					return false;
				}
				position++;
				byte converterId = ByteConverter.INSTANCE.bytesToSource(converterIdBytes.get()).get();
				Optional<EConverterContainer> converterContainerOptional = EConverterContainer.getById(converterId);
				if (!converterContainerOptional.isPresent()) {
					continue;
				}
				@SuppressWarnings("rawtypes")
				IConverter valueConverter = converterContainerOptional.get().getConverter();
				@SuppressWarnings("unchecked")
				Optional<Object> valueOptional = valueConverter.bytesToSource(valueBytes);
				if (!valueOptional.isPresent()) {
					continue;
				}
				Object value = valueOptional.get();
				map.put(key, value);
			}
			this.title = title;
			return true;
		}
	}

}
