package api.payloads;

public class UsersPayloads
{
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

    public int getExpiresInMins() {
        return expiresInMins;
    }

    public void setExpiresInMins(int expiresInMins) {
        this.expiresInMins = expiresInMins;
    }

    String username;
    String password;
    int expiresInMins;

    public UsersPayloads(){}

    public UsersPayloads(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public UsersPayloads(String username, String password, int expiresInMins)
    {
        this.username = username;
        this.password = password;
        this.expiresInMins = expiresInMins;
    }
}
