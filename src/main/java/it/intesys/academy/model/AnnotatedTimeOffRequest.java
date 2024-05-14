package it.intesys.academy.model;

public abstract class AnnotatedTimeOffRequest implements TimeOffRequest {

  private String note;

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
