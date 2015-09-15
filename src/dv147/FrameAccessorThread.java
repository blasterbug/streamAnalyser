package dv147;

import ki.types.ds.Block;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by benjamin on 14/09/15.
 */
public class FrameAccessorThread extends Thread
{

		private ParrallelPerformanceStatistics stat;
		private String streamName;
		private StreamServiceClient serviceClient;
		private int bx_min;
		private int bx_max;
		private int by_min;
		private int by_max;
		private VideoFrame frame;

		public FrameAccessorThread( String stream, StreamServiceClient serviceClient, ParrallelPerformanceStatistics stat )
		{
				streamName = stream;
				this.serviceClient = serviceClient;
				this.stat = stat;
		}

		/**
		 * Define  the range of blocks it will get
		 *
		 * @param bx_min
		 * @param bx_max
		 * @param by_min
		 * @param by_max
		 */
		public void setRangeBlocks( int bx_min, int bx_max, int by_min, int by_max )
		{
				this.bx_min = bx_min;
				this.bx_max = bx_max;
				this.by_min = by_min;
				this.by_max = by_max;
		}

		/**
		 * set the Frame to get
		 *
		 * @param frame
		 */
		public void setFrame( VideoFrame frame )
		{
				this.frame = frame;
		}

		public void run()
		{

				//VideoFrame frame = new VideoFrame(frameId, streamInfo);
				Block b = null;

				for ( int y = by_min; by_max > y; y++ )
				{
						for ( int x = bx_min; bx_max > x; x++ )
						{
								stat.increaseSendPackets( 1, streamName );

								while ( b == null )
								{
										try
										{
												b = serviceClient.getBlock( streamName, frame.getId(), x, y );

										} catch ( SocketTimeoutException e )
										{
												//packet loss
												System.err.println( getId() + " : Packet lost" );
												stat.increaseLostPackets( 1, streamName );
												b = null;
										} catch ( IOException e )
										{
												System.err.println( getId() + e.toString() );
										}
								}
								frame.addBlock( y, x, b );
								System.out.println( getId() + " : Added block: " + frame.getId() + " " + x + " " + y );
								b = null;
						}
				}
				//return frame;

		}

}
