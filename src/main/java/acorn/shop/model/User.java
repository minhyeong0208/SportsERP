package acorn.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    private String id;

    private String customerId;
    private String customerPassword; // 비밀번호 필드 추가

}
