package client;

/**
 * Created by User on 05.02.2017
 */
public class ServerException extends RuntimeException {
    private static final long serialVersionUID = -1779952022165406610L;

    ServerException(String message) {
        super(message);
    }
}
