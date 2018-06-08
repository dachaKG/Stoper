package stoper.stoper.DTO;

public class UserImageDTO {
    private String email;
    private byte[] imageBytes;

    public UserImageDTO() {
    }

    public UserImageDTO(String email, byte[] imageBytes) {
        this.email = email;
        this.imageBytes = imageBytes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
