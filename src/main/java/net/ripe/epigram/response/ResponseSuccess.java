package net.ripe.epigram.response;

/**
 * Response for a successful request
 */
public class ResponseSuccess extends Response {
    private Object data;

    public ResponseSuccess(Object data) {
        super();
        this.status = 200;
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
