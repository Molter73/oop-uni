package flights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class App {
	private BufferedReader br;
	private Map<Integer, Client> clients;
	private Map<Integer, Flight> flights;
	private Vector<Booking> bookings;

	public App() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		clients = new HashMap<>();
		flights = new HashMap<>();
		bookings = new Vector<>();
	}

	private void initializeFlights() {
		flights.put(0, new Flight("Madrid", "Barcelona", "9:00", new Double(157)));
		flights.put(1, new Flight("Barcelona", "Madrid", "12:30", new Double(175)));
		flights.put(2, new Flight("Madrid", "Barcelona", "18:00", new Double(162)));
	}

	private int mainMenu() throws NumberFormatException, IOException {
		System.out.println("[1] Mostrar catálogo de vuelos.");
		System.out.println("[2] Realizar reserva.");
		System.out.println("[3] Listado de reservas.");
		System.out.println("[0] Salir.");
		System.out.print("Elige una opcion: ");
		return Integer.parseInt(br.readLine());
	}

	private String flightsToString() {
		String output = "----------------- Vuelos -----------------\n";

		for (Map.Entry<Integer, Flight> entry : flights.entrySet()) {
			output += (entry.getKey() + 1) + ") ";
			output += entry.getValue().toString() + "\n";
		}

		output += "------------------------------------------\n";
		return output;
	}

	private String bookingsToString() {
		String output = "----------------- Reservas -----------------\n";

		for (Booking booking : bookings) {
			output += booking.toString() + "\n";
		}

		output += "--------------------------------------------\n";
		return output;
	}

	private Client getClient() throws IOException {
		System.out.print("Codigo cliente: ");
		Integer code = Integer.parseInt(br.readLine());

		if (!clients.containsKey(code)) {
			System.out.println("Agregando nuevo cliente...");
			clients.put(code, new Client(code));
		}

		return clients.get(code);
	}

	private int getFlightNumber() throws IOException {
		System.out.print("Numero Vuelo: ");
		return Integer.parseInt(br.readLine()) - 1;
	}

	private void processBooking(Client client, int flightNumber) {
		if (!flights.containsKey(flightNumber)) {
			System.out.println("No se encuentra el vuelo Nº " + flightNumber);
			return;
		}

		bookings.addElement(new Booking(flights.get(flightNumber), client));
	}

	private void mainLoop() throws IOException {
		boolean running = true;

		while (running) {
			switch (mainMenu()) {
				case 1:
					System.out.println(flightsToString());
					break;
				case 2:
					Client client = getClient();
					int flightNumber = getFlightNumber();
					processBooking(client, flightNumber);
					break;
				case 3:
					System.out.println(bookingsToString());
					break;
				default:
					running = false;
			}
		}
	}

	public static void main(String args[]) throws IOException {
		App app = new App();
		app.initializeFlights();
		app.mainLoop();
	}
}
