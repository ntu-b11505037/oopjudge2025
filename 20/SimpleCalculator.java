import java.text.DecimalFormat;

public class SimpleCalculator {
    private double result = 0.0;
    private int calcCount = 0;
    private String lastOperator = "";
    private double lastValue = 0.0;

    DecimalFormat df = new DecimalFormat("0.00");

    public void calResult(String cmd) throws UnknownCmdException {
        // Trim command
        cmd = cmd.trim();

        // Check form
        String[] parts = cmd.split(" ");
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

        // Store last valid command for getMsg()
        lastOperator = operator;
        lastValue = value;

        // Perform operation
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

        calcCount++;
    }

    public String getMsg() {
        if (calcCount == 0) {
            return "Calculator is on. Result = " + df.format(result);
        } else if (calcCount == 1) {
            return "Result " + lastOperator + " " + df.format(lastValue) + " = " + df.format(result) +
                   ". New result = " + df.format(result);
        } else {
            return "Result " + lastOperator + " " + df.format(lastValue) + " = " + df.format(result) +
                   ". Updated result = " + df.format(result);
        }
    }

    public boolean endCalc(String cmd) {
        return cmd.equalsIgnoreCase("r");
    }
}
