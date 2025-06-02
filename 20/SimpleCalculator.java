public class SimpleCalculator {
        private char operator;
        private double operand;

	private double result;
        private int cal_count;

        private boolean is_cal_ended;

        public SimpleCalculator() {
                this.result = 0.0;
                this.cal_count = 0;

                this.is_cal_ended = false;
        }

	public void calResult(String cmd) throws UnknownCmdException {
                String[] operator_operand;

                boolean is_operand_invalid;

                operator_operand = cmd.split(" ");

                is_operand_invalid = false;
                try {
                        operand = Double.parseDouble(operator_operand[1]);
                } catch (NumberFormatException e) {
                        is_operand_invalid = true;
                }
                operator = operator_operand[0].charAt(0);

                if (!(operator == '+' || operator == '-' || operator == '*' || operator == '/')) {
                        if (is_operand_invalid) {
                                throw new UnknownCmdException(
                                        "" + operator + " is an unknown operator and " +
                                        operator_operand[1] + " is an unknown value");
                        } else {
                                throw new UnknownCmdException(
                                        operator + " is an unknown operator");
                        }
                } else {
                        if (is_operand_invalid) {
                                throw new UnknownCmdException(
                                        operator_operand[1] + " is an unknown value");
                        }
                }

                switch (operator) {
                case '+':
                        result += operand;
                        break;
                case '-':
                        result -= operand;
                        break;
                case '*':
                        result *= operand;
                        break;
                case '/':
                        result /= operand;
                        break;
                }
                ++cal_count;
	}

	public String getMsg() {
                String result_fmt;
                int result_fmt_len;
                int i;
                int index, count;

                result_fmt = String.format("%.2f", result);
                result_fmt_len = result_fmt.length();
                count = 0;
                if ((index = result_fmt.indexOf(".")) != -1) {
                        for (i = result_fmt_len - 1;
                             i >= index && result_fmt.charAt(i) == '0';
                             --i, ++count)
                                ;
                        if (count > 0)
                                result_fmt = result_fmt.substring(0, result_fmt_len - count + 1);
                }

                if (is_cal_ended)
                        return ("Final result = " + result_fmt);

                if (cal_count == 0)
                        return ("Calculator is on. Result = " + result_fmt);
                else if (cal_count == 1)
                        return ("Result " + operator + " " + operand + " = " + result_fmt + ". " +
                                "New result = " + result_fmt);
                else
                        return ("Result " + operator + " " + operand + " = " + result_fmt + ". " +
                                "Updated result = " + result_fmt);
	}

	public boolean endCalc(String cmd) {
                char tmp_c;

                tmp_c = cmd.charAt(0);
                if (tmp_c == 'R' || tmp_c == 'r') {
                        is_cal_ended = true;
                        return true;
                } else {
                        return false;
                }
	}
}