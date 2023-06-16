package atm;

/**
 * Thrown when a transaction causes the balance in an account to become
 * negative.
 */
class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String balance, String amount) {
		super("No se puede retirar €" + amount + "\nSaldo disponible: €" + balance);

	}
}

/**
 * Thrown when the user attempts to create an account with a negative balance.
 */
class NegativeBalanceException extends Exception {
	public NegativeBalanceException(String balance) {
		super("No se puede crear cuentas con saldo negativo: €" + balance);
	}
}

/**
 * Handling of account information.
 */
public class Account {
	private Integer balance;

	/**
	 * Create a new {@link Account} with the provided balance.
	 *
	 * @param balance The balance for the new account being created.
	 * @throws NegativeBalanceException Thrown when the provided balance is less
	 *                                  than 0.
	 */
	public Account(Integer balance) throws NegativeBalanceException {
		if (balance < 0) {
			throw new NegativeBalanceException(amountToString(balance));
		}

		this.balance = balance;
	}

	/**
	 * Getter for the balance.
	 *
	 * @return The balance in the account.
	 */
	public Integer getBalance() {
		return balance;
	}

	/**
	 * Perform a deposit transaction on the account.
	 *
	 * @param amount The amount to be added to the account.
	 */
	public void deposit(Integer amount) {
		balance += amount;
	}

	/**
	 * Perform a withdrawal transaction on the account.
	 *
	 * @param amount The amount to be withdrawn.
	 * @throws InsufficientBalanceException Thrown when there is not enough cash to
	 *                                      perform the withdrawal.
	 */
	public void withdraw(Integer amount) throws InsufficientBalanceException {
		if (balance < amount) {
			throw new InsufficientBalanceException(toString(), amountToString(amount));
		}

		balance -= amount;
	}

	/**
	 * Translate an amount to a human readable format.
	 *
	 * @param amount The amount to translate.
	 * @return A string with a human readable representation of the amount.
	 */
	static public String amountToString(Integer amount) {
		Integer intPart = amount / 100;
		Integer decPart = amount % 100;

		return intPart.toString() + "." + decPart.toString();
	}

	public String toString() {
		return amountToString(balance);
	}
}
