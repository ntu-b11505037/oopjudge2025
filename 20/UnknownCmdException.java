/**
 * Custom exception for handling unknown or malformed calculator commands.
 */
public class UnknownCmdException extends Exception {
    /**
     * Constructs an UnknownCmdException with a specific error message.
     *
     * @param errMessage the error message explaining the reason for exception
     */
    public UnknownCmdException(String errMessage) {
        super(errMessage);
    }
}
