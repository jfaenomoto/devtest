import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * A car company is using some data gathered from multiple sources to offer its
 * products to customers. To optimize advertising budget, this customer data is
 * being used to direct which car offers one is able to see.
 * 
 * A customer has a name and a collection of key/value properties which maps its
 * own profile. Not every customer has the same key properties as other
 * customers, due to the fact that not all information regarding someone is
 * equally available.
 * 
 * A car has a name and a collection of key/value properties which maps the
 * lower limit for it to be offered to a customer, which means that for a
 * customer to be eligible for that product he/she must have all requirements
 * for that product and their values must be equal or greater than product's
 * limit.
 * 
 * Both customer profile info and product limits may be represented as string to
 * integer maps.
 * 
 * An automated advertisor is being developed to answer given some car offers
 * which ones are available to a customer and given offers and a list of
 * customers which offer is the most popular (i.e. most people are eligible to).
 * If two or more cars are tied for the most popular, any will do. If no offer
 * has eligible customers, then an error must be shown to the user.
 */
public class CarAdvertiserTest {

    private CarAdvertiser advertiser;

    @Test
    public void findAnOffer() {
        Car car1 = new Car(
                "Fiat 147",
                new HashMap<String, Integer>() {{ put("age", 50); }});
        Car car2 = new Car(
                "Fusca",
                new HashMap<String, Integer>() {{ put("age", 60); }});
        this.advertiser.addOffer(car1);
        this.advertiser.addOffer(car2);

        Customer customer = new Customer(
                "Antonio",
                new HashMap<String, Integer>() {{ put("age", 55); put("siblings", 2); }});

        List<Car> offers = this.advertiser.offersFor(customer);
        assertTrue(offers.contains(car1));
    }

    @Test
    public void missOfferForLackOfOffer() {
        Customer customer = new Customer(
                "Maria",
                new HashMap<String, Integer>() {{ put("friends", 42); }});

        List<Car> offers = this.advertiser.offersFor(customer);
        assertTrue(offers.isEmpty());
    }

    @Test
    public void missOfferForLackOfCustomerData() {
        Car car = new Car(
                "Kadett",
                new HashMap<String, Integer>() {{ put("searches for car", 5); }});
        this.advertiser.addOffer(car);

        Customer customer = new Customer(
                "Joseph",
                new HashMap<String, Integer>() {{ put("fines", 0); }});

        List<Car> offers = this.advertiser.offersFor(customer);
        assertTrue(!offers.contains(car));
    }

    @Test
    public void missOfferForMinimumRequirement() {
        Car car = new Car(
                "Opala",
                new HashMap<String, Integer>() {{ put("t-shirts", 2); put("distance to work", 8); }});
        this.advertiser.addOffer(car);

        Customer customer = new Customer(
                "Britney",
                new HashMap<String, Integer>() {{ put("t-shirts", 1); put("distance to work", 10); }});

        List<Car> offers = this.advertiser.offersFor(customer);
        assertTrue(!offers.contains(car));
    }

    @Test
    public void findMoreThanOneOffer() {
        Car car1 = new Car(
                "Ferrari",
                new HashMap<String, Integer>() {{ put("earnings", 10000); }});
        Car car2 = new Car(
                "McLaren",
                new HashMap<String, Integer>() {{ put("owned boats", 2); put("earnings", 5000); }});
        this.advertiser.addOffer(car1);
        this.advertiser.addOffer(car2);

        Customer customer = new Customer(
                "Lawrence",
                new HashMap<String, Integer>() {{ put("earnings", 12000); put("owned boats", 2); }});

        List<Car> offers = this.advertiser.offersFor(customer);
        assertTrue(offers.contains(car1));
        assertTrue(offers.contains(car2));
    }
    
    @Test
    public void findPopularOffer() {
        Car car1 = new Car(
                "Porsche",
                new HashMap<String, Integer>() {{ put("times in media", 10); }});
        Car car2 = new Car(
                "Lamborghini",
                new HashMap<String, Integer>() {{ put("jobs", 3); }});
        Car car3 = new Car(
                "Ferrari",
                new HashMap<String, Integer>() {{ put("jobs", 5); }});
        this.advertiser.addOffer(car1);
        this.advertiser.addOffer(car2);
        this.advertiser.addOffer(car3);

        Customer customer1 = new Customer(
                "Jeffrey",
                new HashMap<String, Integer>() {{ put("jobs", 3); put("times in media", 10); }});
        Customer customer2 = new Customer(
                "Larry",
                new HashMap<String, Integer>() {{ put("jobs", 5); put("times in media", 7); }});
        List<Customer> customers = Arrays.asList(customer1, customer2);

        Car popularOffer = this.advertiser.popularOfferFor(customers);
        assertEquals(car2, popularOffer);
    }
    
    @Test
    public void findTiedPopularOffer() {
        Car car1 = new Car(
                "Volkswagen",
                new HashMap<String, Integer>() {{ put("height", 180); }});
        Car car2 = new Car(
                "Audi",
                new HashMap<String, Integer>() {{ put("weight", 100); }});
        Car car3 = new Car(
                "GM",
                new HashMap<String, Integer>() {{ put("weight", 80); }});
        this.advertiser.addOffer(car1);
        this.advertiser.addOffer(car2);
        this.advertiser.addOffer(car3);

        Customer customer1 = new Customer(
                "Bella",
                new HashMap<String, Integer>() {{ put("height", 190); put("weight", 70); }});
        Customer customer2 = new Customer(
                "Gil",
                new HashMap<String, Integer>() {{ put("height", 150); put("weight", 95); }});
        List<Customer> customers = Arrays.asList(customer1, customer2);

        Car popularOffer = this.advertiser.popularOfferFor(customers);
        assertTrue(popularOffer.equals(car1) || popularOffer.equals(car3));
    }
    
    @Test(expected = NoOfferAvailableException.class)
    public void noPopularOffer() {
        Car car1 = new Car(
                "Civic",
                new HashMap<String, Integer>() {{ put("gold medals", 2); }});
        Car car2 = new Car(
                "Fit",
                new HashMap<String, Integer>() {{ put("hours playing GTA", 100); }});
        this.advertiser.addOffer(car1);
        this.advertiser.addOffer(car2);

        Customer customer = new Customer(
                "Manfield",
                new HashMap<String, Integer>() {{ put("gold medals", 0); put("hours playing GTA", 50); }});
        List<Customer> customers = Arrays.asList(customer);

        this.advertiser.popularOfferFor(customers);
    }
    
    @Test(expected = NoOfferAvailableException.class)
    public void noPopularOfferWhenNoOffers() {
        Customer customer = new Customer(
                "Heather",
                new HashMap<String, Integer>() {{ put("races attended", 2); }});
        List<Customer> customers = Arrays.asList(customer);

        this.advertiser.popularOfferFor(customers);
    }

    @Before
    public void setup() {
        this.advertiser = new CarAdvertiser();
    }
}
