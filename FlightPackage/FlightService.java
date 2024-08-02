package ensf480.ucalgary.group12.FlightPackage;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository repo;
    
    @Autowired
    public FlightService(FlightRepository flightRepo){
        this.repo = flightRepo;
    }
    
    public List<Flight> getAllFlights(){
        return repo.findAll();
    }

    public Flight addFlight(Flight flight){
        return repo.save(flight);
    }
    
    public List<Flight> getFlightA(String arrivalCity){
        return repo.findByArrivalCity(arrivalCity);
    }

    public List<Flight> getFlightD(String departureCity){
        return repo.findByDepartureCity(departureCity);
    }

    public List<Flight> getFlightAC(String arrivalAirport){
        return repo.findByArrivalAirport(arrivalAirport);
    }

    public List<Flight> getFlightDC(String departureAirport){
        return repo.findByDepartureAirport(departureAirport);
    }
    
    public Flight getFlight(int id){
        return repo.findById(id).orElse(null);
    }

    public List<Flight> getFlightByDepTime(Date date){
        return repo.findByDateOfDepartureAfter(date);
    }

    public String deleteFlight(int id){
        try{
            repo.deleteById(id);
            return "Flight with ID " + id + " was removed";
        }
        catch(Exception e){
            return "Flight with ID " + id + " not found";
        }
    }

    public Flight updateFlight(Flight flight){
        Flight existing = repo.findById(flight.getFlightID()).orElse(null);
        if (existing != null) {
            existing.setDateOfArrival(flight.getDateOfArrival());
            existing.setDateOfDeparture(flight.getDateOfDeparture());
            existing.setArrivalCity(flight.getArrivalCity());
            existing.setDepartureAirport(flight.getDepartureAirport());
            existing.setArrivalAirport(flight.getArrivalAirport());
            existing.setDepartureCity(flight.getDepartureCity());
            return repo.save(existing);
        } else {
            return null;
        }
    }
}



