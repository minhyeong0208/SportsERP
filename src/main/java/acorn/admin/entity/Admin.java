package acorn.admin.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
	@Id
	private String adminId;

    private String adminName;
    private String password;
    private String adminEmail;
    private String teamId;

    private Date adminBirth;
    private Date accountCreate;
}
