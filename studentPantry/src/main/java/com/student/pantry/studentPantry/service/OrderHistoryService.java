package com.student.pantry.studentPantry.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.pantry.studentPantry.dto.OrderHistoryDto;
import com.student.pantry.studentPantry.entity.OrderHistory;
import com.student.pantry.studentPantry.entity.Products;
import com.student.pantry.studentPantry.repository.OrderHistoryRepository;
import com.student.pantry.studentPantry.repository.ProductJpa;
import com.student.pantry.studentPantry.repository.ShoppingCartRepository;
import com.student.pantry.studentPantry.response.OrderHistoryResponse;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    
   private final EmailService emailService;
    
    private final UserServiceImpl userServiceImpl;
    private final ProductJpa productJpa;


    @Autowired
    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository, ShoppingCartRepository shoppingCartRepository,UserServiceImpl userServiceImpl,EmailService emailService,ProductJpa productJpa) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.shoppingCartRepository=shoppingCartRepository;
        this.emailService=emailService;
        this.userServiceImpl=userServiceImpl;
        this.productJpa=productJpa;
    }

    @Transactional
    public OrderHistoryResponse createOrder(OrderHistoryDto orderHistoryDto) {
        OrderHistoryDto orderPlaced=null;
        OrderHistoryResponse response=null;
        String userEmail=null;
        // Validate order placement frequency
        if(!validateOrderPlacementFrequency(orderHistoryDto.getUserId(), orderHistoryDto.getOrderPlacedDate())){
         response= new OrderHistoryResponse("Cannot Place order!!User has already placed two orders for this month", orderPlaced);

        }
        else{
        // Validate the number of products in the current order history
        if(validateNumberOfProducts(orderHistoryDto.getItems())){

        // Convert DTO to Entity and save to the database
        OrderHistory orderHistoryEntity = convertDtoToEntity(orderHistoryDto);
       // boolean updateQuantity= updateProductQuantity(orderHistoryEntity.getItems());
        /*if(!updateQuantity){
             response=new OrderHistoryResponse("Some product in the order are invalid!!", orderPlaced);
             return response;

        }*/
        OrderHistory savedOrder = orderHistoryRepository.save(orderHistoryEntity);
        orderPlaced=convertEntityToDto(savedOrder);
        shoppingCartRepository.deleteByUserID(savedOrder.getUserId());
                 response= new OrderHistoryResponse("Order placed successfully", orderPlaced);

                 userEmail = userServiceImpl.getUserDetailsByUserId(savedOrder.getUserId().longValue());
                 if(userEmail!=null && savedOrder.getOrderId()!=null) {
                	 emailService.sendOrderConfirmation(userEmail, savedOrder.getOrderId());
                 }

        }
        else{
            response=new OrderHistoryResponse("Cannot place order!! Number of items exceeded 15", orderPlaced);
        }
    }
        return response;
        // Convert the saved entity back to DTO for response
       
    }

    public List<OrderHistoryDto> getOrderHistoryForUser(Long userId) {
        // Retrieve order history for a user from the database
        List<OrderHistory> userOrderHistory = orderHistoryRepository.findByUserId(userId);

        // Convert entities to DTOs for response
        return convertEntitiesToDtos(userOrderHistory);
    }

    // Other methods for updating, deleting, or retrieving order history

    // Additional private methods for validation, conversion, etc.

    private boolean validateOrderPlacementFrequency(Long userId, LocalDateTime orderPlacedDate) {
        // Get order history for the current month
        List<OrderHistory> userOrderHistoryForMonth = this.findOrdersByUserIdAndMonth(userId, orderPlacedDate.getMonthValue());

        // Check if the user has placed orders more than twice in the current month
        if (userOrderHistoryForMonth.size() >= 2) {
            return false;
        }
        return true;
    }

    private boolean validateNumberOfProducts(Map<Long, Integer> items) {
        // Check if the current order history has only 15 products
        if (items.size() > 15) {
            return false;
        }
        return true;
    }

    @Transactional
    private boolean updateProductQuantity(Map<Long, Integer> items) {
        try{

            List<Products> products=new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            Long productId = entry.getKey();
            Integer newQuantity = entry.getValue();
            Optional<Products> product=productJpa.findById(productId);
            System.out.println("prduct+" + product.toString());
            if(product.isPresent()){
                int oldQuantity=product.get().getProductQuantity();
                Products newProduct=product.get();
                newProduct.setProductQuantity(oldQuantity-newQuantity);
                System.out.println("prduct+" + newProduct.toString());
                products.add(newProduct);
                 System.out.println("prduct list+" + products);
                //productJpa.save(product.get());
            }
            else{
                System.err.println("Product does not exist");
                return false;
            }
            
        }
        productJpa.saveAll(products);
        
        return true; 
    }
    catch(Exception e){
        System.out.println("Exception + "+e.getLocalizedMessage());
        return false;
    }
    }
    
    public List<OrderHistory> findOrdersByUserIdAndMonth(Long userId, int month) {
        LocalDateTime start = LocalDateTime.of(LocalDate.now().getYear(), month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1);

        return orderHistoryRepository.findByUserIdAndOrderPlacedDateBetween(userId, start, end);
    }

    private OrderHistory convertDtoToEntity(OrderHistoryDto orderHistoryDto) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setUserId(orderHistoryDto.getUserId());
        orderHistory.setItems(orderHistoryDto.getItems());
        orderHistory.setOrderPlacedDate(orderHistoryDto.getOrderPlacedDate());
        // Other mappings if any
        return orderHistory;
    }

    private OrderHistoryDto convertEntityToDto(OrderHistory orderHistory) {
        OrderHistoryDto orderHistoryDto = new OrderHistoryDto();
        orderHistoryDto.setOrderId(orderHistory.getOrderId());
        orderHistoryDto.setUserId(orderHistory.getUserId());
        orderHistoryDto.setItems(orderHistory.getItems());
        orderHistoryDto.setOrderPlacedDate(orderHistory.getOrderPlacedDate());
        // Other mappings if any
        return orderHistoryDto;
    }

    private List<OrderHistoryDto> convertEntitiesToDtos(List<OrderHistory> orderHistoryEntities) {
        return orderHistoryEntities.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
