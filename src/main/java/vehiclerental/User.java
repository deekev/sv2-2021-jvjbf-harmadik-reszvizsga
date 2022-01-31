package vehiclerental;

import java.util.Objects;

public class User {

    private String userName;
    private String email;
    private int balance;

    public User(String userName, String email, int balance) {
        this.userName = userName;
        this.email = email;
        this.balance = balance;
    }

    public void minusBalance(int amount) {
        balance -= amount;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
