package org.softuni.secondtech.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.secondtech.entities.SmartPhone;
import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.enums.ProductStatus;
import org.softuni.secondtech.repositories.SmartphoneRepository;
import org.softuni.secondtech.repositories.UserRepository;
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
public class SmartphoneServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmartphoneService smartphoneService;

    private SmartPhone approvedSmartphone;
    private SmartPhone rejectedSmartphone;
    private User user;

    @Before
    public void init() {
        this.user = new User();
        this.user.setEmail("email");
        this.user.setPassword("password");
        this.user.setUsername("username");
        this.user = this.userRepository.saveAndFlush(this.user);

        SmartPhone pendingSmartphone = new SmartPhone();
        pendingSmartphone.setStatus(ProductStatus.PENDING);
        pendingSmartphone.setUser(this.user);
        pendingSmartphone.setPrice(BigDecimal.ONE);
        this.smartphoneRepository.saveAndFlush(pendingSmartphone);

        this.rejectedSmartphone = new SmartPhone();
        this.rejectedSmartphone.setStatus(ProductStatus.REJECTED);
        this.rejectedSmartphone.setUser(this.user);
        this.rejectedSmartphone.setPrice(BigDecimal.ONE);
        this.rejectedSmartphone = this.smartphoneRepository.saveAndFlush(this.rejectedSmartphone);

        this.approvedSmartphone = new SmartPhone();
        this.approvedSmartphone.setStatus(ProductStatus.APPROVED);
        this.approvedSmartphone.setPrice(BigDecimal.ONE);
        this.approvedSmartphone = this.smartphoneRepository.saveAndFlush(this.approvedSmartphone);

        SmartPhone soldSmartphone = new SmartPhone();
        soldSmartphone.setStatus(ProductStatus.SOLD);
        soldSmartphone.setPrice(BigDecimal.ONE);
        this.smartphoneRepository.saveAndFlush(soldSmartphone);
    }

    @Test
    public void findAllApproved_mixedSmartphoneStatuses_returnOneApproved() {
        List<SmartPhone> resultList = this.smartphoneService.findAllApproved();

        assertEquals(1, resultList.size());
    }

    @Test
    public void findApprovedById_approvedSmartphoneId_returnsApprovedSmartphone() {
        SmartPhone result = this.smartphoneService.findApprovedById(this.approvedSmartphone.getId());

        assertNotNull(result);
        assertEquals(this.approvedSmartphone.getId(), result.getId());
    }

    @Test
    public void findApprovedById_rejectedSmartphoneId_returnsNull() {
        SmartPhone result = this.smartphoneService.findApprovedById(this.rejectedSmartphone.getId());

        assertNull(result);
    }

    @Test
    public void findAllPending_mixedSmartphoneStatuses_returnsPendingSmartphone() {
        List<SmartPhone> resultList = this.smartphoneService.findAllPending();

        assertEquals(1, resultList.size());
        for (SmartPhone laptop : resultList) {
            assertEquals(ProductStatus.PENDING, laptop.getStatus());
        }
    }

    @Test
    public void findAllByUsername_validUsername_returnsTwoSmartphones() {
        List<SmartPhone> resultList = this.smartphoneService.findAllByUsername(this.user.getUsername());

        assertEquals(2, resultList.size());
    }

    @Test
    public void findAllByUsername_invalidUsername_returnsNoSmartphones() {
        List<SmartPhone> resultList = this.smartphoneService.findAllByUsername("invalidUsername");

        assertNull(resultList);
    }

    @Test
    public void findAllByUser_validUser_returnsTwoSmartphones() {
        List<SmartPhone> resultList = this.smartphoneService.findAllByUser(this.user);

        assertEquals(2, resultList.size());
    }

    @Test
    public void findAllByUser_invalidUser_returnsNoSmartphones() {
        User invalidUser = new User();
        invalidUser.setEmail("email2");
        invalidUser.setPassword("password2");
        invalidUser.setUsername("username2");
        invalidUser = this.userRepository.saveAndFlush(invalidUser);

        List<SmartPhone> resultList = this.smartphoneService.findAllByUser(invalidUser);

        assertEquals(0, resultList.size());
    }

    @Test
    public void deleteAllByUser_mixedSmartphones_twoSmartphonesRemainingInRepository() {
        this.smartphoneService.deleteAllByUser(this.user);

        List<SmartPhone> resultList = this.smartphoneRepository.findAll();

        assertEquals(2, resultList.size());
    }
}
