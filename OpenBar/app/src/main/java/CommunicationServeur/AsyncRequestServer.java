package CommunicationServeur;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Frappereau Olivier on 06/11/2015.
 */
public class AsyncRequestServer extends AsyncTask<String,Void,String> {

    private AsyncTaskResponse delegate = null;
    private ConnectionServer myServer;
    private String myReponse;

    public AsyncRequestServer(AsyncTaskResponse delegate) {
        myServer = new ConnectionServer();
        this.myReponse="";
        this.delegate = delegate;
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
        delegate.processFinish(reponse);
        //setMyReponse(reponse);
    }

    private void setMyReponse(String rep) {
        Log.d("rtnRepSetMyReponse()", rep);
        this.myReponse = rep;
    }

    public String getMyReponse() {
        Log.d("rtnRepgetMyReponse()", myReponse);
        return this.myReponse;
    }
}
