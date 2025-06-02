import java.text.DecimalFormat;

/**
 * A simple calculator that maintains a running result starting from 0.00.
 * Supports basic arithmetic operations: addition, subtraction, multiplication, and division.
 * Handles command validation and formats result to 2 decimal places.
 */
public class SimpleCalculator {

    /** Stores the current result of calculations (not rounded). */
    private double result = 0.0;

    /** Counts how many successful calculations have been performed. */
    private int calcCount = 0;

    /** The most recent valid operator used. */
    private String lastOperator = "";

    /** The most recent valid value used in an operation. */
    private double lastValue = 0.0;

    /** Formatter for rounding numbers to 2 decimal places in output. */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Parses and executes a single calculator command.
     * 
     * @param cmd the command string in the form "operator value" (e.g., "+ 5")
     * @throws UnknownCmdException if the command format is invalid or causes an error
     */
    public void calResult(String cmd) throws UnknownCmdException {
        if (cmd == null || cmd.trim().isEmpty()) {
            throw new UnknownCmdException("Please enter 1 operator and 1 value separated by 1 space");
        }

        String[] parts = cmd.trim().split(" ");
        if (parts.length != 2) {
            throw new UnknownCmdException("Please enter 1 operator and 1 value separated by 1 space");
        }

        String operator = parts[0];
        String valueStr = parts[1];

        boolean validOp = operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
        boolean validValue = true;
        double value = 0.0;

        try {
            value = Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            validValue = false;
        }

        // Exception logic
        if (!validOp && !validValue) {
            throw new UnknownCmdException(operator + " is an unknown operator and " + valueStr + " is an unknown value");
        } else if (!validOp) {
            throw new UnknownCmdException(operator + " is an unknown operator");
        } else if (!validValue) {
            throw new UnknownCmdException(valueStr + " is an unknown value");
        }

        if (operator.equals("/") && value == 0.0) {
            throw new UnknownCmdException("Can not divide by 0");
        }

        // Apply the operation
        switch (operator) {
            case "+":
                result += value;
                break;
            case "-":
                result -= value;
                break;
            case "*":
                result *= value;
                break;
            case "/":
                result /= value;
                break;
        }

        // Store last successful command info
        lastOperator = operator;
        lastValue = value;
        calcCount++;
    }

    /**
     * Returns a formatted message describing the current state or the result of the last operation.
     *
     * @return a message corresponding to the calculator state or last result
     */
    public String getMsg() {
        if (calcCount == 0) {
            return "Calculator is on. Result = " + df.format(result);
        } else if (calcCount == 1) {
            return "Result " + lastOperator + " " + df.format(lastValue) + " = " +
                   df.format(result) + ". New result = " + df.format(result);
        } else {
            return "Result " + lastOperator + " " + df.format(lastValue) + " = " +
                   df.format(result) + ". Updated result = " + df.format(result);
        }
    }

    /**
     * Determines whether the calculator should end based on the command.
     *
     * @param cmd the input command
     * @return true if command is "r" or "R", false otherwise
     */
    public boolean endCalc(String cmd) {
        return cmd != null && cmd.trim().equalsIgnoreCase("r");
    }
}
