package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {

    private String idNumber;
    private LocalTime rentingTime;
    public static final int RENT = 15;

    public Bike(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * RENT;
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
