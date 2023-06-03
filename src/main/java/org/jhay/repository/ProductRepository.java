package org.jhay.repository;


import org.jhay.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "update products set price = ?1 where id = ?2",
            nativeQuery = true
    )
    int updateProductPrice(Double price, Long id);

    @Modifying
    @Transactional
    @Query(
            value = "update products set quantity = ?1 where id = ?2",
            nativeQuery = true
    )
    int updateProductQuantity(Integer quantity, Long id);


}
