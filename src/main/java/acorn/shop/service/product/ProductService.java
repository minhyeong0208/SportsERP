package acorn.shop.service.product;

import acorn.shop.dto.product.ProductDTO;
import acorn.shop.entity.product.Product;
import acorn.shop.repository.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * 모든 상품을 조회합니다.
     *
     * @return 모든 상품의 DTO 리스트
     */
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductDTO.convertFromEntity(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * 특정 ID의 상품을 조회합니다.
     *
     * @param id 조회할 상품의 ID
     * @return 조회된 상품의 DTO
     * @throws RuntimeException 상품이 존재하지 않을 경우
     */
    public ProductDTO getProductById(int id) {
        return productRepository.findById(id)
                .map(product -> ProductDTO.convertFromEntity(product, ProductDTO.class))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    /**
     * 새로운 상품을 추가합니다.
     *
     * @param productDTO 추가할 상품의 DTO
     * @return 저장된 상품의 DTO
     */
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productDTO.convertToEntity(Product.class);
        Product savedProduct = productRepository.save(product);
        return ProductDTO.convertFromEntity(savedProduct, ProductDTO.class);
    }

    /**
     * 기존 상품의 정보를 업데이트합니다.
     *
     * @param id 업데이트할 상품의 ID
     * @param productDTO 업데이트할 상품 정보가 담긴 DTO
     * @return 업데이트된 상품의 DTO
     * @throws RuntimeException 상품이 존재하지 않을 경우
     */
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.updateProductName(productDTO.getProductName());
                    existingProduct.updatePrice(productDTO.getProductPrice());
                    existingProduct.updateQuantity(productDTO.getProductQuantity());
                    Product savedProduct = productRepository.save(existingProduct);
                    return ProductDTO.convertFromEntity(savedProduct, ProductDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    /**
     * 특정 ID의 상품을 삭제합니다.
     *
     * @param id 삭제할 상품의 ID
     */
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    /**
     * 상품 이름으로 상품을 검색합니다. (부분 일치)
     *
     * @param name 검색할 상품 이름
     * @return 검색된 상품들의 DTO 리스트
     */
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepository.findByProductNameContaining(name).stream()
                .map(product -> ProductDTO.convertFromEntity(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * 특정 가격 범위 내의 상품들을 검색합니다.
     *
     * @param minPrice 최소 가격
     * @param maxPrice 최대 가격
     * @return 해당 가격 범위 내의 상품들의 DTO 리스트
     */
    public List<ProductDTO> getProductsByPriceRange(int minPrice, int maxPrice) {
        return productRepository.findByProductPriceBetween(minPrice, maxPrice).stream()
                .map(product -> ProductDTO.convertFromEntity(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * 재고가 특정 수량 이하인 상품들을 조회합니다.
     *
     * @param threshold 재고 임계값
     * @return 재고가 임계값 이하인 상품들의 DTO 리스트
     */
    public List<ProductDTO> getLowStockProducts(int threshold) {
        return productRepository.findByProductQuantityLessThanEqual(threshold).stream()
                .map(product -> ProductDTO.convertFromEntity(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * 최근에 수정된 상품들을 조회합니다.
     *
     * @return 최근 수정된 상품들의 DTO 리스트
     */
    public List<ProductDTO> getRecentlyModifiedProducts() {
        return productRepository.findRecentlyModifiedProducts().stream()
                .map(product -> ProductDTO.convertFromEntity(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}