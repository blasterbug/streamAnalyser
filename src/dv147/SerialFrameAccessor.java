/**
 *
 */
package dv147;

import ki.types.ds.Block;
import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @author baaz
 *
 */
public class SerialFrameAccessor implements FrameAccessor {

	private StreamServiceClient serviceClient;
	private StreamInfo streamInfo;
	private PerformanceStatistics performance;

	private int packetCounter = 0;
	private int lossCounter = 0;

	public SerialFrameAccessor(StreamServiceClient client, StreamInfo info) {

		serviceClient = client;
		streamInfo = info;
		performance = new SerialStatistics(client);


	}


	@Override
	public Frame getFrame(int frameId) throws IOException {

		VideoFrame frame = new VideoFrame(frameId, streamInfo);
		Block b = null;

		for (int y = 0; y < streamInfo.getHeightInBlocks(); y++) {
			for (int x = 0; x < streamInfo.getWidthInBlocks(); x++) {

				packetCounter++;

				while (b == null) {
					try {
						b = serviceClient.getBlock(streamInfo.getName(), frameId, x, y);

					} catch (SocketTimeoutException e) {
						//packet loss
						System.out.println("Packet lost");
						lossCounter++;
						b = null;
					}
				}
				frame.addBlock(y, x, b);
				System.out.println("Added block:" + frameId + " " + x + " " + y);
				b = null;

			}
		}
		return frame;
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
