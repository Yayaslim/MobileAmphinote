package fr.l3miage.amphinote.model;

import fr.l3miage.amphinote.utils.Serveur;

public class NoteModel {

    private String image;
    private String description;
    private Integer note;
    private String titre;
    private Integer userid;
    private Integer id;

    public NoteModel(String image, String titre) {
        this.image = image;
        this.titre = titre;
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

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
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

}
