package acorn.shop.repository.product;

import acorn.shop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 상품 이름으로 검색 (부분 일치)
    List<Product> findByProductNameContaining(String productName);

    // 가격 범위로 상품 검색
    List<Product> findByProductPriceBetween(int minPrice, int maxPrice);

    // 재고 수량이 특정 값 이하인 상품 검색
    List<Product> findByProductQuantityLessThanEqual(int quantity);

    // 특정 날짜 이후에 등록된 상품 검색
    List<Product> findByProductRegistDateAfter(Date date);

    // 가격 내림차순으로 상품 정렬
    List<Product> findAllByOrderByProductPriceDesc();

    // 최근 수정된 상품 조회
    @Query("SELECT p FROM Product p ORDER BY p.productLastModiDate DESC")
    List<Product> findRecentlyModifiedProducts();
}