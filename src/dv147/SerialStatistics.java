/**
 * 
 */
package dv147;

import se.umu.cs._5dv147.a1.client.FrameAccessor.PerformanceStatistics;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

/**
 * @author baaz
 *
 */
public class SerialStatistics implements PerformanceStatistics {

	
	private double bandWidthUtilization = -1;
	private double frameThroughPut = -1;
	
	private double linkbandWidth = -1;
	private double packetDropRate = -1;
	private double packetlatency = -1;
	
	
	private StreamServiceClient client;
	
	public SerialStatistics(StreamServiceClient streamClient) {
		
		client = streamClient;
	}
	
	
	public void modifyBandWidthUtilization(double value) {
		
	}
	
	
	public void modifyFrameThroughPut(double value) {
		
	}
	
	
	public void modifylinkBandwidth(String host, double value) {
		
		if (host == client.getHost()) {
			
		}
	}
	
	public void modifyPacketDropRate(String host, double value) {
		
		if (host == client.getHost()) {
			
		}
	}
	
	
	public void modifyPacketLatency(String host, double value) {
		
		if (host == client.getHost()) {
			
		}
	}
	
	
	

	@Override
	public double getBandwidthUtilization() {
		
		return bandWidthUtilization;
	}

	
	@Override
	public double getFrameThroughput() {
		
		return frameThroughPut;
	}

	
	@Override
	public double getLinkBandwidth(String host) {
		
		if (host == client.getHost()) {
			
			return linkbandWidth;
		}
		
		return -1;
	}

	
	@Override
	public double getPacketDropRate(String host) {
		
		if (host == client.getHost()) {
			
			return packetDropRate;
		}
		
		return -1;
	}

	
	@Override
	public double getPacketLatency(String host) {
		
		if (host == client.getHost()) {
			
			return packetlatency;
		}
		
		return -1;
	}

}
