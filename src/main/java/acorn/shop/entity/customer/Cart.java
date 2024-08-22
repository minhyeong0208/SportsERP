package acorn.shop.entity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;

@Entity
@Table (name = "cart") // 장바구니 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * 장바구니 엔티티 클래스
 *
 * 예제:
 *
 * # 빌더를 사용하여 생성
 * Cart cart = Cart.builder()
 *              .customer(customer)
 *              .build();
 *
 */
public class Cart {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @PrePersist
    protected void onCreate() {
    }

    @PreUpdate
    protected void onUpdate() {
    }
}