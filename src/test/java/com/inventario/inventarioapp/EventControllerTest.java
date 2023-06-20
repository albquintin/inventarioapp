package com.inventario.inventarioapp;

import com.inventario.inventarioapp.controller.EventController;
import com.inventario.inventarioapp.dto.EventDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventControllerTest {

    EventController eventController;
    EventDto eventDto;

    @BeforeEach
    public void setupBeforeEach(){
        eventController = new EventController(null, null, null, null);
        eventDto = new EventDto();
    }

    @Test
    @Order(0)
    public void testOnePaymentMatch(){
        eventDto.setPrice(1000f);
        eventDto.setFirstPayment(1000f);
        assertTrue(eventController.checkPaymentsMatch(eventDto));
    }
    @Test
    @Order(1)
    public void testTwoPaymentsMatch(){
        eventDto.setPrice(1000f);
        eventDto.setFirstPayment(500f);
        eventDto.setSecondPayment(500f);
        assertTrue(eventController.checkPaymentsMatch(eventDto));
    }

    @Test
    @Order(2)
    public void testThreePaymentsMatch(){
        eventDto.setPrice(1000f);
        eventDto.setFirstPayment(500f);
        eventDto.setSecondPayment(250f);
        eventDto.setThirdPayment(250f);
        assertTrue(eventController.checkPaymentsMatch(eventDto));
    }
    @Test
    @Order(3)
    public void testSecondPaymentEmptyThirdPaymentPresent(){
        eventDto.setPrice(1000f);
        eventDto.setFirstPayment(500f);
        eventDto.setThirdPayment(500f);
        assertFalse(eventController.checkPaymentsMatch(eventDto));
    }

    @Test
    @Order(4)
    void testTwoDatesAreInOrder(){
        eventDto.setFirstPaymentDay(LocalDate.of(2023, 4, 4));
        eventDto.setSecondPaymentDay(LocalDate.of(2023, 4, 24));
        assertTrue(eventController.checkDatesAreInOrder(eventDto));
    }
    @Test
    @Order(5)
    void testTwoDatesAreNotInOrder(){
        eventDto.setFirstPaymentDay(LocalDate.of(2023, 4, 4));
        eventDto.setSecondPaymentDay(LocalDate.of(2023, 3, 24));
        assertFalse(eventController.checkDatesAreInOrder(eventDto));
    }
    @Test
    @Order(6)
    void testThreeDatesAreInOrder(){
        eventDto.setFirstPaymentDay(LocalDate.of(2023, 4, 4));
        eventDto.setSecondPaymentDay(LocalDate.of(2023, 4, 24));
        eventDto.setThirdPaymentDay(LocalDate.of(2023, 5, 15));
        assertTrue(eventController.checkDatesAreInOrder(eventDto));
    }
    @Test
    @Order(7)
    void testThreeDatesAreNotInOrder(){
        eventDto.setFirstPaymentDay(LocalDate.of(2023, 4, 4));
        eventDto.setSecondPaymentDay(LocalDate.of(2023, 4, 24));
        eventDto.setThirdPaymentDay(LocalDate.of(2023, 4, 15));
        assertFalse(eventController.checkDatesAreInOrder(eventDto));
    }
    @Test
    @Order(8)
    void testSecondDateEmptyThirdDatePresent(){
        eventDto.setFirstPaymentDay(LocalDate.of(2023, 4, 4));
        eventDto.setThirdPaymentDay(LocalDate.of(2023, 4, 15));
        assertFalse(eventController.checkDatesAreInOrder(eventDto));
    }
}
