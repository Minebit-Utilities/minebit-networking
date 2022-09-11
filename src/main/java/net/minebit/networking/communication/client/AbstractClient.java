package net.minebit.networking.communication.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;

import net.minebit.networking.exceptions.communication.client.ClientException;

/**
 * This class represents a client taking part in a client server web
 * communication.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractClient {

	protected SocketChannel channel;

	private EClientStatus status = EClientStatus.DISABLED;

	protected final ByteBuffer buffer;
	protected final IClientListener listener;
	private final Executor executor;
	private final int loopNanos;
	private final long loopMillis;

	protected final Object mutex = new Object();
	private final Runnable loop = () -> loop();

	public AbstractClient(IClientListener listener, Executor executor, int bufferSize, long loopDelay) throws ClientException {
		if (listener == null) {
			throw new ClientException("The given listener cannot be NULL!");
		}
		if (executor == null) {
			throw new ClientException("The given executor cannot be NULL!");
		}
		if (bufferSize < 1) {
			throw new ClientException("The given buffer size cannot be smaller than 1!");
		}
		if (loopDelay < 0) {
			throw new ClientException("The given delay cannot be smaller than 0!");
		}
		this.listener = listener;
		this.executor = executor;
		this.buffer = ByteBuffer.allocate(bufferSize);
		this.loopNanos = (int) (loopDelay % 1000000);
		this.loopMillis = (loopDelay - this.loopNanos) / 1000000;
	}

	/**
	 * This method represents loop controlling several repeatable operations of the
	 * client.
	 */
	private void loop() {
		while (true) {
			synchronized (this.mutex) {
				if (this.getStatus() == EClientStatus.DISABLED) {
					break;
				}
				this.cycle();
			}
			this.delay();
		}
	}

	/**
	 * This method is invoked every time the client loop runs a cycle.
	 */
	protected abstract void cycle();

	/**
	 * This method handles the delay between each cycle of the loop.
	 */
	private void delay() {
		try {
			Thread.sleep(this.loopMillis, this.loopNanos);
		} catch (InterruptedException exception) {
			this.listener.onException(exception);
		}
	}

	/**
	 * This method executes the client loop.
	 */
	protected final void executeLoop() {
		synchronized (this.mutex) {
			this.executor.execute(this.loop);
		}
	}

	/**
	 * This method starts the client and connects it to the server with the given
	 * address.
	 * 
	 * @param address The address of the server
	 * @throws ClientException If an error occurs while starting the client
	 */
	public abstract void start(InetSocketAddress address);

	/**
	 * This method stops the client.
	 * 
	 * @throws ClientException If an error occurs while stopping the client.
	 */
	public abstract void stop() throws ClientException;

	/**
	 * This method sends the given request to the connected server.
	 * 
	 * @throws ClientException If an error occurs while sending the request.
	 */
	public abstract void sendRequest() throws ClientException;

	/**
	 * This method returns the current status of the client.
	 * 
	 * @return The client's status
	 */
	public final EClientStatus getStatus() {
		synchronized (this.mutex) {
			return this.status;
		}
	}

	/**
	 * This method sets the current status of the client.
	 * 
	 * @param status The status to set the client's status to
	 */
	protected final void setStatus(EClientStatus status) {
		synchronized (this.mutex) {
			final EClientStatus previousStatus = this.getStatus();
			this.status = status != null ? status : EClientStatus.DISABLED;
			this.listener.onStatusChange(previousStatus, this.getStatus());
		}
	}

}