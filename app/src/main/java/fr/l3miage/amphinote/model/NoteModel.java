package fr.l3miage.amphinote.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.l3miage.amphinote.utils.Serveur;
public class NoteModel implements Serializable {

    private String image;
    private String description;
    private String titre;
    private Integer userid;
    private Integer id;
    @SerializedName("aime")
    private int aime;
    private String matiere;
    private String date_ajout;


    public int getAime() {
        return aime;
    }

    public void setAime(int aime) {
        this.aime = aime;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getImage() {
        return image;
    }

    public String getPath(){
        return Serveur.url+image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenomination(){
        return "["+this.matiere+"] - "+this.titre;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }
}
