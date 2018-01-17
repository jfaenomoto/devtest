import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CarAdvertiser {

    private List<Car> offers;

    public CarAdvertiser() {
        this.offers = new ArrayList<>();
    }

    public void addOffer(Car car) {
        this.offers.add(car);
    }

    public List<Car> offersFor(Customer customer) {
        List<Car> offersForCustomer = new ArrayList<>();
        for (Car car : this.offers) {
            if (customer.isEligibleTo(car)) {
                offersForCustomer.add(car);
            }
        }
        return offersForCustomer;
    }

    public Car popularOfferFor(List<Customer> customers) {
        int eligibleCustomers = 0;
        Car popularOffer = null;
        for (Car car : this.offers) {
            int eligibleForCar = car.eligiblesFrom(customers).size();
            if (eligibleForCar > eligibleCustomers) {
                popularOffer = car;
                eligibleCustomers = eligibleForCar;
            }
        }
        if (popularOffer == null) {
            throw new NoOfferAvailableException();
        }
        return popularOffer;
    }
}

class Car {

    String name;

    Map<String, Integer> requirements;

    public Car(String name, Map<String, Integer> requirements) {
        this.name = name;
        this.requirements = requirements;
    }
    
    public List<Customer> eligiblesFrom(List<Customer> customers) {
        List<Customer> eligibles = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.isEligibleTo(this)) {
                eligibles.add(customer);
            }
        }
        return eligibles;
    }

}

class Customer {

    String name;

    Map<String, Integer> information;

    public Customer(String name, Map<String, Integer> information) {
        this.name = name;
        this.information = information;
    }

    public boolean isEligibleTo(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car can't be null on Customer.isEligibleTo()");
        }
        for (Entry<String, Integer> requirement : car.requirements.entrySet()) {
            if (!this.information.containsKey(requirement.getKey())) {
                return false;
            }
            if (this.information.get(requirement.getKey()) < requirement.getValue()) {
                return false;
            }
        }
        return true;
    }

}

class NoOfferAvailableException extends RuntimeException {
    
}