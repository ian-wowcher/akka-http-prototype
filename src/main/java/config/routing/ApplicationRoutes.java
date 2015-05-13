package config.routing;

public enum ApplicationRoutes {

    HELLO_WORLD("/hello");

    private String uri;

    ApplicationRoutes(String uri) {
        this.uri = uri;
    }

    public String uri() {
        return uri;
    }
}
