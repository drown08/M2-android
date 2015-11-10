package CommunicationServeur;

/**
 * Created by Frappereau Olivier on 10/11/2015.
 */
public interface AsyncTaskResponse {
    //Permet de réccuperer la réponse "asynchrone"
    void processFinish(String output);
}
