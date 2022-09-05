package com.example.projectsem2.repository;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p from Product p order by p.createdAt DESC")
    Page<Product> getAllNewProduct(PageRequest of);

    @Query(value = "select p from Product p inner join Category c on c.id = p.categoryById.id where c.name=?1")
    List<Product> getAllByCategory(String category);

    @Query(value = "select p from Product p inner join  Sale s on p.sale.id = s.id where s.id = 2")
    List<Product> getAllSale25();


    //admin
    @Query("select t from Product t where t.categoryById = ?1")
    Optional<Product> findProductByCategoryID(Category category);

    // String sortField
    @Query(value = "select p from Product p Order By p.id DESC")
    List<Product> sortAllProductOrderByIdDesc();

    @Query(value = "select p from Product p Order By p.id ASC")
    List<Product> sortAllProductOrderByIdAsc();

    @Query(value = "select p from Product p Order By p.name DESC")
    List<Product> sortAllProductOrderByNameDesc();

    @Query(value = "select p from Product p Order By p.name ASC")
    List<Product> sortAllProductOrderByNameAsc();

    @Query(value = "select p from Product p Order By p.price DESC")
    List<Product> sortAllProductOrderByPriceDesc();

    @Query(value = "select p from Product p Order By p.price ASC")
    List<Product> sortAllProductOrderByPriceAsc();

    @Query(value = "select p from Product p Order By p.sale.percent DESC")
    List<Product> sortAllProductOrderBySaleDesc();

    @Query(value = "select p from Product p Order By p.sale.percent ASC")
    List<Product> sortAllProductOrderBySaleAsc();

    @Query(value = "select p from Product p Order By p.createdAt DESC")
    List<Product> sortAllProductOrderByCreatedAtDesc();

    @Query(value = "select p from Product p Order By p.createdAt ASC")
    List<Product> sortAllProductOrderByCreatedAtAsc();
}
