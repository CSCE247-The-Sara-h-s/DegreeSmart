package degreesmart;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

  public boolean equals(Object object) {
    if (object == null || ! (object instanceof AdvisingNote)) {
      return false;
    }
    AdvisingNote advisingNote = (AdvisingNote)object;

    return note.equals(advisingNote.getNote())
      && author.equals(advisingNote.getAuthor())
      && time.equals(advisingNote.getTime());
  }

  public String toString() {
    return author.getUsername()  + ", "
      + time.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss z"))
      + " - " + note;
  }
}
