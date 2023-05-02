package flights;

public class Flight {
	private String origin;
	private String destination;
	private String departureTime;
	private Double price;

	public Flight(String origin, String destination, String departureTime, Double price) {
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.price = price;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", departureTime=" + departureTime
				+ ", price=" + price + "]";
	}
}
