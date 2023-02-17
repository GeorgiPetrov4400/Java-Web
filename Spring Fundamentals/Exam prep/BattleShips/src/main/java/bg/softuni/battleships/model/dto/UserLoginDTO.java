package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Size(min = 3, max = 10)
    private String username;

    @Size(min = 3)
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
