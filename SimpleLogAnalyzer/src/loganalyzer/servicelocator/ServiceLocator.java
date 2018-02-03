package loganalyzer.servicelocator;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private static ServiceLocator serviceLocatorInstance;
    private Map<Class<?>, Object> register = new HashMap<>();

    public static ServiceLocator getInstance() {
        if (serviceLocatorInstance == null) {
            serviceLocatorInstance = new ServiceLocator();
        }
        return serviceLocatorInstance;
    }

    public <T> void register(Class<T> clazz, T object) {
        register.put(clazz, object);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz) {
        return (T) register.get(clazz);
    }

}
