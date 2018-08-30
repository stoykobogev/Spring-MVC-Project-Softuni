package org.softuni.secondtech.service;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.softuni.secondtech.domain.entities.*;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.softuni.secondtech.domain.models.binding.UserRegisterBindingModel;
import org.softuni.secondtech.domain.models.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");

    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configMapper();
    }

    @Override
    public User mapModelToUser(UserRegisterBindingModel model) {
        return this.modelMapper.map(model, User.class);
    }

    @Override
    public User mapModelToModerator(UserRegisterBindingModel model) {
        return this.modelMapper.map(model, User.class);
    }

    @Override
    public Page<LaptopAllViewModel> mapLaptopPageToLaptopsAllViewModelPage(Page<Laptop> laptopPage) {
        return laptopPage.map(this::mapLaptopToLaptopAllViewModel);

    }

    private LaptopAllViewModel mapLaptopToLaptopAllViewModel(Laptop laptop) {
        return this.modelMapper.map(laptop, LaptopAllViewModel.class);
    }

    @Override
    public Page<SmartphoneAllViewModel> mapSmartphonePageToSmartphonesAllViewModelPage(Page<SmartPhone> smartphonePage) {
        return smartphonePage.map(this::mapSmartphoneToSmartphoneAllViewModel);

    }

    private SmartphoneAllViewModel mapSmartphoneToSmartphoneAllViewModel(SmartPhone smartphone) {
        return this.modelMapper.map(smartphone, SmartphoneAllViewModel.class);
    }

    @Override
    public Page<TabletAllViewModel> mapTabletPageToTabletsAllViewModelPage(Page<Tablet> tabletPage) {
        return tabletPage.map(this::mapLaptopToLaptopAllViewModel);

    }

    private TabletAllViewModel mapLaptopToLaptopAllViewModel(Tablet tablet) {
        return this.modelMapper.map(tablet, TabletAllViewModel.class);
    }

    @Override
    public LaptopDetailsViewModel mapLaptopToLaptopDetailsViewModel(Laptop laptop) {
        return this.modelMapper.map(laptop, LaptopDetailsViewModel.class);
    }

    @Override
    public List<LaptopDetailsViewModel> mapLaptopListToLaptopDetailsViewModelList(List<Laptop> laptopList) {
        return laptopList.stream().map(this::mapLaptopToLaptopDetailsViewModel).collect(Collectors.toList());
    }

    @Override
    public SmartphoneDetailsViewModel mapSmartphoneToSmartphoneDetailsViewModel(SmartPhone smartphone) {
        return this.modelMapper.map(smartphone, SmartphoneDetailsViewModel.class);
    }

    @Override
    public List<SmartphoneDetailsViewModel> mapSmartphoneListToSmartphoneDetailsViewModelList
            (List<SmartPhone> smartphoneList) {
        return smartphoneList.stream().map(this::mapSmartphoneToSmartphoneDetailsViewModel).collect(Collectors.toList());
    }

    @Override
    public TabletDetailsViewModel mapTabletToTabletDetailsViewModel(Tablet tablet) {
        return this.modelMapper.map(tablet, TabletDetailsViewModel.class);
    }

    @Override
    public List<TabletDetailsViewModel> mapTabletListToTabletDetailsViewModelList(List<Tablet> tabletList) {
        return tabletList.stream().map(this::mapTabletToTabletDetailsViewModel).collect(Collectors.toList());
    }

    @Override
    public List<CommentDetailsViewModel> mapCommentListToCommentDetailsViewModelList(List<Comment> commentList) {
        return commentList.stream().map(this::mapCommentToCommentDetailsViewModel).collect(Collectors.toList());
    }

    private CommentDetailsViewModel mapCommentToCommentDetailsViewModel(Comment comment) {
        return this.modelMapper.map(comment, CommentDetailsViewModel.class);
    }

    @Override
    public List<OrderAllViewModel> mapOrderListToOrderAllViewModelList(List<Order> orderList) {
        return orderList.stream().map(this::mapOrderToOrderAllViewModel).collect(Collectors.toList());
    }

    private OrderAllViewModel mapOrderToOrderAllViewModel(Order order) {
        OrderAllViewModel model = new OrderAllViewModel();
        model.setUsername(order.getUser().getUsername());
        model.setEmail(order.getUser().getEmail());
        model.setProductHref(generateHref(order.getProduct()));
        model.setOrderedOn(order.getOrderedOn().format(DATE_TIME_FORMATTER));
        model.setProductName(order.getProduct().getName());

        return model;
    }

    @Override
    public List<CommentPendingViewModel> mapCommentListToCommentPendingViewModelList(List<Comment> comments){
        return comments.stream().map(this::mapCommentToCommentPendingViewModel).collect(Collectors.toList());
    }

    private CommentPendingViewModel mapCommentToCommentPendingViewModel(Comment comment) {
        return this.modelMapper.map(comment, CommentPendingViewModel.class);
    }

    @Override
    public List<LaptopMineViewModel> mapLaptopListToLaptopMineViewModelList(List<Laptop> laptopList) {
        return laptopList.stream().map(this::mapLaptopToLaptopMineViewModel).collect(Collectors.toList());
    }

    private LaptopMineViewModel mapLaptopToLaptopMineViewModel(Laptop laptop) {
        return this.modelMapper.map(laptop, LaptopMineViewModel.class);
    }

    @Override
    public List<SmartphoneMineViewModel> mapSmartphoneListToSmartphoneMineViewModelList(List<SmartPhone> smartphoneList) {
        return smartphoneList.stream().map(this::mapSmartphoneToSmartphoneMineViewModel).collect(Collectors.toList());
    }

    private SmartphoneMineViewModel mapSmartphoneToSmartphoneMineViewModel(SmartPhone smartphone) {
        return this.modelMapper.map(smartphone, SmartphoneMineViewModel.class);
    }

    @Override
    public List<TabletMineViewModel> mapTabletListToTabletMineViewModelList(List<Tablet> tabletList) {
        return tabletList.stream().map(this::mapTabletToTabletMineViewModel).collect(Collectors.toList());
    }

    private TabletMineViewModel mapTabletToTabletMineViewModel(Tablet tablet) {
        return this.modelMapper.map(tablet, TabletMineViewModel.class);
    }

    @Override
    public List<LaptopRemoveViewModel> mapLaptopListToLaptopRemoveViewModelList(List<Laptop> laptopList) {
        return laptopList.stream().map(this::mapLaptopToLaptopRemoveViewModel).collect(Collectors.toList());
    }

    private LaptopRemoveViewModel mapLaptopToLaptopRemoveViewModel(Laptop laptop) {
        return this.modelMapper.map(laptop, LaptopRemoveViewModel.class);
    }

    @Override
    public List<SmartphoneRemoveViewModel> mapSmartphoneListToSmartphoneRemoveViewModelList(List<SmartPhone> smartphoneList) {
        return smartphoneList.stream().map(this::mapSmartphoneToSmartphoneRemoveViewModel).collect(Collectors.toList());
    }

    private SmartphoneRemoveViewModel mapSmartphoneToSmartphoneRemoveViewModel(SmartPhone smartphone) {
        return this.modelMapper.map(smartphone, SmartphoneRemoveViewModel.class);
    }

    @Override
    public List<TabletRemoveViewModel> mapTabletListToTabletRemoveViewModelList(List<Tablet> tabletList) {
        return tabletList.stream().map(this::mapTabletToTabletRemoveViewModel).collect(Collectors.toList());
    }

    private TabletRemoveViewModel mapTabletToTabletRemoveViewModel(Tablet tablet) {
        return this.modelMapper.map(tablet, TabletRemoveViewModel.class);
    }

    @Override
    public Page<UserAllViewModel> mapUserPageToUserAllViewModelPage(Page<User> userList) {
        return userList.map(this::mapUserToUserAllViewModel);
    }

    private UserAllViewModel mapUserToUserAllViewModel(User user) {
        return this.modelMapper.map(user, UserAllViewModel.class);
    }

    private String generateHref(BaseProduct product) {
        if (product instanceof Laptop) {
            return "/laptops/details/" + product.getId();
        } else if (product instanceof SmartPhone) {
            return "/smartphones/details/" + product.getId();
        } else if (product instanceof Tablet) {
            return "/tablets/details/" + product.getId();
        } else {
            return "#";
        }
    }

    private void configMapper() {

//        Don't change to lambda function, it breaks

        this.modelMapper.addConverter(new Converter<String, BigDecimal>() {
            @Override
            public BigDecimal convert(MappingContext<String, BigDecimal> context) {
                return  new BigDecimal(context.getSource());
            }
        });

        this.modelMapper.addConverter(new Converter<BigDecimal, String>() {
            @Override
            public String convert(MappingContext<BigDecimal, String> context) {
                return context.getSource().toString();
            }
        });

        this.modelMapper.addMappings(new PropertyMap<Comment, CommentDetailsViewModel>() {
            @Override
            protected void configure() {
                map().setUsername(source.getUser().getUsername());
            }
        });

        this.modelMapper.addMappings(new PropertyMap<Comment, CommentPendingViewModel>() {
            @Override
            protected void configure() {
                map().setUsername(source.getUser().getUsername());
            }
        });

        this.modelMapper.addMappings(new PropertyMap<User, UserAllViewModel>() {
            @Override
            protected void configure() {
                map().setRole(source.extractRole());
            }
        });

        this.modelMapper.addConverter(new Converter<ProductStatus, String>() {
            @Override
            public String convert(MappingContext<ProductStatus, String> context) {
                return context.getSource().toString();
            }
        });
    }
}
