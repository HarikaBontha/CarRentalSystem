package model;

public class Customer {
    private String name;
    private String contactinfo;
    private String licenceNumber;
    public Customer(String name,String contactinfo,String licenceNumber){
        this.name=name;
        this.contactinfo=contactinfo;
        this.licenceNumber=licenceNumber;
    }
    public String getName(){
        return this.name;
    }
    public String getContactInfo(){
        return this.contactinfo;
    }
    public String licenceNumber(){
        return this.licenceNumber;
    }
    
}
