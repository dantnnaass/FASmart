package poly.store.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Product;

/**
 * Class thuc hien truy van thong tin bang Product trong database
 */
public interface ProductDao extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null")
	List<Product> getListProduct();
	
	@Query(value="SELECT TOP(16) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY CreateDay DESC", nativeQuery = true)
	List<Product> getListLatestProduct();
	
	@Query(value="SELECT TOP(14) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListViewsProduct();
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 ORDER BY p.Createday DESC")
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 AND p.price >= ?2 AND p.price <= ?3 ORDER BY p.Createday DESC")
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null")
	List<Product> getListDemo(String nameSearch);
	
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null AND p.Namesearch LIKE ?1")
	Product getProductByNameSearch(String nameSearch);
	
	@Query(value="SELECT TOP(10) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Manu_Id = ?1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductRelated(int manuId);
}
