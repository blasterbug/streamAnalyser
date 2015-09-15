package dv147;

import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * Created by benjamin on 14/09/15.
 */
public class ParallelFrameAccessor implements FrameAccessor
{

		private StreamServiceClient[] clients;
		private StreamInfo streamInfo;
		private ParrallelPerformanceStatistics stat;

		private int packetCounter = 0;
		private int lossCounter = 0;

		public ParallelFrameAccessor( StreamServiceClient[] clients, String stream )
		{
				this.clients = clients;
				try
				{
						// search stream in the frist host
						for ( StreamInfo strm : clients[0].listStreams() )
								if ( strm.getName().equals( stream ) )
								{
										streamInfo = strm;
										//System.out.println(streamInfo.getName());
								}
				} catch ( IOException e )
				{
						System.err.println( e.getMessage() );
				}
				stat = new ParrallelPerformanceStatistics();
		}


		@Override
		// fill a frame with several threads
		// There is a thread for each client
		// The frame is divided into subparts fill by each threads
		public Frame getFrame( int frameId ) throws IOException
		{

				// create a new frame
				VideoFrame frame = new VideoFrame( frameId, streamInfo );
				// create a array to store the threads
				//ArrayList<FrameAccessorThread> threads = new ArrayList<FrameAccessorThread>();
				// compute subframes size to fill by threads
				int bx_max = streamInfo.getWidthInBlocks();
				int by_max = streamInfo.getHeightInBlocks();

				int increment_bx = bx_max / clients.length;
				int increment_by = by_max / clients.length;

				int bx_min = 0;
				int by_min = 0;

				for ( StreamServiceClient client : clients )
				{
						FrameAccessorThread thread = new FrameAccessorThread( streamInfo.getName(), client, stat );
						thread.setFrame( frame );
						thread.setRangeBlocks( bx_min, bx_min += increment_bx, by_min, by_min += increment_by );
						thread.run();
						//threads.add( thread );
				}
				return frame;
		}


		@Override
		public PerformanceStatistics getPerformanceStatistics()
		{
				return stat;
		}


		@Override
		public StreamInfo getStreamInfo() throws IOException,
						SocketTimeoutException
		{
				return streamInfo;
		}
}
