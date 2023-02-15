package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.service.OrderServiceModel;
import bg.softuni.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrderOrderByPriceDesc();

    void readyOrder(Long id);
}
