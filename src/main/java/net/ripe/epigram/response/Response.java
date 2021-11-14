package net.ripe.epigram.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

/**
 * A helper class for response
 * {
 * "status": the status code as defined
 * "path": the requested uri
 * "data" / "error": the successful data or the error reason
 * }
 */
public class Response {
    protected int status;
    protected String path;

    public Response() {
        // get relative path from uribuilder
        // exception won't happen
        try {
            URI uri = new URI(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            this.path = uri.getPath();
        } catch (Exception ignored) {}

    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }
}
