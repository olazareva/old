package loganalyzer.entity;

import java.time.LocalDateTime;

public class LogToken {
    private LocalDateTime time;
    private HttpMethod method;
    private String message;

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }


}

