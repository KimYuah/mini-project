package mini.project.domain;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int no; // 번호
  private String title; // 제목
  private String author; // 저자
  private String publisher; // 출판사
  private Date ReceivingDate; // 도서 입고일
  private boolean available; // 대여가능 여부



  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Date getReceivingDate() {
    return ReceivingDate;
  }

  public void setReceivingDate(Date receivingDate) {
    ReceivingDate = receivingDate;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }



  @Override
  public String toString() {
    return "Book [no=" + no + ", title=" + title + ", author=" + author + ", publisher=" + publisher
        + ", ReceivingDate=" + ReceivingDate + ", available=" + available + "]";
  }



}
