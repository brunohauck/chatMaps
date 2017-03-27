package android.usuario.chatexemploapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunodelhferreira on 19/03/17.
 */

@SuppressWarnings("serial")
public class MensagemList implements Serializable {

    @SerializedName("MensagemList")
    private List<Mensagem> mensagemList = new ArrayList<Mensagem>();

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }
}
