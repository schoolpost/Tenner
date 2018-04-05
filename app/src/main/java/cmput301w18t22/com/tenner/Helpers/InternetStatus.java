package cmput301w18t22.com.tenner.Helpers;

class InternetStatus {
    private static final InternetStatus ourInstance = new InternetStatus();

    static InternetStatus getInstance() {
        return ourInstance;
    }

    public InternetStatus() {
    }
}
