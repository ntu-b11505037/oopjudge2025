/**
 * ATM_Exception is a custom exception class for ATM operations.
 * It represents two types of errors: balance not sufficient and invalid withdrawal amount.
 */
public class ATM_Exception extends Exception {

    /**
     * ExceptionTYPE defines the specific reasons why an ATM_Exception can be thrown.
     */
    public enum ExceptionTYPE {
        BALANCE_NOT_ENOUGH,
        AMOUNT_INVALID
    }

    /** Holds the specific exception condition for this instance */
    private ExceptionTYPE exceptionCondition;

    /**
     * Constructs an ATM_Exception with a specific exception type.
     * 
     * @param exceptionCondition The type of exception (e.g., BALANCE_NOT_ENOUGH or AMOUNT_INVALID)
     */
    public ATM_Exception(ExceptionTYPE exceptionCondition) {
        this.exceptionCondition = exceptionCondition;
    }

    /**
     * Returns a string representation of the exception type.
     * 
     * @return The name of the exception condition
     */
    @Override
    public String getMessage() {
        return exceptionCondition.name();
    }
}
