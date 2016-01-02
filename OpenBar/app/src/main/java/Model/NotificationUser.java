package Model;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class NotificationUser {
    private String name;
    private String object;
    private String typeAction;
    private int num;

    public NotificationUser(String name, String object, String typeAction, int num) {
        this.name=name;
        this.object=object;
        this.typeAction=typeAction;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getObject() {
        return object;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }
}
