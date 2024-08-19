package acorn.user.entity;

import acorn.user.dto.UsersDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Users {

    @Id
    @Column(name="user_id")
    private String id;

    @Column(name="user_name")
    private String name;

    private String password;

    @Column(name="user_birth")
    private String birthDate;

    @Column(name="user_email")
    private String email;

    @Column(name="account_create")
    private String accCrtDate;
    
    // dto to entity
    public static Users toentity(UsersDto dto){
        return Users.builder()
                .id(dto.getId())
                .name(dto.getName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .accCrtDate(dto.getAccCrtDate())
                .build();
    }
}
