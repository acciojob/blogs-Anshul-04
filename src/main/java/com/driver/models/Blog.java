package com.driver.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="blog")
public class Blog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  //primary key
    private  String title;
    private  String content;
    @CreationTimestamp  //Auto timestamp the time when an entry is created
    Date pubDate;   //it is auto generated


    // Blog is child wrt User(parent).
    // We are doing unidirectional mapping here
    @ManyToOne
    @JoinColumn  // Add an extra attribute of authorId (parent table PK) for the foreign key of child table
    private User user;  //This variable is used in the parent class,
                       // while doing the bidirectional mapping

    //Blog is also parent wrt child(Image)
    //Doing Bi-directional mapping

    //Name of variable of the Parent Entity that you have written in child class foreign key attribute.
    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<>();


    public Blog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}