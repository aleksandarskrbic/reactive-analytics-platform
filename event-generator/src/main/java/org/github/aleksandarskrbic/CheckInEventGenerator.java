package org.github.aleksandarskrbic;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckInEventGenerator {

    private Random random = new Random();

    public CheckInEvent randomEvent() {
        return new CheckInEvent((long) random.nextInt(100), device());
    }

    private String device() {
        return random.nextBoolean() ? "PHONE" : "LAPTOP";
    }
}
