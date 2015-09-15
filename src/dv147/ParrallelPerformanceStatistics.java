package dv147;

import se.umu.cs._5dv147.a1.client.FrameAccessor;

/**
 * Created by benjamin on 15/09/15.
 */
public class ParrallelPerformanceStatistics implements FrameAccessor.PerformanceStatistics
{
		private int sendPackets;
		private int lossPackets;

		public void increaseSendPackets( int amount, String stream )
		{
			sendPackets += amount;
		}

	public void increaseLostPackets( int amount, String stream )
	{
		sendPackets += amount;
	}

		@Override
		public double getLinkBandwidth( String streams )
		{
				return 0;
		}

		@Override
		public double getPacketDropRate( String s )
		{
				return lossPackets/sendPackets; //??
		}

		@Override
		public double getPacketLatency( String s )
		{
				return 0;
		}

		@Override
		public double getFrameThroughput()
		{
				return 0;
		}

		@Override
		public double getBandwidthUtilization()
		{
				return 0;
		}
}
