package org.softuni.secondtech.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.secondtech.domain.entities.Laptop;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.softuni.secondtech.repository.LaptopRepository;
import org.softuni.secondtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LaptopServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LaptopService laptopService;

    private Laptop approvedLaptop;
    private Laptop rejectedLaptop;
    private User user;

    @Before
    public void init() {
        this.user = new User();
        this.user.setEmail("email");
        this.user.setPassword("password");
        this.user.setUsername("username");
        this.user = this.userRepository.saveAndFlush(this.user);

        Laptop pendingLaptop = new Laptop();
        pendingLaptop.setStatus(ProductStatus.PENDING);
        pendingLaptop.setUser(this.user);
        pendingLaptop.setPrice(BigDecimal.ONE);
        this.laptopRepository.saveAndFlush(pendingLaptop);

        this.rejectedLaptop = new Laptop();
        this.rejectedLaptop.setStatus(ProductStatus.REJECTED);
        this.rejectedLaptop.setUser(this.user);
        this.rejectedLaptop.setPrice(BigDecimal.ONE);
        this.rejectedLaptop = this.laptopRepository.saveAndFlush(this.rejectedLaptop);

        this.approvedLaptop = new Laptop();
        this.approvedLaptop.setStatus(ProductStatus.APPROVED);
        this.approvedLaptop.setPrice(BigDecimal.ONE);
        this.approvedLaptop = this.laptopRepository.saveAndFlush(this.approvedLaptop);

        Laptop soldLaptop = new Laptop();
        soldLaptop.setStatus(ProductStatus.SOLD);
        soldLaptop.setPrice(BigDecimal.ONE);
        this.laptopRepository.saveAndFlush(soldLaptop);
    }

    @Test
    public void findAllApproved_mixedLaptopStatuses_returnOneApproved() {
        List<Laptop> laptopList = this.laptopService.findAllApproved();

        assertEquals(1, laptopList.size());
    }

    @Test
    public void findApprovedById_approvedLaptopId_returnsApprovedLaptop() {
        Laptop result = this.laptopService.findApprovedById(this.approvedLaptop.getId());

        assertNotNull(result);
        assertEquals(this.approvedLaptop.getId(), result.getId());
    }

    @Test
    public void findApprovedById_rejectedLaptopId_returnsNull() {
        Laptop result = this.laptopService.findApprovedById(this.rejectedLaptop.getId());

        assertNull(result);
    }

    @Test
    public void findAllPending_mixedLaptopStatuses_returnsPendingLaptop() {
        List<Laptop> resultList = this.laptopService.findAllPending();

        assertEquals(1, resultList.size());
        for (Laptop laptop : resultList) {
            assertEquals(ProductStatus.PENDING, laptop.getStatus());
        }
    }

    @Test
    public void findAllByUsername_validUsername_returnsTwoLaptops() {
        List<Laptop> resultList = this.laptopService.findAllByUsername(this.user.getUsername());

        assertEquals(2, resultList.size());
    }

    @Test
    public void findAllByUsername_invalidUsername_returnsNoLaptops() {
        List<Laptop> resultList = this.laptopService.findAllByUsername("invalidUsername");

        assertNull(resultList);
    }

    @Test
    public void findAllByUser_validUser_returnsTwoLaptops() {
        List<Laptop> resultList = this.laptopService.findAllByUser(this.user);

        assertEquals(2, resultList.size());
    }

    @Test
    public void findAllByUser_invalidUser_returnsNoLaptops() {
        User invalidUser = new User();
        invalidUser.setEmail("email2");
        invalidUser.setPassword("password2");
        invalidUser.setUsername("username2");
        invalidUser = this.userRepository.saveAndFlush(invalidUser);

        List<Laptop> resultList = this.laptopService.findAllByUser(invalidUser);

        assertEquals(0, resultList.size());
    }

    @Test
    public void deleteAllByUser_mixedLaptops_twoLaptopsRemainingInRepository() {
        this.laptopService.deleteAllByUser(this.user);

        List<Laptop> resultList = this.laptopRepository.findAll();

        assertEquals(2, resultList.size());
    }
}
