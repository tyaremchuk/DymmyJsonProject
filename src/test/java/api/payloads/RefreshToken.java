package api.payloads;

public class RefreshToken
{
    String refreshToken;
    //int expiresInMins;

    public RefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    /*public RefreshToken(String refreshToken, int expiresInMins)
    {
        this.refreshToken = refreshToken;
        this.expiresInMins = expiresInMins;
    }*/

    /*public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }*/

}
