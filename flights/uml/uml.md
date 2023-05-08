## Diagrama de clases
@startuml
class Flight {
    -String origin
    -String destination
    -String departureTime
    -Double price
    +Flight(String origin, String destination, String departureTime, Double price)
    +String getOrigin()
    +String getDestination()
    +String getDepartureTime()
    +Double getPrice()
    +String toString()
}

class Client {
    -Integer code
    +Client(Integer code)
    +Integer getCode()
    +String toString()
}

class Booking {
    -Flight flight
    -Client client
    +Booking(Flight flight, Client client)
    +Flight getFlight()
    +Client getClient()
    +String toString()
}

class App {
    -BufferedReader br
    -Map<Integer, Flight> flights
    -Map<Integer, Client> clients
    -Vector<Booking> bookings
    +App()
    -void initializeFlights()
    -int mainMenu()
    -String flightsToString()
    -String bookingToString()
    -Client getClient()
    -int getFlightNumber()
    -void processBooking(Client client, int flightNumber)
    -void mainLoop()
    +void main()
}

Booking *-- Flight
Booking *-- Client
App *-- "1..n" Flight
App *-- "0..n" Client
App *-- "0..n" Booking
@enduml
