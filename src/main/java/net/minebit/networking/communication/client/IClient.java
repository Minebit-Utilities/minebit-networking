package net.minebit.networking.communication.client;

import java.net.InetSocketAddress;

import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.exceptions.communication.client.ClientException;

/**
 * This interface represents a client taking part in a client server web
 * communication.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IClient {
	
	/**
	 * This method starts the client and connects it to the server with the given
	 * address.
	 * 
	 * @param address The address of the server
	 * @throws ClientException If an error occurs while starting the client
	 */
	public void start(InetSocketAddress address) throws ClientException;
	
	/**
	 * This method stops the client.
	 * 
	 * @throws ClientException If an error occurs while stopping the client.
	 */
	public void stop() throws ClientException;

	/**
	 * This method sends the given request to the connected server.
	 * 
	 * @param request The request to send to the connected server.
	 * @param handler What should happen when the request's response is received.
	 * @throws ClientException If an error occurs while sending the request.
	 */
	public void sendRequest(AbstractRequest request, IResponseHandler handler) throws ClientException;	
	
	/**
	 * This method returns the current status of the client.
	 * 
	 * @return The client's status
	 */
	public EClientStatus getStatus();
	
}