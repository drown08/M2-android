package CommunicationServeur;

import android.os.AsyncTask;

import static CommunicationServeur.CommunicationService.createResponse;

/**
 * Created by Frappereau Olivier on 06/11/2015.
 */
public class AsyncRequestServer extends AsyncTask<String,Void,String> {

    private ConnectionServer myServer;

    public AsyncRequestServer() {
        myServer = new ConnectionServer();
    }

    @Override
    protected String doInBackground(String... params) {
        myServer.setUrl(params[0]);
        String rep = myServer.post();
        myServer.close();
        return rep;
    }

    @Override
    protected void onPostExecute(String reponse) {
        //Static method of CommunicationService; n'est pas appellée grâce à ma solution ??
        createResponse(reponse);
    }
}
