package CommunicationServeur;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Frappereau Olivier on 06/11/2015.
 */
public class AsyncRequestServer extends AsyncTask<String,Void,String> {

    private AsyncTaskResponse delegate = null;
    private Activity myActivity;
    private ConnectionServer myServer;
    private String myReponse;
    private ProgressDialog tmp;
    private boolean showProgressDialog;
    private int flag;

    public AsyncRequestServer(AsyncTaskResponse delegate, Activity activity, Boolean show,int flag) {
        myServer = new ConnectionServer();
        this.myReponse="";
        this.myActivity = activity;
        this.delegate = delegate;
        this.showProgressDialog = show;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        if(showProgressDialog) {
            this.tmp = new ProgressDialog(this.myActivity);
            this.tmp.setMessage("Wait..");
            this.tmp.setCancelable(false);
            this.tmp.setIndeterminate(false);
            this.tmp.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.tmp.show();
        }

       // setProgressBarIndeterminateVisibilty(true);
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
        if(this.showProgressDialog){
            this.tmp.dismiss();
        }
        Log.d("returnRepForFlag",String.valueOf(flag)+"-"+reponse);
        //if(delegate!=null)
            delegate.processFinish(reponse,flag);
        //delegate.processFinish(reponse);
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
