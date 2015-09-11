/**
 * 
 */
package dv147;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import ki.types.ds.StreamInfo;

import se.umu.cs._5dv147.a1.client.DefaultStreamServiceClient;
import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.FrameAccessor.Factory;
import se.umu.cs._5dv147.a1.client.FrameAccessor.Frame;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

/**
 * @author baaz
 *
 */
public class StreamAnalyzer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException, IOException {
		
		
		String host = "bellatrix.cs.umu.se";
		int timeout = 1000;
		String username = "dva95blt";
		
		StreamServiceClient client = DefaultStreamServiceClient.bind(host,timeout,username);
		
		StreamInfo streams[] = client.listStreams();
		
		for (StreamInfo sinfo : streams) {
			System.out.println(sinfo.getName());
		}
		
		Factory factory = new FrameAccessorFactory();
		
		FrameAccessor frameAccessor = factory.getFrameAccessor(client, streams[0].getName());
		
		Frame testFrame = frameAccessor.getFrame(0);
		
		
		
	}

}
