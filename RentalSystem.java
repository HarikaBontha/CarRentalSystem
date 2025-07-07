import model.payment.PaymentProcessor;
import model.payment.CreditCardPaymentProcessor;
import model.Reservation;
import model.Customer;
import model.Car;
import java.util.Map;
import java.util.HashMap;
import java.util.*;
import java.util.UUID;
import java.time.LocalDate;
public class RentalSystem {
    private Map<String,Car> cars;
    private Map<String,Reservation> reservations;
    private PaymentProcessor paymentProcessor;
    public RentalSystem(){
        cars=new HashMap<>();
        reservations =new HashMap<>();
        paymentProcessor =new CreditCardPaymentProcessor();

    }
    public void addCar(Car car){
        cars.put(car.getLicencePlate(),car);
        System.out.println("Car added sucessfully");
    }
    public List<Car> searchCars(String maker,String model,LocalDate startDate,LocalDate endDate){
        List<Car> availableCars=new ArrayList<>();
        for(Car car:cars.values()){
            if(car.getMaker().equals(maker) && car.getModel().equals(model) && car.getIsAvailable()){
                if(isCarAvailable(car,startDate,endDate)){
                    availableCars.add(car);
                }
            }
        }
        return availableCars;
    }
    public boolean isCarAvailable(Car car,LocalDate startDate,LocalDate endDate){
        for(Reservation reservation:reservations.values()){
            if(reservation.getCar().equals(car)){
                if(startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())){
                    return false;
                }
            }
        }
        return true;
    }
    public Reservation makeReservation(Customer customer,Car car,LocalDate startDate,LocalDate endDate)
    {
        if(isCarAvailable(car,startDate,endDate)){
            String reservationId=generatereservationId();
            Reservation reservation=new Reservation(reservationId,car,customer,startDate,endDate,1500.0);
            reservations.put(reservationId,reservation);
            car.setAvailable(false);
            return reservation;

        }
        return null;
    }   
    public  void cancelReservation(String reservationId){
    Reservation reservation=reservations.remove(reservationId);
    
    reservation.getCar().setAvailable(true);
    } 
    public void removeCar(String licencePlate){
        cars.remove(licencePlate);
    }
    private String generatereservationId(){
        return "RES"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
    
    public boolean processorPayment(Reservation reservation){
        return  paymentProcessor.processPayment(reservation.getTotalPrice());
    }
}
