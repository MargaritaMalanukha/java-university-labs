package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.BasketItem;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.BasketItemRepository;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.margomalanuha.spring.labs.repository.PurchaseRepository;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTest {

    private PurchaseRepository purchaseRepository;
    private ProductRepository productRepository;
    private BasketItemRepository basketItemRepository;
    private PurchaseServiceImpl purchaseService;

    @Before
    public void mock() {
        purchaseRepository = Mockito.mock(PurchaseRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        basketItemRepository = Mockito.mock(BasketItemRepository.class);
        purchaseRepository.setTest();
        productRepository.setTest();
        basketItemRepository.setTest();

        purchaseService = new PurchaseServiceImpl(basketItemRepository, productRepository, purchaseRepository);
    }

    @Test
    public void addToBasket_whenDataIsCorrect_CreateNewRecordInDB() {
        //GIVEN
        int userId = 15;
        User user = new User();
        user.setId(userId);
        Product product = new Product(1, "milk", 25, 1);
        Mockito.doReturn(1).when(basketItemRepository).create(new BasketItem(product.getId(), userId));

        //WHEN
        purchaseService.addToBasket(product, user);

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).create(new BasketItem(product.getId(), userId));

    }

    @Test
    public void deleteFromBasket_whenExists_DeleteRecordFromDB() {
        //GIVEN
        List<BasketItem> basketItems = new LinkedList<>();
        basketItems.add(new BasketItem(1, 2, 1));
        basketItems.add(new BasketItem(2, 2, 1));
        basketItems.add(new BasketItem(3, 3, 1));
        User user = new User();
        user.setId(1);
        Product product = new Product();
        product.setId(2);
        Mockito.doReturn(basketItems).when(basketItemRepository).getAll();
        Mockito.doReturn(1).when(basketItemRepository).delete(1);

        //WHEN
        purchaseService.deleteFromBasket(product, user);

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).getAll();
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(1);
    }

    @Test
    public void getBasket_whenUserExists_returnBasketItemsOfThisUser() {
        //GIVEN
        User user = new User();
        user.setId(1);
        List <BasketItem> basketItems = new LinkedList<>();
        basketItems.add(new BasketItem(1, 1, 1));
        basketItems.add(new BasketItem(2, 2, 1));
        basketItems.add(new BasketItem(3, 5, 1));
        basketItems.add(new BasketItem(4, 2, 2));
        List<Product> products = new LinkedList<>();
        products.add(new Product(1, "milk", 25, 1));
        products.add(new Product(2, "kefir", 30, 1));
        products.add(new Product(5, "yogurt", 40, 1));

        Mockito.doReturn(basketItems).when(basketItemRepository).getAll();
        Mockito.doReturn(products.get(0)).when(productRepository).getById(1);
        Mockito.doReturn(products.get(1)).when(productRepository).getById(2);
        Mockito.doReturn(products.get(2)).when(productRepository).getById(5);

        //WHEN
        List<Product> actual = purchaseService.getBasket(user);

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).getAll();
        Mockito.verify(productRepository, Mockito.times(1)).getById(1);
        Assertions.assertEquals(products, actual);
    }

    @Test
    public void clearBasket_whenNotEmpty_DeleteRecordsFromDatabase() {
        //GIVEN
        List<BasketItem> basketItems = new LinkedList<>();
        basketItems.add(new BasketItem(1, 1, 1));
        basketItems.add(new BasketItem(2, 2, 1));
        basketItems.add(new BasketItem(3, 5, 1));
        basketItems.add(new BasketItem(4, 2, 2));
        Mockito.doReturn(basketItems).when(basketItemRepository).getAll();
        Mockito.doReturn(1).when(basketItemRepository).delete(1);
        Mockito.doReturn(1).when(basketItemRepository).delete(2);
        Mockito.doReturn(1).when(basketItemRepository).delete(3);
        User user = new User();
        user.setId(1);

        //WHEN
        purchaseService.clearBasket(user);

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).getAll();
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(1);
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(2);
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(3);
    }

    @Test
    public void getPurchaseHistory_whenPurchasesAreInDatabase_returnAllPurchasesForUser() {
        //GIVEN
        List<Purchase> purchases = new LinkedList<>();
        purchases.add(new Purchase(1, 200, ""));
        purchases.add(new Purchase(1, 150, ""));
        purchases.add(new Purchase(2, 300, ""));
        purchases.add(new Purchase(1, 160,""));
        Mockito.doReturn(purchases).when(purchaseRepository).getAll();
        User user = new User();
        user.setId(1);

        //WHEN
        List<Purchase> actual = purchaseService.getPurchaseHistory(user);

        //THEN
        Mockito.verify(purchaseRepository, Mockito.times(1)).getAll();
        purchases.remove(2);
        Assertions.assertEquals(purchases, actual);
    }

}