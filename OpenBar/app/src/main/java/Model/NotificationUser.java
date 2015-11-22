package Model;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class NotificationUser {
    private String name;
    private String object;
    private String typeAction;

    public NotificationUser(String name, String object, String typeAction) {
        this.name=name;
        this.object=object;
        this.typeAction=typeAction;
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
