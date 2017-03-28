package android.usuario.chatexemploapplication.model;

import java.io.Serializable;

/**
 * Created by User on 18/03/2017.
 */

@SuppressWarnings("serial")
public class Mensagem implements Serializable {
    private String userIdOrigem;
    private String userIdDestino;
    private String msgEnviada;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMsgEnviada() {
        return msgEnviada;
    }

    public void setMsgEnviada(String msgEnviada) {
        this.msgEnviada = msgEnviada;
    }

    public String getUserIdOrigem() {
        return userIdOrigem;
    }

    public void setUserIdOrigem(String userIdOrigem) {
        this.userIdOrigem = userIdOrigem;
    }

    public String getUserIdDestino() {
        return userIdDestino;
    }

    public void setUserIdDestino(String userIdDestino) {
        this.userIdDestino = userIdDestino;
    }
}
