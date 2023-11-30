package com.student.pantry.studentPantry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.student.pantry.studentPantry.dto.OrderHistoryDto;
import com.student.pantry.studentPantry.entity.OrderHistory;
import com.student.pantry.studentPantry.repository.OrderHistoryRepository;
import com.student.pantry.studentPantry.repository.ShoppingCartRepository;
import com.student.pantry.studentPantry.response.OrderHistoryResponse;
import com.student.pantry.studentPantry.service.EmailService;
import com.student.pantry.studentPantry.service.OrderHistoryService;
import com.student.pantry.studentPantry.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class OrderHistoryServiceTest {

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private OrderHistoryService orderHistoryService;


    @Test
    void testCreateOrder_PlacementFrequencyExceeded() {
        // Arrange
        OrderHistoryDto orderHistoryDto = createValidOrderHistoryDto();
        when(orderHistoryRepository.findByUserIdAndOrderPlacedDateBetween(anyLong(), any(), any()))
            .thenReturn(List.of(new OrderHistory(), new OrderHistory())); // Simulate two orders already placed
       
        // Act
        OrderHistoryResponse response = orderHistoryService.createOrder(orderHistoryDto);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getMessage());
        //assertEquals("Cannot place order!! User has already placed two orders for this month", response.getMessage());
    }

    @Test
    void testCreateOrder_ExceedsProductLimit() {
        // Arrange
        OrderHistoryDto orderHistoryDto = createOrderHistoryDtoWithExceededProductLimit();

        // Act
        OrderHistoryResponse response = orderHistoryService.createOrder(orderHistoryDto);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getMessage());
        assertEquals("Cannot place order!! Number of items exceeded 15", response.getMessage());
    }

    // Additional tests for other scenarios, error handling, etc.

    private OrderHistoryDto createValidOrderHistoryDto() {
        OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
        orderHistoryDto.setUserId(1L);
        orderHistoryDto.setItems(createValidItems());
        orderHistoryDto.setOrderPlacedDate(LocalDateTime.now());
        return orderHistoryDto;
    }

    private Map<Long, Integer> createValidItems() {
        Map<Long, Integer> items = new HashMap<>();
        items.put(1L, 2);
        items.put(2L, 3);
        return items;
    }

    private OrderHistoryDto createOrderHistoryDtoWithExceededProductLimit() {
        OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
        orderHistoryDto.setUserId(1L);
        orderHistoryDto.setItems(createTooManyItems());
        orderHistoryDto.setOrderPlacedDate(LocalDateTime.now());
        return orderHistoryDto;
    }

    private Map<Long, Integer> createTooManyItems() {
        Map<Long, Integer> items = new HashMap<>();
        for (long i = 1; i <= 20; i++) {
            items.put(i, 1);
        }
        return items;
    }
}
