package ensf480.ucalgary.group12.FlightPackage;

import java.util.Date;
import ensf480.ucalgary.group12.Seat.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    public Flight findByFlightID(int id);
    public List<Flight> findByArrivalCity(String id);
    public List<Flight> findByArrivalAirport(String id);
    public List<Flight> findByDepartureCity(String id);
    public List<Flight> findByDepartureAirport(String id);
    public List<Flight> findByDateOfDepartureAfter(Date departureTime);
}
