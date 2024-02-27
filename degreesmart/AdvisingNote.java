package degreesmart;

import java.time.ZonedDateTime;

public class AdvisingNote {
  private String note;
  private Advisor author;
  private ZonedDateTime time;

  public AdvisingNote(Advisor author, String note) {
    this.author = author;
    this.note = note;
    time = ZonedDateTime.now();
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Advisor getAuthor() {
    return author;
  }

  public void setAuthor(Advisor author) {
    this.author = author;
  }

  public ZonedDateTime getTime() {
    return time;
  }
}
