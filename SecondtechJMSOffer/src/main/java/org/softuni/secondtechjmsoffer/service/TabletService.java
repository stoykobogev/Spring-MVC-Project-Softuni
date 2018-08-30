package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.Tablet;
import org.softuni.secondtechjmsoffer.domain.models.binding.TabletRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.TabletUpdateBindingModel;

import java.util.List;

public interface TabletService {
    void saveTablet(Tablet tablet);

    void updateTablet(TabletUpdateBindingModel tabletUpdateBindingModel);

    void removeTablet(TabletRemoveBindingModel tabletRemoveBindingModel);

    void deleteAllRejected();

    List<Tablet> findAllRejected();
}
