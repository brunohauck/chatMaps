package android.usuario.chatexemploapplication.model;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by User on 18/03/2017.
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    private String id;
    private String nome;
    private String email;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }




}
