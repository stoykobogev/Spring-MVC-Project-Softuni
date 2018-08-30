package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.Tablet;
import org.softuni.secondtechjmsoffer.models.binding.TabletRemoveBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.TabletUpdateBindingModel;

import java.util.List;

public interface TabletService {
    void saveTablet(Tablet tablet);

    void updateTablet(TabletUpdateBindingModel tabletUpdateBindingModel);

    void removeTablet(TabletRemoveBindingModel tabletRemoveBindingModel);

    void deleteAllRejected();

    List<Tablet> findAllRejected();
}
