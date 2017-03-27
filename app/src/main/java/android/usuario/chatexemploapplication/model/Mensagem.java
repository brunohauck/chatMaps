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
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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
