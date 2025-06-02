import java.text.DecimalFormat;

/**
 * A simple calculator that performs basic arithmetic operations based on user input commands.
 */
public class SimpleCalculator {

    private double result = 0.0;
    private int calcCount = 0;
    private String lastOperator = "";
    private double lastValue = 0.0;
    private boolean ended = false;  // Flag to indicate if calculation has ended
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Parses and calculates the result from the given command.
     * 
     * @param cmd the command string (e.g., "+ 5")
     * @throws UnknownCmdException if command format or contents are invalid
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

        // Perform the calculation
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
     * Returns a message reflecting the current or final result.
     * 
     * @return a formatted message depending on calculator state
     */
    public String getMsg() {
        if (ended) {
            return "Final result = " + df.format(result);
        }
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
     * Checks if the command ends the calculation.
     * 
     * @param cmd the input command
     * @return true if cmd is "r" or "R", otherwise false
     */
    public boolean endCalc(String cmd) {
        if (cmd != null && cmd.trim().equalsIgnoreCase("r")) {
            ended = true;  // mark as finished to affect getMsg()
            return true;
        }
        return false;
    }
}

