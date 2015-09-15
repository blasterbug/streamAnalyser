package dv147;

import ki.types.ds.Block;
import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by benjamin on 14/09/15.
 */
public class FrameAccessorThread extends Thread {

    private String streamName;
    private StreamServiceClient serviceClient;
    private int bx_min;
    private int bx_max;
    private int by_min;
    private int by_max;
    private int frameId;
    private VideoFrame frame;

    public FrameAccessorThread(String stream, StreamServiceClient serviceClient) {
        streamName = stream;
        this.serviceClient = serviceClient;
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
    public void setFrame(VideoFrame frame, int frameid)
    {
        this.frame = frame;
        frameId = frameid;
    }

    public void run() {

        //VideoFrame frame = new VideoFrame(frameId, streamInfo);
        Block b = null;

        for ( int y = by_min ; y < by_max; y++ )
        {
            for ( int x = bx_min ; x < bx_max ; x++ )
            {
                //packetCounter++;

                while (b == null)
                {
                    try
                    {
                        b = serviceClient.getBlock( streamName, frameId, x, y );

                    }
                    catch ( SocketTimeoutException e )
                    {
                        //packet loss
                        System.err.println("Packet lost ");
                        //lossCounter++;
                        b = null;
                    }
                    catch ( IOException e )
                    {
                        System.err.println( e );
                    }
                }
                frame.addBlock( y, x, b );
                System.out.println( "Added block:" + frameId + " " + x + " " + y );
                b = null;
            }
        }
        //return frame;

    }

}
