package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.Order;
import org.softuni.secondtechjmsorder.domain.models.binding.OrderBindingModel;

public interface ModelService {

    Order mapOrderBindingModelToOrder(OrderBindingModel orderBindingModel);
}
