package gobang;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * <p> 网络连接 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName OOP
 * @package gobang
 * @className Net
 * @date 2021/9/29 19:16
 * @TODO
 **/
public class Net {
    /**
     * 输入流
     */
    private InputStream inputStream;
    /**
     * 输出流
     */
    private OutputStream outputStream;


    /**
     * <p> 发送信息 </p>
     *
     * @param msg 要发送的信息
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public void send(String msg) {
        try {
            outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p> 接收 </p>
     *
     * @return java.lang.String 接收到的信息
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public String receive() {
        byte[] data = new byte[100];
        while (true) {
            try {
                int len = inputStream.available();
                if ((len == 0)) {
                    Thread.sleep(500);
                } else {
                    inputStream.read(data, 0, len);
                    return new String(data, 0, len);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * <p> 准备连接 </p>
     *
     * @param port 接收的端口号
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public void ready(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p> 主动连接 </p>
     *
     * @param ip ip地址
     * @param port 端口号
     * @author 汤卫豪
     * @since 2021/9/29
     */
    public void bind(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
