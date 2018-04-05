package cmput301w18t22.com.tenner.Helpers;

public class ServerDataConverter {
    private static final ServerDataConverter ourInstance = new ServerDataConverter();

    static ServerDataConverter getInstance() {
        return ourInstance;
    }

    public ServerDataConverter() {
    }
}
