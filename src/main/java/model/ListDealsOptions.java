package model;

import java.util.List;
import java.util.Optional;

/**
 * TODO Make fields final so this is immutable
 *
 * */
public class ListDealsOptions {

    public int page;
    public int pageSize;
    public Optional<String> location;
    public Optional<GeoCords> geo;
    public Optional<String> postcode;
    public Optional<List<String>> tags;
    public String sort;
    public String filter;
    public Optional<String> focus;
    public Optional<String> q;

}
