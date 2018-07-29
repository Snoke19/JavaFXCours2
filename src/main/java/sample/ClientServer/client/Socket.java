package sample.ClientServer.client;

import java.io.IOException;

class Socket {

    private static java.net.Socket socket;

    public static java.net.Socket getSocket() {
        if (socket == null){
            synchronized (Socket.class) {
                if (socket == null) {
                    try {
                        socket = new java.net.Socket("localhost",2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return socket;
    }
}
