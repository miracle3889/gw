package nio_tutorial;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by xudabiao on 2017/3/24.
 */
public class abc {

    private final static String base_path = "c:/users/apple/desktop/nio_tutorial/";
    public static void main(String[] args) throws IOException, InterruptedException {
//        writeSomething();
//        copyfile("66.jpg","cp.jpg");
//        socketChannelTest();
//        socketTest();
//        urlTest();
//        btsTest();
        serverSocketChannelTest();
    }


    private static void btsTest()
    {
        byte[] bts = "Ðì´ï±ê".getBytes();
        for (byte bt : bts)
            System.out.println(bt);
    }

    private static void writeSomething()
    {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(base_path+"info.txt", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            byteBuffer.put("HEHE,SHADIAO!!!\n\b\tYESIAM.".getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.format("fileChannel write %d bytes", fileChannel.write(byteBuffer));
            }
            fileChannel.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void copyfile(String from,String to)
    {
        try {
            FileChannel fromChannel = new FileInputStream(new File(base_path,from)).getChannel();
            System.out.println(fromChannel.size());
            FileChannel toChannel = new FileOutputStream(base_path+to).getChannel();
            fromChannel.transferTo(0,fromChannel.size(),toChannel);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void socketChannelTest()
    {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            System.out.println(socketChannel.connect(new InetSocketAddress("bbs.sgamer.com", 80)));
            while (!socketChannel.finishConnect())
                System.out.println("not connected");
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            byteBuffer.put(header().getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();
            int count = socketChannel.read(byteBuffer);
            while (count!=-1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining())
                    System.out.print((char)byteBuffer.get());
                byteBuffer.clear();
                count = socketChannel.read(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serverSocketChannelTest()
    {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9000));
            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println(socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String header(){
        StringBuilder sb = new StringBuilder();
        sb.append("GET /forum-44-1.html HTTP/1.1\r\n");
        sb.append("Host:bbs.sgamer.com\r\n");
        sb.append("Connection: Keep-Alive\r\n");
        sb.append("\r\n");
        return sb.toString();
    }
    private static void socketTest()
    {

        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress("bbs.sgamer.com", 80));
            OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
            osw.write(header());
            osw.flush();
            InputStream inputStream = s.getInputStream();
            int c = 0;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, Charset.forName("utf-8"))
            );
            String line = bufferedReader.readLine();
            while(line!=null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            inputStream.close();
            osw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void urlTest(){
        try {
            URL url = new URL("file:\\C:\\Users\\apple\\Desktop\\img\\66.jpg");
            URLConnection urlConnection = url.openConnection();
            InputStream input = urlConnection.getInputStream();
            System.out.println(input.available());
            int data = input.read();
            while (data != -1) {
                System.out.print(data);
                data = input.read();
            }
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
