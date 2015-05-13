package model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// TODO make immutable
public class Deal {

    public long id = 1L;
    public String closeTime = "23-12-17";
    public String product = "product";
    public String title = "title";
    public String description = "description";
    public DealPrices prices;
    public String businessName = "business name";
    public String categoryId = "123";
    public String scheduledLocationName = "schedule location name";
    public Optional<String> finePrint;
    public List<String> highlights = Arrays.asList(new String[]{"highlight one"});
    public String emailSubject = "email subject";
    public int purchaseCount = 1;
    public int soldOrLeftCount = 12;
    public int minLiveDeals = 1;
    public String soldOrLeftText;
    public List<Address> addresses;
    public List<Location> locations;
    public ImageLinks imageLinks;
    public DealConditions conditions;


    public SimpleDeal toSimpleDeal() {
        return new SimpleDeal(
                this.id,
                this. title,
                this. product,
                this. locations);
    }

    public Deal(String product, String title, String description, String businessName) {
        this.product = product;
        this.title = title;
        this.description = description;
        this.businessName = businessName;
    }
}