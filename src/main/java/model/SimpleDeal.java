package model;

import java.util.List;

public class SimpleDeal {

    public final Long id;
    public final String title;
    public final String product;
    public final List<Location> locations;

    public SimpleDeal(Long id, String title, String product, List<Location> locations) {
        this.id = id;
        this.title = title;
        this.product = product;
        this.locations = locations;
    }
}
