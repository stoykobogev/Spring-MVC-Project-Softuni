package org.softuni.secondtech.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.secondtech.domain.entities.Tablet;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.softuni.secondtech.repository.TabletRepository;
import org.softuni.secondtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TabletServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private TabletRepository tabletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TabletService tabletService;

    private Tablet approvedTablet;
    private Tablet rejectedTablet;
    private User user;

    @Before
    public void init() {
        this.user = new User();
        this.user.setEmail("email");
        this.user.setPassword("password");
        this.user.setUsername("username");
        this.user = this.userRepository.saveAndFlush(this.user);

        Tablet pendingTablet = new Tablet();
        pendingTablet.setStatus(ProductStatus.PENDING);
        pendingTablet.setUser(this.user);
        pendingTablet.setPrice(BigDecimal.ONE);
        this.tabletRepository.saveAndFlush(pendingTablet);

        this.rejectedTablet = new Tablet();
        this.rejectedTablet.setStatus(ProductStatus.REJECTED);
        this.rejectedTablet.setUser(this.user);
        this.rejectedTablet.setPrice(BigDecimal.ONE);
        this.rejectedTablet = this.tabletRepository.saveAndFlush(this.rejectedTablet);

        this.approvedTablet = new Tablet();
        this.approvedTablet.setStatus(ProductStatus.APPROVED);
        this.approvedTablet.setPrice(BigDecimal.ONE);
        this.approvedTablet = this.tabletRepository.saveAndFlush(this.approvedTablet);

        Tablet soldTablet = new Tablet();
        soldTablet.setStatus(ProductStatus.SOLD);
        soldTablet.setPrice(BigDecimal.ONE);
        this.tabletRepository.saveAndFlush(soldTablet);
    }

    @Test
    public void findAllApproved_mixedTabletStatuses_returnOneApproved() {
        List<Tablet> resultList = this.tabletService.findAllApproved();

        assertEquals(1, resultList.size());
    }

    @Test
    public void findApprovedById_approvedTabletId_returnsApprovedTablet() {
        Tablet result = this.tabletService.findApprovedById(this.approvedTablet.getId());

        assertNotNull(result);
        assertEquals(this.approvedTablet.getId(), result.getId());
    }

    @Test
    public void findApprovedById_rejectedTabletId_returnsNull() {
        Tablet result = this.tabletService.findApprovedById(this.rejectedTablet.getId());

        assertNull(result);
    }

    @Test
    public void findAllPending_mixedTabletStatuses_returnsPendingTablet() {
        List<Tablet> resultList = this.tabletService.findAllPending();

        assertEquals(1, resultList.size());
        for (Tablet laptop : resultList) {
            assertEquals(ProductStatus.PENDING, laptop.getStatus());
        }
    }

    @Test
    public void findAllByUsername_validUsername_returnsTwoTablets() {
        List<Tablet> resultList = this.tabletService.findAllByUsername(this.user.getUsername());

        assertEquals(2, resultList.size());
    }

    @Test
    public void findAllByUsername_invalidUsername_returnsNoTablets() {
        List<Tablet> resultList = this.tabletService.findAllByUsername("invalidUsername");

        assertNull(resultList);
    }

    @Test
    public void findAllByUser_validUser_returnsTwoTablets() {
        List<Tablet> resultList = this.tabletService.findAllByUser(this.user);

        assertEquals(2, resultList.size());
    }

    @Test
    public void findAllByUser_invalidUser_returnsNoTablets() {
        User invalidUser = new User();
        invalidUser.setEmail("email2");
        invalidUser.setPassword("password2");
        invalidUser.setUsername("username2");
        invalidUser = this.userRepository.saveAndFlush(invalidUser);

        List<Tablet> resultList = this.tabletService.findAllByUser(invalidUser);

        assertEquals(0, resultList.size());
    }

    @Test
    public void deleteAllByUser_mixedTablets_twoTabletsRemainingInRepository() {
        this.tabletService.deleteAllByUser(this.user);

        List<Tablet> resultList = this.tabletRepository.findAll();

        assertEquals(2, resultList.size());
    }
}
