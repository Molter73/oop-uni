import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String balance, String amount) {
		super("No se puede retirar €" + amount + "\nSaldo disponible: €" + balance);

	}
}

class NegativeBalanceException extends Exception {
	public NegativeBalanceException(String balance) {
		super("No se puede crear cuentas con saldo negativo: €" + balance);
	}
}

class Account {
	private Integer balance;

	public Account(Integer balance) throws NegativeBalanceException {
		if (balance < 0) {
			throw new NegativeBalanceException(amountToString(balance));
		}

		this.balance = balance;
	}

	public Integer getBalance() {
		return balance;
	}

	public void deposit(Integer amount) {
		balance += amount;
	}

	public void withdraw(Integer amount) throws InsufficientBalanceException {
		if (balance < amount) {
			throw new InsufficientBalanceException(toString(), amountToString(amount));
		}

		balance -= amount;
	}

	static public String amountToString(Integer amount) {
		Integer intPart = amount / 100;
		Integer decPart = amount % 100;

		return intPart.toString() + "." + decPart.toString();
	}

	public String toString() {
		return amountToString(balance);
	}
}

enum MenuOption {
	CREATE_EMPTY_ACCOUNT,
	CREATE_ACCOUNT,
	DEPOSIT,
	WITHDRAWAL,
	CHECK_BALANCE,
	EXIT,
}

class ATM {
	private Account account;
	private BufferedReader br;

	public ATM() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

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

	private String createAccount(Integer balance) {
		try {
			account = new Account(balance);
		} catch (NegativeBalanceException e) {
			return "Error: " + e.getMessage();
		}

		return "Creada cuenta con balance €" + account.toString();
	}

	private String deposit(Integer amount) throws NullPointerException {
		account.deposit(amount);

		return "Depósito correcto. Nuevo saldo: €" + account.toString();
	}

	private String withdraw(Integer amount) throws NullPointerException {
		try {
			account.withdraw(amount);
		} catch (InsufficientBalanceException e) {
			return "Error: " + e.getMessage();
		}

		return "Extracción correcta. Nuevo saldo: €" + account.toString();
	}

	private String checkBalance() throws NullPointerException {
		return "Saldo disponible: €" + account.toString();
	}

	private void logResult(String msg) {
		System.out.println("El resultado de la operación es: " + msg);
	}

	private Integer getUserAmount() throws IOException, NumberFormatException {
		System.out.print("Ingrese el monto deseado: €");
		Float rawAmount = Float.parseFloat(br.readLine()) * 100;
		return rawAmount.intValue();
	}

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

public class Main {
	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.run();
	}
}
