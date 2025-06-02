import java.text.DecimalFormat;

/**
 * A simple calculator that processes string commands with arithmetic operations.
 * It maintains a single result value and provides detailed error handling.
 */
public class SimpleCalculator {

    private double result = 0.0;
    private int calcCount = 0;
    private String lastOperator = "";
    private double lastValue = 0.0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Performs a calculation based on the input command string.
     * 
     * @param cmd the command string in the format: operator value (e.g., "+ 5")
     * @throws UnknownCmdException if the command format or values are invalid
     */
    public void calResult(String cmd) throws UnknownCmdException {
        if (cmd == null || cmd.trim().isEmpty()) {
            throw new UnknownCmdException("Please enter 1 operator and 1 value separated by 1 space");
        }

        String[] parts = cmd.trim().split(" ");
        if (parts.length != 2) {
            throw new UnknownCmdException("Please enter 1 operator and 1 value separated by 1 space");
        }

        String op = parts[0];
        String valStr = parts[1];
        boolean validOp = op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
        boolean validVal = true;
        double val = 0.0;

        try {
            val = Double.parseDouble(valStr);
        } catch (NumberFormatException e) {
            validVal = false;
        }

        if (!validOp && !validVal) {
            throw new UnknownCmdException(op + " is an unknown operator and " + valStr + " is an unknown value");
        } else if (!validOp) {
            throw new UnknownCmdException(op + " is an unknown operator");
        } else if (!validVal) {
            throw new UnknownCmdException(valStr + " is an unknown value");
        }

        if (op.equals("/") && val == 0.0) {
            throw new UnknownCmdException("Can not divide by 0");
        }

        switch (op) {
            case "+":
                result += val;
                break;
            case "-":
                result -= val;
                break;
            case "*":
                result *= val;
                break;
            case "/":
                result /= val;
                break;
        }

        lastOperator = op;
        lastValue = val;
        calcCount++;
    }

    /**
     * Returns a formatted message after each command based on the calculation count.
     * 
     * @return a user-friendly message showing the result state
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
     * Determines whether the command indicates the end of calculation.
     * 
     * @param cmd the user input command
     * @return true if cmd is "r" or "R", false otherwise
     */
    public boolean endCalc(String cmd) {
        return cmd != null && cmd.trim().equalsIgnoreCase("r");
    }

    /**
     * Returns the final result rounded to 2 decimal places.
     * Used for displaying final output.
     *
     * @return final result string
     */
    public String getFinalResult() {
        return "Final result = " + df.format(result);
    }
}

