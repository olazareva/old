package ua.lazareva.staticwebserver.model;

public enum HttpMethod {
GET("GET");

private String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public static HttpMethod getMethod(String str) {
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if(httpMethod.method.equalsIgnoreCase(str)){return httpMethod;}
        }
        throw new IllegalArgumentException("Method " + str + " is not supported");
    }
}
