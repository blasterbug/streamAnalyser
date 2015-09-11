package dv147;

import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.FrameAccessor.Factory;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

public class FrameAccessorFactory implements Factory {

	@Override
	public FrameAccessor getFrameAccessor(StreamServiceClient arg0, String arg1) {
		
		// return new SerialFrameAccessor(arg0, arg1);
		return null;
	}

	@Override
	public FrameAccessor getFrameAccessor(StreamServiceClient[] arg0,
			String arg1) {
		
		return null;
	}

}
