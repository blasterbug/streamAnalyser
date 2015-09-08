package se.umu.cs._5dv147.a1.client;

import java.io.IOException;
import java.net.SocketTimeoutException;
import ki.types.ds.Block;
import ki.types.ds.StreamInfo;

public final class ExampleClient
{
  public static final int DEFAULT_TIMEOUT = 1000;


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static void listStreamInfo (StreamServiceClient client)
    throws IOException
  {
    StreamInfo[] streams = client.listStreams();
    System.out.println("found " + streams.length + " streams");
    for (StreamInfo stream : streams)
    {
      System.out.println("  '" + stream.getName() + "': " + stream.getLengthInFrames() + " frames, " +
                         stream.getWidthInBlocks() + " x " + stream.getHeightInBlocks() + " blocks");
    }
  }

  //----------------------------------------------------------
	public static void main (String[] args)
	{
    try
    {
      final String host     = (args.length > 0) ? args[0] : "localhost";
      final int timeout     = (args.length > 1) ? Integer.parseInt(args[1]) : DEFAULT_TIMEOUT;
      final String username = (args.length > 2) ? args[2] : "test";

      StreamServiceClient client = DefaultStreamServiceClient.bind(host,timeout,username);

      listStreamInfo(client);

      int nr = 100;
      int count = 0;
      String stream = "stream1";
      int frame = 0;
      int blockX = 0;
      int blockY = 0;
      for (int i=0; i<nr; i++)
      {
        try
        {
          long t1 = System.currentTimeMillis();
          Block block = client.getBlock(stream,frame,blockX,blockY);
          long t2 = System.currentTimeMillis();
          System.out.println("block received from server in " + (t2 - t1) + " ms");
          count++;
        }
        catch (SocketTimeoutException e)
        {
          System.out.println("socket timeout");
        }
      }
      System.out.println("received " + count + " / " + nr);
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	  }
	}
}
