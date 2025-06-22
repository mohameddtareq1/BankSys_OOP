package banksystem.account;

import java.io.Serializable;

public abstract class Account implements Serializable {
    public String firstName;
    public String lastName;
    protected long phoneNumber;
    protected String address;

    protected String username;
    private String password;

    public Account(){}
    public Account(String fName, String lName, long pNumber, String address,
                   String username, String password) {
        firstName = fName;
        lastName = lName;
        phoneNumber = pNumber;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }
}