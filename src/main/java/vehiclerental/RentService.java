package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    private Set<User> users = new HashSet<>();
    private Set<Rentable> rentables = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();
    public static final int MAX_RENTING_MINUTES = 180;

    public void registerUser(User user) {
        if (!users.add(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
    }

    public void addRentable(Rentable vehicle) {
        rentables.add(vehicle);
    }

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    public Set<Rentable> getRentables() {
        return new HashSet<>(rentables);
    }

    public Map<Rentable, User> getActualRenting() {
        return new TreeMap<>(actualRenting);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        if (rentable.getRentingTime() != null) {
            throw new IllegalStateException("Vehicle is rented.");
        }
        if (rentable.calculateSumPrice(MAX_RENTING_MINUTES) > user.getBalance()) {
            throw new IllegalStateException("Not enough balance.");
        } else {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        }
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (actualRenting.containsKey(rentable)) {
            User user = actualRenting.get(rentable);
            user.minusBalance(rentable.calculateSumPrice(minutes));
            actualRenting.remove(rentable, user);
            rentable.closeRent();
        } else {
            throw new IllegalStateException("Vehicle is not in rent.");
        }
    }
}
