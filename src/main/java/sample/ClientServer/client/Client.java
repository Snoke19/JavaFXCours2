package sample.ClientServer.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ClientServer.commands.ClientCommand;
import sample.ClientServer.commands.ClientCommandTypes;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client extends Application {

    private static Socket socket;
    private static OutputStream socketOutputStream;
    private static InputStream socketInputStream;
    private static ObjectOutputStream objectSocketOS;
    private static ObjectInputStream objectSocketIS;

    public void initSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.socketOutputStream = this.socket.getOutputStream();
        this.socketInputStream = this.socket.getInputStream();
        this.objectSocketOS = new ObjectOutputStream(this.socketOutputStream);
        this.objectSocketIS = new ObjectInputStream(this.socketInputStream);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initSocket(sample.ClientServer.client.Socket.getSocket());

        ClassLoader classLoader = getClass().getClassLoader();
        Parent root = FXMLLoader.load(classLoader.getResource("main/window/MainWindow.fxml"));
        primaryStage.setTitle("Catering Company");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }

    public static List sendARequestToTheServer(ClientCommandTypes clientCommandType, List<Object> parametersList) {
        ClientCommand clientCommand = new ClientCommand(clientCommandType, parametersList);
        try {
            objectSocketOS.writeObject(clientCommand);
            return (List) objectSocketIS.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}