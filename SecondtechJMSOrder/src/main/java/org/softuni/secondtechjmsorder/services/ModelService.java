package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.Order;
import org.softuni.secondtechjmsorder.models.binding.OrderBindingModel;

public interface ModelService {

    Order mapOrderBindingModelToOrder(OrderBindingModel orderBindingModel);
}
