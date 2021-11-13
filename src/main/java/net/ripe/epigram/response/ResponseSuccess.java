package net.ripe.epigram.response;

public class ResponseSuccess extends Response {
    private Object data;

    public ResponseSuccess(String path, Object data) {
        this.status = 200;
        this.path = path;
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
