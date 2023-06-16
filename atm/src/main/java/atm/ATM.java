package atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

enum MenuOption {
	CREATE_EMPTY_ACCOUNT,
	CREATE_ACCOUNT,
	DEPOSIT,
	WITHDRAWAL,
	CHECK_BALANCE,
	EXIT,
}

public class ATM {
	private Account account;
	private BufferedReader br;

	public ATM() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Print the main menu to the user and get their selection.
	 *
	 * @return A {@link MenuOption} depending on the selected option.
	 */
	private MenuOption menu() {
		while (true) {
			System.out.println("Seleccione la opción deseada:");
			System.out.println("1. Crear cuenta vacía");
			System.out.println("2. Crear cuenta con saldo");
			System.out.println("3. Realizar un depósito");
			System.out.println("4. Realizar una retirada");
			System.out.println("5. Ver saldo");
			System.out.println("6. Salir");

			Integer option;
			try {
				option = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("Opción inválida: " + e.getMessage());
				continue;
			}

			switch (option) {
				case 1:
					return MenuOption.CREATE_EMPTY_ACCOUNT;
				case 2:
					return MenuOption.CREATE_ACCOUNT;
				case 3:
					return MenuOption.DEPOSIT;
				case 4:
					return MenuOption.WITHDRAWAL;
				case 5:
					return MenuOption.CHECK_BALANCE;
				case 6:
					return MenuOption.EXIT;
				default:
					System.out.println("Opción inválida: " + option);
			}
		}
	}

	/**
	 * Create a new {@link Account} with the provided balance.
	 *
	 * @param balance The balance the new {@link Account} should have.
	 * @return A string describing the result of the operation.
	 */
	private String createAccount(Integer balance) {
		try {
			account = new Account(balance);
		} catch (NegativeBalanceException e) {
			return "Error: " + e.getMessage();
		}

		return "Creada cuenta con balance €" + account.toString();
	}

	/**
	 * Perform a deposit transaction on the current {@link Account}.
	 *
	 * @param amount The amount to deposit into the account.
	 * @return A string describing the result of the operation.
	 * @throws NullPointerException Thrown when no account is selected.
	 */
	private String deposit(Integer amount) throws NullPointerException {
		account.deposit(amount);

		return "Depósito correcto. Nuevo saldo: €" + account.toString();
	}

	/**
	 * Perform a withdrawal transaction on the current {@link Account}.
	 *
	 * @param amount The amount to withdraw from the {@link Account}.
	 * @return A string describing the result of the operation.
	 * @throws NullPointerException Thrown when no account is selected.
	 */
	private String withdraw(Integer amount) throws NullPointerException {
		try {
			account.withdraw(amount);
		} catch (InsufficientBalanceException e) {
			return "Error: " + e.getMessage();
		}

		return "Extracción correcta. Nuevo saldo: €" + account.toString();
	}

	/**
	 * Return a string with the balance in the {@link Account} in a human readable
	 * format.
	 *
	 * @return a string with the balance in the {@link Account} in a human readable
	 *         format.
	 * @throws NullPointerException Thrown when no account is selected.
	 */
	private String checkBalance() throws NullPointerException {
		return "Saldo disponible: €" + account.toString();
	}

	/**
	 * Helper class for uniformly logging the result of a transaction.
	 *
	 * @param msg The message to log.
	 */
	private void logResult(String msg) {
		System.out.println("El resultado de la operación es: " + msg);
	}

	/**
	 * Ask the user to input an amount and turn it into an {@link Account}
	 * compatible format.
	 *
	 * @return An integer with the input amount.
	 * @throws IOException           Thrown when a system error occurs while reading
	 *                               stdin.
	 * @throws NumberFormatException Thrown when the number input by the user is in
	 *                               a wrong format.
	 */
	private Integer getUserAmount() throws IOException, NumberFormatException {
		System.out.print("Ingrese el monto deseado: €");
		Float rawAmount = Float.parseFloat(br.readLine()) * 100;
		return rawAmount.intValue();
	}

	/**
	 * Run in a continuous loop, asking the user for input, performing transactions
	 * and logging the result of said transactions.
	 */
	public void run() {
		for (MenuOption option = menu(); option != MenuOption.EXIT; option = menu()) {
			String result;
			Integer amount;

			switch (option) {
				case CREATE_EMPTY_ACCOUNT:
					result = createAccount(0);
					break;
				case CREATE_ACCOUNT:
					try {
						amount = getUserAmount();
					} catch (IOException e) {
						result = "Monto inválido: " + e.getMessage();
						break;
					} catch (NumberFormatException e) {
						result = "Valor ingresado no es numérico: " + e.getMessage();
						break;
					}

					result = createAccount(amount);
					break;
				case DEPOSIT:
					try {
						amount = getUserAmount();
					} catch (IOException e) {
						result = "Monto inválido: " + e.getMessage();
						break;
					} catch (NumberFormatException e) {
						result = "Valor ingresado no es numérico: " + e.getMessage();
						break;
					}

					try {
						result = deposit(amount);
					} catch (NullPointerException e) {
						result = "Error: Debe crear una cuenta antes de operar";
					}

					break;
				case WITHDRAWAL:
					try {
						amount = getUserAmount();
					} catch (IOException e) {
						result = "Monto inválido: " + e.getMessage();
						break;
					} catch (NumberFormatException e) {
						result = "Valor ingresado no es numérico: " + e.getMessage();
						break;
					}

					try {
						result = withdraw(amount);
					} catch (NullPointerException e) {
						result = "Error: Debe crear una cuenta antes de operar";
					}
					break;
				case CHECK_BALANCE:
					try {
						result = checkBalance();
					} catch (NullPointerException e) {
						result = "Error: Debe crear una cuenta antes de operar";
					}
					break;
				default:
					// If everything is handled properly (and it really should),
					// we will never hit this point.
					throw new RuntimeException("Opción inválida");
			}

			logResult(result);
		}
	}
}
