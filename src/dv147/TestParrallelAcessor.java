package dv147;

import ki.types.ds.StreamInfo;
import se.umu.cs._5dv147.a1.client.DefaultStreamServiceClient;
import se.umu.cs._5dv147.a1.client.FrameAccessor;
import se.umu.cs._5dv147.a1.client.StreamServiceClient;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by Benjamin on 14/09/15.
 */
public class TestParrallelAcessor
{
    public static void main( String[] args ) throws SocketException, IOException
    {



        // hosts list
        String[] hosts = {
                "bellatrix.cs.umu.se",
                "dobby.cs.umu.se",
                "draco.cs.umu.se",
                "harry.cs.umu.se",
        };

        // clients list
        StreamServiceClient[] clients = new DefaultStreamServiceClient[ hosts.length ];

        int timeout = 1000;

        String username = "test2";

        for ( int i = 0 ; hosts.length > i ; i++ )
        {
            clients[ i ] =  DefaultStreamServiceClient.bind(hosts[ i ],timeout,username);
        }

        //StreamInfo streams[] = client.listStreams();

        FrameAccessor.Factory factory = new FrameAccessorFactory();

        FrameAccessor frameAccessor = factory.getFrameAccessor(clients, "stream4");

        StreamInfo info = frameAccessor.getStreamInfo();

        //System.out.println("FrameAccessor.getstream: " + info.getName());
        System.out.println(info.getName());

        for ( int i = 0 ; info.getLengthInFrames() > i; i++ )
        {
            try
            {
                long t1 = System.currentTimeMillis();
                FrameAccessor.Frame testFrame = frameAccessor.getFrame(i);
                long t2 = System.currentTimeMillis();
                System.out.println("Frame received from server in " + (t2 - t1) + " ms");

            }
            catch (SocketTimeoutException e)
            {
                System.out.println("socket timeout");
            }
        }



    }

}
