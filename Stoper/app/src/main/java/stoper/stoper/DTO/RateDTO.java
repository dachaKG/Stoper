package stoper.stoper.DTO;

import java.util.Date;

public class RateDTO {

    private String recieverEmail;

    private String recieverFirstName;

    private String evaluatorEmail;

    private String evaluatorFirstName;

    private String mark;

    private String comment;

    private Date date;

    private byte[] recieverProfileImage;

    private byte[] evaluatorProfileImage;
    public RateDTO() {
    }

    public RateDTO(String recieverEmail, String recieverFirstName, String evaluatorEmail, String evaluatorFirstName, String mark, String comment, Date date, byte[] recieverProfileImage, byte[] evaluatorProfileImage) {
        this.recieverEmail = recieverEmail;
        this.recieverFirstName = recieverFirstName;
        this.evaluatorEmail = evaluatorEmail;
        this.evaluatorFirstName = evaluatorFirstName;
        this.mark = mark;
        this.comment = comment;
        this.date = date;
        this.recieverProfileImage = recieverProfileImage;
        this.evaluatorProfileImage = evaluatorProfileImage;
    }

    public String getRecieverEmail() {
        return recieverEmail;
    }

    public void setRecieverEmail(String recieverEmail) {
        this.recieverEmail = recieverEmail;
    }

    public String getEvaluatorEmail() {
        return evaluatorEmail;
    }

    public void setEvaluatorEmail(String evaluatorEmail) {
        this.evaluatorEmail = evaluatorEmail;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRecieverFirstName() {
        return recieverFirstName;
    }

    public void setRecieverFirstName(String recieverFirstName) {
        this.recieverFirstName = recieverFirstName;
    }

    public String getEvaluatorFirstName() {
        return evaluatorFirstName;
    }

    public void setEvaluatorFirstName(String evaluatorFirstName) {
        this.evaluatorFirstName = evaluatorFirstName;
    }

    public byte[] getRecieverProfileImage() {
        return recieverProfileImage;
    }

    public void setRecieverProfileImage(byte[] recieverProfileImage) {
        this.recieverProfileImage = recieverProfileImage;
    }

    public byte[] getEvaluatorProfileImage() {
        return evaluatorProfileImage;
    }

    public void setEvaluatorProfileImage(byte[] evaluatorProfileImage) {
        this.evaluatorProfileImage = evaluatorProfileImage;
    }
}
