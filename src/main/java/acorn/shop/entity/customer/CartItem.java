package acorn.shop.entity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import acorn.shop.entity.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "cartitem") // 장바구니 항목 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * 장바구니 항목 엔티티 클래스
 *
 * 예제:
 *
 * # 빌더를 사용하여 생성
 * CartItem cartitem = CartItem.builder()
 *              .cart(cart)
 *              .product(product)
 *              .cartItemQuantity(10)
 *              .build();
 *
 * # 각 항목 업데이트
 * cartitem.updateCart(cart);
 * cartitem.updateProduct(product);
 * cartitem.updateCartItemQuantity(10);
 */
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitem_id")
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "cartitem_quantity")
    private int cartItemQuantity;

    @PrePersist
    protected void onCreate() {
    }

    @PreUpdate
    protected void onUpdate() {
    }

    public void updateCart(Cart cart) {
        this.cart = cart;
    }
    public void updateProduct(Product product) {
        this.product = product;
    }
    public void updateCartItemQuantity(int cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }
}
