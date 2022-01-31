package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable {

    private String idNumber;
    private LocalTime rentingTime;
    private int rent;

    public Car(String idNumber, int rent) {
        this.idNumber = idNumber;
        this.rent = rent;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * rent;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }
}
