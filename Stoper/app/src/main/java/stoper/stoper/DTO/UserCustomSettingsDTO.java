package stoper.stoper.DTO;

public class UserCustomSettingsDTO {

    private String email;
    private int speaking;
    private int smoking;
    private int music;
    private int pets;

    public UserCustomSettingsDTO() {
    }

    public UserCustomSettingsDTO(String email, int speaking, int smoking, int music, int pets) {
        this.email = email;
        this.speaking = speaking;
        this.smoking = smoking;
        this.music = music;
        this.pets = pets;
    }

    public int getSpeaking() {
        return speaking;
    }

    public void setSpeaking(int speaking) {
        this.speaking = speaking;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
