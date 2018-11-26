package cn.com.sourcetest.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.ImagingOpException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by sky.song on 2018/9/2.
 */
public class ServerClient {



        private static final Logger logger = LoggerFactory.getLogger(ServerClient.class);

        public void client() throws Exception {
            Socket client = null;
            BufferedReader is = null;

            Writer writer = null;
            try {
                client = new Socket("192.168.1.5", 8888);

                writer = new OutputStreamWriter(client.getOutputStream());
                writer.write("Hello Server.");
                writer.write("eof\n");
                writer.flush();

                is = new BufferedReader(new InputStreamReader(client.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String temp;
                int index;
                while ((temp = is.readLine()) != null) {
                    if ((index = temp.indexOf("eof")) != -1) {
                        sb.append(temp.substring(0, index));
                        break;
                    }
                    sb.append(temp);
                }
                // logger.info(sb.toString());
                System.out.println(sb.toString());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (ImagingOpException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    if (client != null) {
                        client.close();
                    }
                } catch (IOException e) {
                    logger.error("Close the IO session error: ", e);
                }
            }

        }

        public static void main(String[] args) throws Exception{
            ServerClient sc = new ServerClient();
            sc.client();
        }



}
