package CommunicationServeur;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Frappereau Olivier on 06/11/2015.
 * Offer the communication-threaded service for any request to the server
 */
public class CommunicationService {
    private static CommunicationService myInstance = null;
    private AsyncRequestServer mySender;
    private LinkedHashMap<String,String> params;
    private static String reponse; //Useless avec ma solution mySend.get() (qui reçoit la rep) ???
    private static final String URL_SERVER = "http://127.0.0.1/serveurOpenBar/server.php";
    private static boolean onCharge;

    private CommunicationService() {
        mySender = new AsyncRequestServer();
        params = new LinkedHashMap<>();
        reponse = "riendutoutinit";
    }

    public static CommunicationService getInstance() {
        if(myInstance==null) {
            return new CommunicationService();
        }
        return myInstance;
    }

    public void addParams(String key, String value) {
        this.params.put(key, value);
    }

    public String sendToServer() {
        //This is a thread who execute the static method createResponse(rep)
        //mySender.execute(buildRequest());
        //mySender.execute("http://www.google.com");
        mySender.execute("http://10.0.2.2/serveurOpenBar/server.php");
        String result = "";
        try {
            result = mySender.get(); //WOW ? Remplace la méthode onPostResume() du thread mySender ???
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String buildRequest(){
        String query = URL_SERVER +"?id=0";

        for(Map.Entry<String, String> entry : this.params.entrySet())
        {
            String cle = entry.getKey();
            String valeur = entry.getValue();

            query +="&"+cle+"="+valeur;
            //System.out.println(valeur);
        }
        return query;
    }

    public static void createResponse(String rep) {
        //Inutile d'utiliser cet attribut avec la solution
            reponse = rep;
    }

    public String getReponse() {
        return reponse;
    }

    public void flush() {
        this.params.clear();
    }

}
