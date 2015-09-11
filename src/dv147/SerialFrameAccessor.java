/**
 * 
 */
package dv147;

import java.io.IOException;
import java.net.SocketTimeoutException;

import ki.types.ds.Block;
import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

/**
 * @author baaz
 *
 */
public class SerialFrameAccessor implements FrameAccessor {
	
	private StreamServiceClient serviceClient;
	private StreamInfo streamInfo;
	private PerformanceStatistics performance;
	
	public SerialFrameAccessor(StreamServiceClient client, StreamInfo info) {
		
		serviceClient = client;
		streamInfo = info;
		performance = new SerialStatistics(client);
		
	}

	
	@Override
	public Frame getFrame(int frameId) throws IOException, SocketTimeoutException {
		
		return null;
	}

	
	@Override
	public PerformanceStatistics getPerformanceStatistics() {
		return performance;
	}

	
	@Override
	public StreamInfo getStreamInfo() throws IOException,
			SocketTimeoutException {
		return streamInfo;
	}

}
