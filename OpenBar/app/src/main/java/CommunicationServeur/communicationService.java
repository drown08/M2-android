package CommunicationServeur;

import android.app.Activity;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Frappereau Olivier on 06/11/2015.
 * Offer the communication-threaded service for any request to the server
 */
public class CommunicationService {
    //private static CommunicationService myInstance = null;
    private AsyncRequestServer mySender;
    private LinkedHashMap params;
    private  String reponse; //Useless avec ma solution mySend.get() (qui reçoit la rep) ???
    private static final String URL_SERVER = "http://10.0.2.2/serveurOpenBar/server.php";

    public CommunicationService(AsyncTaskResponse delegate, Activity activity, Boolean show, int flag) {
        mySender = new AsyncRequestServer(delegate,activity,show,flag);
        params = new LinkedHashMap();
        reponse = "riendutoutinit";
    }

    public void addParams(String key, String value)
    {
         this.params.put(key.toString(), value.toString());
    }

    public String sendToServer() {
        //This is a thread who execute the static method createResponse(rep)
        mySender.execute(buildRequest());
       return createResponse();
    }

    private String buildRequest(){
        String query = URL_SERVER +"?id=10";
        Set set = this.params.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            String cle = me.getKey().toString();
            String valeur = me.getValue().toString();
            query +="&"+cle+"="+valeur;
        }
        return query;
    }

    public String createResponse() {
         return mySender.getMyReponse();
    }


    public void flush() {
        this.params.clear();
        this.reponse = "riendutoutReset";
    }

}
