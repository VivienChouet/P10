package com.bibliotheque.API.Entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "book")
public class Book {

   @Id
    public int id;
    public String title;
    public String author;
    public Date publication;
    public String resume;


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

 public String getAuthor() {
  return author;
 }

 public void setAuthor(String author) {
  this.author = author;
 }

 public Date getPublication() {
  return publication;
 }

 public void setPublication(Date publication) {
  this.publication = publication;
 }

 public String getResume() {
  return resume;
 }

 public void setResume(String resume) {
  this.resume = resume;
 }
}


