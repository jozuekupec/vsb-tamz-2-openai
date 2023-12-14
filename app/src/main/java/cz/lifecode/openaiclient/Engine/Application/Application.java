package cz.lifecode.openaiclient.Engine.Application;

public class Application {
    private static Application instance = null;

    private Application() {}

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }

        return instance;
    }
}
