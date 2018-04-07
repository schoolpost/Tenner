package cmput301w18t22.com.tenner.helpers;

public class ServerDataConverterHelper {
    private static final ServerDataConverterHelper ourInstance = new ServerDataConverterHelper();

    static ServerDataConverterHelper getInstance() {
        return ourInstance;
    }

    public ServerDataConverterHelper() {
    }
}
