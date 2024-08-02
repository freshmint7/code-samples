package ensf480.ucalgary.group12.FlightPackage;

import java.time.LocalDateTime;
import java.util.Date;
import ensf480.ucalgary.group12.AircraftPackage.AircraftService;
import ensf480.ucalgary.group12.AircraftPackage.Aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping(path = "/flights")
@CrossOrigin(origins = "*")
public class FlightController {
    private final FlightService service;
    private final AircraftService airService;
    
    @Autowired
    public FlightController(FlightService flightService, AircraftService airService){
        this.service = flightService;
        this.airService = airService;
    }
    
    @GetMapping("/getFlights")
    public List<Flight> getFlights(){
       return service.getAllFlights();
    }
    
    @GetMapping("/getFlightArrive/{id}")
    public List<Flight> getFlightA(@PathVariable("id") String id){
        return service.getFlightA(id);
    }
    
    @GetMapping("/getFlightDest/{id}")
    public List<Flight> getFlightD(@PathVariable("id") String id){
        return service.getFlightD(id);
    }
    
    @GetMapping("/getFlightArriveCode/{id}")
    public List<Flight> getFlightAC(@PathVariable("id") String id){
        return service.getFlightAC(id);
    }
    
    @GetMapping("/getFlightDestCode/{id}")
    public List<Flight> getFlightDC(@PathVariable("id") String id){
        return service.getFlightDC(id);
    }
    
    @GetMapping("getFlightByDep/{date}")
    public List<Flight> getFlightDepTime(@PathVariable String date) {
        LocalDateTime departureTime = LocalDateTime.parse(date);
        Date departureDate = java.sql.Timestamp.valueOf(departureTime);
        return service.getFlightByDepTime(departureDate);
    }
    
    @PostMapping("/addFlight")
    public Flight addFlight(@RequestBody Flight flight){
        System.out.println(flight.getPlane().getAircraftName());
        Aircraft exists = airService.getAircraftByName(flight.getPlane().getAircraftName());
        
        if (exists != null){
            flight.setPlane(exists);
        }
        else{
            airService.saveAircraft(flight.getPlane());
        }
        return service.addFlight(flight);
    }
    
    @DeleteMapping("/deleteFlight/{id}")
    public String deleteFlight(@PathVariable("id") int id){
        return service.deleteFlight(id);
    }
    
    @PutMapping("/updateFlight")
    public Flight updateFlight(@RequestBody Flight flight){
        return service.updateFlight(flight);
    }
}
