package bg.softuni.coffeeshop.repository;

import bg.softuni.coffeeshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderByPriceDesc();

//    List<Order> findAllByOrderTimeIsTrueOrderByPriceDesc();
}
