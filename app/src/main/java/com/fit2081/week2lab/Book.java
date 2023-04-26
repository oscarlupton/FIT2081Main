package com.fit2081.week2lab;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "userID")
    private String userID;
    @ColumnInfo(name = "bookName")
    private String bookName;
    @ColumnInfo(name = "bookISBN")
    private String bookISBN;
    @ColumnInfo(name = "bookAuthor")
    private String bookAuthor;
    @ColumnInfo(name = "bookDescription")
    private String bookDescription;
    @ColumnInfo(name = "bookPrice")
    private String bookPrice;
    public Book(String userID, String bookName, String bookISBN, String bookAuthor, String bookDescription, String bookPrice){
        this.userID = userID;
        this.bookName = bookName;
        this.bookISBN = bookISBN;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookPrice = bookPrice;
    }
    public void setId(int id){
        this.id =id;
    }
    public int getId() {
        return id;
    }
    public String getUserID(){return userID;}
    public void setUserID(@NonNull String userID){this.userID = userID;}
    public String getBookName(){return bookName;}
    public void setBookName(String bookName) {this.bookName = bookName;}
    public String getBookISBN(){return bookISBN;}
    public void setBookISBN(String bookISBN) {this.bookISBN = bookISBN;}
    public String getBookAuthor(){return bookAuthor;}
    public void setBookAuthor(String bookAuthor) {this.bookAuthor = bookAuthor;}
    public String getBookDescription(){return bookDescription;}
    public void setBookDescription(String bookDescription) {this.bookDescription = bookDescription;}
    public String getBookPrice(){return bookPrice;}
    public void setBookPrice(String bookPrice) {this.bookPrice = bookPrice;}
}
