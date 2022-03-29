package net.minebit.networking.common;

import java.util.function.Function;

/**
 * {@link LinkContainer} that contains a specific object and acts as a container
 * for links to this object.
 * 
 * @author Aggelowe
 *
 * @param <ObjectType> The type of the linked object.
 */
public class LinkContainer<ObjectType> {

	private ObjectType object = null;
	private Link<ObjectType>[] links = new Link[0];
	private Function<LinkContainer<ObjectType>, Void> onModification = (object) -> null;

	/**
	 * This method constructs a new {@link Link} that is connected with the object.
	 * 
	 * @return The new {@link Link}
	 */
	public final Link<ObjectType> contructLink() {
		Link<ObjectType>[] links = new Link[this.links.length + 1];
		int counter = 0;
		while (counter < this.links.length) {
			links[counter] = this.links[counter];
			counter++;
		}
		Link<ObjectType> link = new ContainedLink();
		links[this.links.length] = link;
		this.links = links;
		return link;
	}

	/**
	 * This method defines what should happen when the object is modified.
	 * 
	 * @param function The defining {@link Function}.
	 */
	public final void onModification(Function<LinkContainer<ObjectType>, Void> function) {
		this.onModification = function;
	}

	/**
	 * This method returns the value of the contained object.
	 * 
	 * @return The object's value.
	 */
	public ObjectType getObject() {
		return this.object;
	}

	/**
	 * Set the given value of the contained object.
	 * 
	 * @param value The value to change the contained object to.
	 */
	public void setObject(ObjectType object) {
		this.object = object;
		for (Link<ObjectType> link : LinkContainer.this.links) {
			if (link != null) {
				link.onModification();
			}
		}
		this.onModification.apply(this);
	}

	/**
	 * This class represents a new {@link Link} that is specifically constructed to
	 * work with {@link LinkContainer} objects.
	 * 
	 * @author Aggelowe
	 *
	 */
	private class ContainedLink extends Link<ObjectType> {

		@Override
		public void write(ObjectType value) {
			LinkContainer.this.setObject(value);
		}

		@Override
		public ObjectType read() {
			return LinkContainer.this.getObject();
		}

	}

}
