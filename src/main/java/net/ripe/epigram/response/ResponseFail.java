package net.ripe.epigram.response;

public class ResponseFail extends Response {
    private String error;

    public ResponseFail(String path, int status, String error) {
        this.path = path;
        this.status = status;
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
