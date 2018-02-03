package loganalyzer.entity;

public enum HttpMethod {
    GET("GET"), POST("POST");

    private String name;

    HttpMethod(String name) {
        this.name = name;
    }

   public static HttpMethod getHttpMethod(String name) {
        for (HttpMethod method : HttpMethod.values()) {
            if (method.name.equals(name)) {
                return method;
            }
        }
        throw new IllegalArgumentException("No HTTP methods found with name '" + name + "'");
    }
}
