package com.sample.ecommerce.services.impl;

import com.sample.ecommerce.dtos.AddressDto;
import com.sample.ecommerce.dtos.CartDto;
import com.sample.ecommerce.entities.*;
import com.sample.ecommerce.exceptions.CartException;
import com.sample.ecommerce.exceptions.OrderException;
import com.sample.ecommerce.exceptions.ProductException;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.BeanValidator;
import com.sample.ecommerce.helpers.Validator;
import com.sample.ecommerce.repositories.*;
import com.sample.ecommerce.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Address addAddress(AddressDto addressDto) {
        User loggedInUser = getLoggedInUser();
        Address address = new Address(addressDto.getTitle(), addressDto.getStreet(), addressDto.getCity(), addressDto.getState(), addressDto.getCountry(), addressDto.getZip());
        address.setUser(loggedInUser);
        return addressRepository.save(address);
    }

    @Transactional
    @Override
    public Cart createOrUpdateCart(CartDto cartDto) throws ProductException, CartException {
        String errorMessage = BeanValidator.isValidCartDetails(cartDto);
        if (errorMessage != null) {
            throw new CartException(errorMessage);
        }
        User loggedInUser = getLoggedInUser();
        Optional<Cart> existingCart = cartRepository.findByUserId(loggedInUser.getId());
        Long existingCartId = existingCart.map(Cart::getId).orElse(null);
        if (Validator.isEmpty(cartDto.getCartItems())) {
            return cartRepository.save(new Cart(existingCartId, loggedInUser, null, 0.0));
        }
        List<Item> productsAndQuantity = getProductsAndQuantity(cartDto.getCartItems());
        List<CartItem> cartItems = productsAndQuantity.stream().map(item -> new CartItem(item.getProduct(), item.getQuantity())).toList();
        Cart cart = new Cart(existingCartId, loggedInUser, cartItems, getTotalValueOfItems(productsAndQuantity));
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public Order createOrder(Long shippingAddressId) throws CartException, UserException {
        User loggedInUser = getLoggedInUser();
        Cart cart = cartRepository.findByUserId(loggedInUser.getId()).orElseThrow(() -> new CartException("Empty Cart"));
        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> new OrderItem(cartItem.getProduct(), cartItem.getQuantity())).toList();
        Address address = getAddress(shippingAddressId, loggedInUser.getId());
        Order order = new Order(loggedInUser, orderItems, cart.getTotalValue(), address, OrderStatus.CREATED);
        loggedInUser.setCart(null);
        userRepository.save(loggedInUser);
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty() || (!order.get().getUser().getId().equals(getLoggedInUser().getId()))) {
            throw new OrderException("Order Not Found");
        }
        if (OrderStatus.CANCELLED.equals(order.get().getStatus()) || OrderStatus.DELIVERED.equals(order.get().getStatus())) {
            throw new OrderException("Order Already " + order.get().getStatus());
        }
        order.get().setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order.get());
    }

    private User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private List<Item> getProductsAndQuantity(Map<Long, Integer> cartItems) throws ProductException {
        List<Item> items = new ArrayList<>();
        for (Map.Entry<Long, Integer> item : cartItems.entrySet()) {
            Optional<Product> product = productRepository.findById(item.getKey());
            if (product.isEmpty()) {
                throw new ProductException("Product Not Found");
            }
            items.add(new Item(product.get(), cartItems.get(item.getKey())));
        }
        return items;
    }

    private double getTotalValueOfItems(Collection<Item> items) {
        double total = items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).reduce(0.0, Double::sum);
        BigDecimal totalRounded = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
        return totalRounded.doubleValue();
    }

    private Address getAddress(Long shippingAddressId, Long userId) throws UserException {
        return addressRepository.findAddressByUserIdAndAddressId(shippingAddressId, userId).orElseThrow(() -> new UserException("Address Not Found"));
    }
}
