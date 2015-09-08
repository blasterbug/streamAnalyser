package se.umu.cs._5dv147.a1.client;

import java.io.IOException;
import java.net.SocketTimeoutException;
import ki.types.ds.Block;
import ki.types.ds.StreamInfo;

public interface FrameAccessor
{
  //----------------------------------------------------------
  public StreamInfo getStreamInfo ()
    throws IOException, SocketTimeoutException;

  //----------------------------------------------------------
  public Frame getFrame (int frame)
    throws IOException, SocketTimeoutException;

  //----------------------------------------------------------
  public PerformanceStatistics getPerformanceStatistics ();


  //----------------------------------------------------------
  //----------------------------------------------------------
  public interface Frame
  {
    //----------------------------------------------------------
    public Block getBlock (int blockX, int blockY)
      throws IOException, SocketTimeoutException;
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public interface PerformanceStatistics
  {
    //----------------------------------------------------------
    // returns link bandwidth in bits per second (bps) (or -1 for unused hosts)
    public double getLinkBandwidth (String host);

    //----------------------------------------------------------
    // returns packet drop rate in percent (%) (or -1 for unused hosts)
    public double getPacketDropRate (String host);

    //----------------------------------------------------------
    // returns packet latency in milliseconds (ms) (or -1 for unused hosts)
    public double getPacketLatency (String host);

    //----------------------------------------------------------
    // returns frame throughput in frames per second (fps)
    public double getFrameThroughput ();

    //----------------------------------------------------------
    // returns bandwidth utilization in bits per second (bps)
    public double getBandwidthUtilization ();
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public interface Factory
  {
    //----------------------------------------------------------
    public FrameAccessor getFrameAccessor (String stream)
      throws IOException, SocketTimeoutException;
  }
}
