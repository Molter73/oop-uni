package atm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

enum State {
	CREATE_ACCOUNT,
	DEPOSIT,
	WITHDRAWAL,
	CHECK_BALANCE,
	MAIN_MENU,
	ERROR,
	CONFIRMATION,
}

public class ATM {
	private Account account;
	private State state;
	private Integer amount;
	private String screenMessage;

	/**
	 * Handler used on buttons that should return the app to the main menu.
	 */
	private EventHandler<ActionEvent> handleBackToMain;

	/**
	 * Handler used on buttons that should accept a certain action.
	 */
	private EventHandler<ActionEvent> handleAccept;

	/**
	 * Handler used to place the ATM in the account creation state.
	 */
	private EventHandler<ActionEvent> handleAccountCreation;

	/**
	 * Handler used to place the ATM in the deposit state
	 */
	private EventHandler<ActionEvent> handleDeposit;

	/**
	 * Handler used to place the ATM in the withdrawal state.
	 */
	private EventHandler<ActionEvent> handleWithdrawl;

	/**
	 * Handler used to place the ATM in the check balance state.
	 */
	private EventHandler<ActionEvent> handleBalance;

	/**
	 * Handler used for capturing user input and adding it to the amount for the
	 * transaction.
	 */
	private EventHandler<KeyEvent> handleKeyTyped;

	@FXML
	private HBox atmFrame;

	@FXML
	private Button buttonA;

	@FXML
	private Button buttonB;

	@FXML
	private Button buttonC;

	@FXML
	private Button buttonD;

	@FXML
	private TextArea screen;

	public ATM() {
		handleBackToMain = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainMenu();
			}
		};

		handleAccept = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switch (state) {
					case CREATE_ACCOUNT:
						try {
							account = new Account(amount);
						} catch (NegativeBalanceException e) {
							errorScreen("Error creando cuenta:\n\n" + e.getMessage());
							return;
						}

						confirmationScreen("Se creó una cuenta con € " + Account.amountToString(amount));
						break;
					case DEPOSIT:
						account.deposit(amount);

						confirmationScreen("Se depositaron € " + Account.amountToString(amount));
						break;
					case WITHDRAWAL:
						try {
							account.withdraw(amount);
						} catch (InsufficientBalanceException e) {
							errorScreen("Error en extracción:\n\n" + e.getMessage());
							return;
						}

						confirmationScreen("Se extrajeron € " + Account.amountToString(amount));
						break;
					default:
						// This should be impossible to reach, crash the app otherwise.
						throw new RuntimeException("Received Accept event from unexpected state: " + state);
				}
			}
		};

		handleKeyTyped = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getEventType() != KeyEvent.KEY_TYPED) {
					// Ignore everything that is not a key typed
					return;
				}

				try {
					amount = amount * 10 + Integer.parseInt(event.getCharacter());
				} catch (NumberFormatException e) {
					System.err.println("Error al parsear entero: " + e.getMessage());
					return;
				}

				screen.setText(screenMessage + Account.amountToString(amount));
			}
		};

		handleAccountCreation = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				state = State.CREATE_ACCOUNT;
				amount = 0;

				acceptOrBack("\n\nIngrese el monto inicial de la cuenta\n\n€ ");
			}
		};

		handleDeposit = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (account == null) {
					errorScreen("Error: Cuenta inexistente");
					return;
				}

				state = State.DEPOSIT;
				amount = 0;

				acceptOrBack("\n\nIngrese el monto que desea depositar\n\n€");
			}
		};

		handleWithdrawl = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (account == null) {
					errorScreen("Error: Cuenta inexistente");
					return;
				}

				state = State.WITHDRAWAL;
				amount = 0;

				acceptOrBack("\n\nIngrese el monto que desea retirar\n\n€");
			}
		};

		handleBalance = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (account == null) {
					errorScreen("Error: Cuenta inexistente");
					return;
				}

				state = State.CHECK_BALANCE;

				genericBackScreen("\n\nSu saldo es: € " + account.toString());
			}
		};
	}

	/**
	 * Initialize the ATM state to the main menu.
	 */
	@FXML
	public void initialize() {
		mainMenu();
	}

	/**
	 * Set the ATM to the main menu state.
	 *
	 * This is done by setting the text displayed on the main screen and setting the
	 * buttons to handle a specific action each.
	 */
	private void mainMenu() {
		state = State.MAIN_MENU;

		atmFrame.setOnKeyTyped(null);

		screen.setText("Elija un opción");

		buttonA.setOnAction(handleAccountCreation);
		buttonA.setText("Crear cuenta");

		buttonB.setOnAction(handleDeposit);
		buttonB.setText("Realizar depósito");

		buttonC.setOnAction(handleWithdrawl);
		buttonC.setText("Realizar retirada");

		buttonD.setOnAction(handleBalance);
		buttonD.setText("Ver saldo");
	}

	/**
	 * Set the ATM to display a generic screen with only 2 buttons enabled, one
	 * accepts the action, the other one goes back to the main menu.
	 *
	 * @param msg The message to display in the ATM screen.
	 */
	private void acceptOrBack(String msg) {
		atmFrame.setOnKeyTyped(handleKeyTyped);

		screenMessage = msg;
		screen.setText(msg);

		buttonA.setOnAction(null);
		buttonA.setText("");

		buttonB.setOnAction(null);
		buttonB.setText("");

		buttonC.setOnAction(handleAccept);
		buttonC.setText("Aceptar");

		buttonD.setOnAction(handleBackToMain);
		buttonD.setText("Volver al menú");
	}

	/**
	 * Set the ATM to a generic screen with a single button enabled.
	 *
	 * @param msg The message to display in the ATM screen.
	 */
	private void genericBackScreen(String msg) {
		atmFrame.setOnKeyTyped(null);

		screen.setText(msg);

		buttonA.setOnAction(null);
		buttonA.setText("");

		buttonB.setOnAction(null);
		buttonB.setText("");

		buttonC.setOnAction(null);
		buttonC.setText("");

		buttonD.setOnAction(handleBackToMain);
		buttonD.setText("Volver");
	}

	/**
	 * Display an error screen.
	 *
	 * @param msg The message to display in the ATM screen.
	 */
	private void errorScreen(String msg) {
		state = State.ERROR;

		genericBackScreen(msg);
	}

	/**
	 * Display a screen confirming the result of the last transaction.
	 *
	 * @param msg The message to display in the ATM screen.
	 */
	private void confirmationScreen(String msg) {
		state = State.CONFIRMATION;

		genericBackScreen(msg);
	}
}
