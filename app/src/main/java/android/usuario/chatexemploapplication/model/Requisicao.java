package android.usuario.chatexemploapplication.model;

/**
 * Created by brunodelhferreira on 19/03/17.
 */

public class Requisicao {

    private String erro;
    private String msg;
    private String userId;


    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
