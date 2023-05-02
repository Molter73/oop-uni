package flights;

public class Booking {
	private Flight flight;
	private Client client;

	public Booking(Flight flight, Client client) {
		this.flight = flight;
		this.client = client;
	}

	public Flight getFlight() {
		return flight;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public String toString() {
		return "Booking [flight=" + flight + ", client=" + client + "]";
	}
}
