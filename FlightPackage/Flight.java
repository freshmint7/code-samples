package ensf480.ucalgary.group12.FlightPackage;

import java.util.Date;

import ensf480.ucalgary.group12.AircraftPackage.Aircraft;
import ensf480.ucalgary.group12.CustomerPackage.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "Flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FlightID")
    private int flightID;
    
    @Column(name = "DepartureAirport")
    private String departureAirport;
    @Column(name = "ArrivalAirport")
    private String arrivalAirport;
    @ManyToOne
    @JoinColumn(name = "AircraftID")
    private Aircraft plane;
    @Column(name = "ArrivalTime")
    private Date dateOfArrival;
    @Column(name = "DepartureTime")
    private Date dateOfDeparture;
    @Column(name = "DepartureCity")
    private String departureCity;
    @Column(name = "ArrivalCity")
    private String arrivalCity;

    public Flight() {
    }

    public Flight(String departureAirport, String arrivalAirport, Date dateOfArrival, Date dateOfDeparture,
            String departureCity, String arrivalCity) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }
    
    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Aircraft getPlane() {
        return plane;
    }

    public void setPlane(Aircraft plane) {
        this.plane = plane;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
