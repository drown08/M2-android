package CommunicationServeur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Frappereau Olivier on 06/11/2015.
 */
public class ConnectionServer {
    private URL url;
    private HttpURLConnection connexion;

    public ConnectionServer() {

    }

    public void setUrl(String urlBuilded) {
        try {
            this.url = new URL(urlBuilded);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String post() {
        String input = "rien";
        try {
            this.connexion = (HttpURLConnection)this.url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(this.connexion.getInputStream()));
            String aux = "";
            StringBuilder builder = new StringBuilder();
            while ((aux = br.readLine()) != null) {
                builder.append(aux);
            }

            input = builder.toString();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

    public void close()
    {
        if (this.connexion != null)
        {
            this.connexion.disconnect();
        }
    }

}
