package hello.world;

public class GreetingPojo {
    private String greeting;

    public GreetingPojo(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "{" +
                "greeting:'" + greeting + '\'' +
                '}';
    }
}
