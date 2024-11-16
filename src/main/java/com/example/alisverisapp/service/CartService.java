package com.example.alisverisapp.service;

import com.example.alisverisapp.model.Cart;
import com.example.alisverisapp.model.Product;
import com.example.alisverisapp.repository.CartRepository;
import com.example.alisverisapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet Boş!"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Ürün yok!"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Stok Tükendi :( ");
        }

        cart.getProducts().merge(product, quantity, Integer::sum);
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        double totalPrice = cart.getProducts().entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public Cart updateCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet boş"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Ürün Yok"));

        if (quantity <= 0) {
            cart.getProducts().remove(product);
        } else {

            if (product.getStock() + cart.getProducts().getOrDefault(product, 0) < quantity) {
                throw new RuntimeException("Stok yok");
            }

            int currentQuantity = cart.getProducts().getOrDefault(product, 0);
            int stockAdjustment = quantity - currentQuantity;

            cart.getProducts().put(product, quantity);
            product.setStock(product.getStock() - stockAdjustment);
            productRepository.save(product);
        }

        double totalPrice = calculateTotalPrice(cart);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    private double calculateTotalPrice(Cart cart) {
        return cart.getProducts().entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet boş!"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Ürün yok!"));

        if (cart.getProducts().containsKey(product)) {
            int quantity = cart.getProducts().remove(product);
            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Sepette Ürün yok!");
        }

        double totalPrice = calculateTotalPrice(cart);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }


    public Cart emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Sepet Yok!"));
        cart.getProducts().clear();
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }
}
