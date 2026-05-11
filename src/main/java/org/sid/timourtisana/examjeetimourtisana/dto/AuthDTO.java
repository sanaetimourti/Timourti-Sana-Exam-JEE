package org.sid.timourtisana.examjeetimourtisana.dto;
import lombok.Data;
@Data
public class AuthDTO {
    private String username;
    private String password;
    private String email;
    private String role;
    private String token;
}