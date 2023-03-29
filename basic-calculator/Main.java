/*
 * Student: Mauro Ezequiel Moltrasio
 *
 * Basic calculator program, taking 2 parameters from the CLI
 * and running the operation selected by the user on them.
*/

import java.util.AbstractMap.SimpleEntry;
import java.util.Scanner;
import java.util.function.Function;

class Main {
	public static void main(String[] args) {
		while (Menu() != MenuOptions.QUIT) {
			SimpleEntry<Integer, Integer> userArgs = TakeArguments();
			Function<SimpleEntry<Integer, Integer>, Integer> operation;

			try {
				operation = GetOperation();
			} catch (RuntimeException e) {
				System.out.println(e);
				continue;
			}

			System.out.println("El resultado de la operación es " + operation.apply(userArgs));
		}

		System.out.println("Have a nice day! :)");
	}

	// Available options for the main menu.
	private enum MenuOptions {
		QUIT,
		OPERATE
	}

	/**
	 * Prints the options for the main menu and prompts the user to choose one.
	 * Translates the selection to a MenuOptions value.
	 */
	private static MenuOptions Menu() {
		System.out.println("1. Realizar operación");
		System.out.println("2-9. Salir");

		int option = input.nextInt();

		if (option == 1) {
			return MenuOptions.OPERATE;
		}
		return MenuOptions.QUIT;
	}

	/**
	 * Prompts the user to input two integers and returns them in a SimpleEntry.
	 * If I was doing this on C++, I would use a std::pair, but alas.
	 */
	private static SimpleEntry<Integer, Integer> TakeArguments() {
		System.out.println("Introduzca el primer argumento");
		int first = input.nextInt();

		System.out.println("Introduzca el segundo argumento");
		int second = input.nextInt();

		return new SimpleEntry<>(first, second);
	}

	/**
	 * Prompts the user to select an operation on a couple of integers.
	 * Returns a callable object that performs the operation.
	 * If the selected operation is invalid, throws an RuntimeException
	 */
	private static Function<SimpleEntry<Integer, Integer>, Integer> GetOperation() {
		System.out.println("1. Sumar");
		System.out.println("2. Restar");
		System.out.println("3. Multiplicar");
		System.out.println("4. Dividir");
		System.out.println("Seleccione una opción");

		switch (input.nextInt()) {
			case 1:
				return (pair) -> pair.getKey() + pair.getValue();
			case 2:
				return (pair) -> pair.getKey() - pair.getValue();
			case 3:
				return (pair) -> pair.getKey() * pair.getValue();
			case 4:
				return (pair) -> pair.getKey() / pair.getValue();
			default:
				throw new RuntimeException("Operación inválida");
		}
	}

	// This scanner is used for reading stdin throughout the program execution
	static final Scanner input = new Scanner(System.in);
}
