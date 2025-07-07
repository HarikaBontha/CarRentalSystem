import model.*;
import java.util.*;
import java.time.LocalDate;
public class App {
    public static void main(String[] args) throws Exception {
        RentalSystem rentalSystem=new RentalSystem();

        Car car1=new Car("Toyota","Glanza",2022,"ABCD1234",600,true);
        Car car2=new Car("Honda","Civic",2021,"DEFG5678",700,true);
        Car car3=new Car("Ford","Mustang",2023,"HIJK8973",1200,true);


        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        Customer customer=new Customer("Virat","9013465783","DL1234");

        LocalDate startDate=LocalDate.now();
        LocalDate endDate=startDate.plusDays(3);

        List<Car> availableCars=rentalSystem.searchCars("Toyota","Glanza",startDate,endDate);

        if(!availableCars.isEmpty()){
            Car selectedcar=availableCars.get(0);
            Reservation reservation=rentalSystem.makeReservation(customer,selectedcar,startDate,endDate);
            if(reservation!=null){
                boolean isPaymentSucessful =rentalSystem.processorPayment(reservation);
                if(isPaymentSucessful==true){
                    System.out.println("Reservations sucessful with Reservation Id:"+reservation.getReservationId());

                }else{
                    System.out.println("payment failed.Reservation cancelled"+reservation.getReservationId());
                    rentalSystem.cancelReservation(reservation.getReservationId());
                }
            }else{
                System.out.println("Selected Car not available for given Dates");
            }
        }else{
            System.out.println("No car available with given specification");
        }
    }

}
