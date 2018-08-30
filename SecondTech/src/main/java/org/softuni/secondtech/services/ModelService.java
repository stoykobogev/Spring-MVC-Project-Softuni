package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.*;
import org.softuni.secondtech.models.binding.UserRegisterBindingModel;
import org.softuni.secondtech.models.view.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModelService {
    User mapModelToUser(UserRegisterBindingModel model);

    Page<SmartphoneAllViewModel> mapSmartphonePageToSmartphonesAllViewModelPage(Page<SmartPhone> smartphonePage);

    User mapModelToModerator(UserRegisterBindingModel model);

    Page<LaptopAllViewModel> mapLaptopPageToLaptopsAllViewModelPage(Page<Laptop> laptopPage);

    Page<TabletAllViewModel> mapTabletPageToTabletsAllViewModelPage(Page<Tablet> tabletPage);

    LaptopDetailsViewModel mapLaptopToLaptopDetailsViewModel(Laptop laptop);;

    SmartphoneDetailsViewModel mapSmartphoneToSmartphoneDetailsViewModel(SmartPhone smartphone);

    List<SmartphoneDetailsViewModel> mapSmartphoneListToSmartphoneDetailsViewModelList
            (List<SmartPhone> smartphoneList);

    TabletDetailsViewModel mapTabletToTabletDetailsViewModel(Tablet tablet);

    List<LaptopDetailsViewModel> mapLaptopListToLaptopDetailsViewModelList(List<Laptop> laptopList);

    List<TabletDetailsViewModel> mapTabletListToTabletDetailsViewModelList(List<Tablet> tabletList);

    List<CommentDetailsViewModel> mapCommentListToCommentDetailsViewModelList(List<Comment> commentList);

    List<OrderAllViewModel> mapOrderListToOrderAllViewModelList(List<Order> orderList);

    List<CommentPendingViewModel> mapCommentListToCommentPendingViewModelList(List<Comment> comments);

    List<SmartphoneMineViewModel> mapSmartphoneListToSmartphoneMineViewModelList(List<SmartPhone> smartphoneList);

    List<LaptopMineViewModel> mapLaptopListToLaptopMineViewModelList(List<Laptop> laptopList);

    List<TabletMineViewModel> mapTabletListToTabletMineViewModelList(List<Tablet> tabletList);

    List<LaptopRemoveViewModel> mapLaptopListToLaptopRemoveViewModelList(List<Laptop> laptopList);

    List<SmartphoneRemoveViewModel> mapSmartphoneListToSmartphoneRemoveViewModelList(List<SmartPhone> smartphoneList);

    List<TabletRemoveViewModel> mapTabletListToTabletRemoveViewModelList(List<Tablet> tabletList);

    Page<UserAllViewModel> mapUserPageToUserAllViewModelPage(Page<User> userList);
}
