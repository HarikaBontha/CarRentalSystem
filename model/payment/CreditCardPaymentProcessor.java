package model.payment;

public class CreditCardPaymentProcessor implements PaymentProcessor{
    public boolean processPayment(double amount){
        System.out.println("Amount: "+amount+" Payment succefully completed through credit card");
        return true;
    }
    
}
