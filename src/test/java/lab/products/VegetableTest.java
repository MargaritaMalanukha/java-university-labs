package lab.products;

import com.lab.products.Purchaseable;
import com.lab.products.Vegetable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class VegetableTest {

    private final ArrayList<Purchaseable> listOfVegetable = new ArrayList<>();
    {
        listOfVegetable.addAll(Arrays.asList(Vegetable.values()));
    }

    @Test
    public void ToString_WithOnionAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfVegetable.get(4).toString();

        Assert.assertEquals("The title of product is: Onion, price: 5.0", result);
    }

}