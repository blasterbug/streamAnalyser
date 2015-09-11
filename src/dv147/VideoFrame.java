/**
 * 
 */
package dv147;

import java.io.IOException;
import java.net.SocketTimeoutException;

import ki.types.ds.Block;
import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.FrameAccessor.Frame;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

/**
 * @author baaz
 *
 */
public class VideoFrame implements Frame {
	
	private int id;
	private StreamInfo info;
	private StreamServiceClient client;
	
	public VideoFrame(int frameId, StreamInfo streamInfo, StreamServiceClient streamClient) {
		
		id = frameId;
		info = streamInfo;
		client = streamClient;
	}

	/* (non-Javadoc)
	 * @see se.umu.cs._5dv147.a1.client.FrameAccessor.Frame#getBlock(int, int)
	 */
	@Override
	public Block getBlock(int xPos, int yPos) throws IOException,
			SocketTimeoutException {

		if ((xPos >= info.getWidthInBlocks()) || (yPos >= info.getHeightInBlocks()))
			return null;
					
		
		return client.getBlock(info.getName(), id, xPos, yPos);
	}

}
