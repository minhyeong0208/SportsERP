package acorn.shop.entity.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory") // 재고 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * 재고 엔티티 클래스
 *
 * 예제:
 *
 * # 빌더를 사용하여 재고 생성
 * Inventory inventory = Inventory.builder()
 *              .inventoryName("Widget")
 *              .category("Gadgets")
 *              .quantity(100)
 *              .purchaseDate(new Date())
 *              .price(1000)
 *              .build();
 *
 * // 수량, 가격, 판매일 업데이트
 * inventory.updateQuantity(150);
 * inventory.updatePrice(3000);
 * inventory.updatePurchaseDate(new Date());
 */
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "inventory_name", nullable = false)
    private String inventoryName;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @Column(name = "price", nullable = false)
    private int price;

    @PrePersist
    protected void onCreate() {
        purchaseDate = new Date(); // default (생성 시점)
    }

    @PreUpdate
    protected void onUpdate() {
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
    public void updatePrice(int newPrice) {
        this.price = newPrice;
    }
    public void updatePurchaseDate(Date newPurchaseDate) {
        this.purchaseDate = newPurchaseDate;
    }
    public void updateInventoryName(String newInventoryName) {
        this.inventoryName = newInventoryName;
    }
    public void updateCategory(String newCategory) {
    }
}