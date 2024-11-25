package ma.ensaj.StaySafe.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    // Constructor, getters, setters

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


}