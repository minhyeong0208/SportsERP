package acorn.user.dto;

import acorn.user.entity.Users;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UsersDto {

    private String id, name, password, birthDate, email, accCrtDate;

    // 비밀번호 확인용?
    //private String passwordCheck;

    // entity to dto
    public static UsersDto toDto(Users users){
        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .password(users.getPassword())
                .birthDate(users.getBirthDate())
                .email(users.getEmail())
                .accCrtDate(users.getAccCrtDate())
                .build();
    }
}
