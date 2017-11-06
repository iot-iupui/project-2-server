package org.iupui.iot.project2.server;

public class HelloPojo {
    String greeting;

    public HelloPojo(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "HelloPojo{" +
                "greeting='" + greeting + '\'' +
                '}';
    }
}
