package dv147;

import java.io.IOException;
import java.net.SocketTimeoutException;

import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.FrameAccessor.Factory;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

public class FrameAccessorFactory implements Factory {

	@Override
	public FrameAccessor getFrameAccessor(StreamServiceClient client, String streamName) {


		StreamInfo[] infoList = null;


		try {
			infoList = client.listStreams();
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if (infoList == null) {
			System.out.println("infolist null");
			// return null;
		}

		StreamInfo streamInfo = null;

		for(StreamInfo info : infoList) {
			if (info.getName().equals(streamName)) {
				streamInfo = info;
				break;
			}
		}

		System.out.println("factory: " + streamInfo.getName());

		return new SerialFrameAccessor(client, streamInfo);

	}

	@Override
	public FrameAccessor getFrameAccessor(StreamServiceClient[] arg0,
			String arg1) {

		return null;
	}

}
