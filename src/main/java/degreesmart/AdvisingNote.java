package degreesmart;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AdvisingNote {
  private String note;
  private Advisor author;
  private ZonedDateTime time;
  private static final String timeFormat = "dd MMMM yyyy HH:mm:ss z";

  public AdvisingNote(Advisor author, String note, String time) {
    setAuthor(author);
    setNote(note);
    setTime(time);
  }

  public AdvisingNote(Advisor author, String note) {
    this(author, note, ZonedDateTime.now().format(DateTimeFormatter.ofPattern(timeFormat)));
  }

  private void setTime(String time) throws IllegalArgumentException {
    if (time == null) {
      throw new IllegalArgumentException("time cannot be null");
    }

    try {
      this.time = ZonedDateTime.parse(time, DateTimeFormatter.ofPattern(timeFormat));
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) throws IllegalArgumentException {
    if (note == null) {
      throw new IllegalArgumentException("note cannot be null");
    }

    if (note.equals("")) {
      throw new IllegalArgumentException("note cannot be empty");
    }

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

  public String getTimeString() {
    return time.format(DateTimeFormatter.ofPattern(timeFormat));
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
    return ((author == null)? "(deleted user)" : author.getUsername())  + ", "
      + time.format(DateTimeFormatter.ofPattern(timeFormat))
      + " - " + note;
  }
}
