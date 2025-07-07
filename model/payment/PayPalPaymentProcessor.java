package model.payment;

public class PayPalPaymentProcessor implements PaymentProcessor {
    public boolean processPayment(double amount){
        System.out.println("Amount:"+amount+"payed through paypal account");
        return true;
    }
}
