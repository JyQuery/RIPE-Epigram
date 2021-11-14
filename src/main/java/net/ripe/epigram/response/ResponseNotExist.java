package net.ripe.epigram.response;

/**
 * Response for resource not found
 */
public class ResponseNotExist extends ResponseFail{

    public ResponseNotExist() {
        super(404, "Epigram Not exist");
    }
}
