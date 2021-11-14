package net.ripe.epigram.response;

/**
 * Response for a failed request
 */
public class ResponseFail extends Response {
    private String error;

    public ResponseFail(int status, String error) {
        super();
        this.status = status;
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
