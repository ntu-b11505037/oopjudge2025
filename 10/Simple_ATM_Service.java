/**
 * Simple_ATM_Service is an implementation of the ATM_Service interface.
 * It provides basic ATM functionality such as validating balance and withdrawal amount,
 * and processing a withdrawal transaction.
 */
public class Simple_ATM_Service implements ATM_Service {

    /**
     * Checks whether the account has sufficient balance to withdraw the specified amount.
     * 
     * @param account The user's account
     * @param money The amount to withdraw
     * @return true if the account has enough balance
     * @throws ATM_Exception If the balance is not enough (BALANCE_NOT_ENOUGH)
     */
    @Override
    public boolean checkBalance(Account account, int money) throws ATM_Exception {
        if (account.getBalance() < money) {
            throw new ATM_Exception(ATM_Exception.ExceptionTYPE.BALANCE_NOT_ENOUGH);
        }
        return true;
    }

    /**
     * Checks whether the amount is a valid withdrawal amount (must be divisible by 1000).
     * 
     * @param money The amount to check
     * @return true if the amount is valid
     * @throws ATM_Exception If the amount is invalid (AMOUNT_INVALID)
     */
    @Override
    public boolean isValidAmount(int money) throws ATM_Exception {
        if (money % 1000 != 0) {
            throw new ATM_Exception(ATM_Exception.ExceptionTYPE.AMOUNT_INVALID);
        }
        return true;
    }

    /**
     * Attempts to withdraw the specified amount from the account.
     * It first checks the balance and the validity of the amount.
     * If any check fails, it catches the first exception and prints the error message.
     * Regardless of success or failure, it prints the updated account balance.
     * 
     * @param account The user's account
     * @param money The amount to withdraw
     */
    @Override
    public void withdraw(Account account, int money) {
        try {
            checkBalance(account, money);
            isValidAmount(money);
            account.setBalance(account.getBalance() - money);
        } catch (ATM_Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("updated balance : " + account.getBalance());
        }
    }
}
