package com.pietrantuono.bjss;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import converter.CurrencyConverter;
import model.classes.ShoppingItem;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CurrencyConverterTest {

    @Test
    public void itemInGbp()  {
        BigDecimal price = new BigDecimal("0.1234");
        ShoppingItem shoppingItem= new ShoppingItem("foo",price,"unity");
        shoppingItem.increase();
        BigDecimal priceCalculated = CurrencyConverter.itemInGbp(shoppingItem);
        assertEquals(price, priceCalculated);
        //Litmus
        assertTrue(price.toString().equalsIgnoreCase(priceCalculated.toString()));
    }

    @Test
    public void itemFromGbpToCurr()  {
        BigDecimal price = new BigDecimal("0.1234");
        ShoppingItem shoppingItem= new ShoppingItem("foo",price,"unity");
        shoppingItem.increase();
        BigDecimal rate=new BigDecimal("0.1111");
        BigDecimal priceCalculated = CurrencyConverter.itemFromGbpToCurr(shoppingItem,rate);
        BigDecimal expected=new BigDecimal("0.0137");
        assertEquals(expected, priceCalculated);
        //Litmus
        assertTrue(expected.toString().equalsIgnoreCase(priceCalculated.toString()));
    }

    @Test
    public void calculateTotalInCurr()  {
        ArrayList<ShoppingItem> shoppingItems= new ArrayList<>();
        BigDecimal price = new BigDecimal("0.1234");
        ShoppingItem item1= new ShoppingItem("foo",price,"unity");
        item1.increase();
        shoppingItems.add(item1);

        BigDecimal price2 = new BigDecimal("0.0001");
        ShoppingItem item2= new ShoppingItem("bar",price2,"unity");
        item2.increase();
        item2.increase();
        shoppingItems.add(item2);

        BigDecimal rate = new BigDecimal("2.001");
        BigDecimal priceCalculated = CurrencyConverter.calculateTotalInCurr(shoppingItems, rate);
        //0.1234x2.001=0.2469
        //0.0001x2.001x2=0.0004002
        //=0.2473
        BigDecimal expected=new BigDecimal("0.2473");
        assertEquals(expected, priceCalculated);
        //Litmus
        assertTrue(expected.toString().equalsIgnoreCase(priceCalculated.toString()));
    }

    @Test
    public void calculateTotalInGbp()  {
        ArrayList<ShoppingItem> shoppingItems= new ArrayList<>();
        BigDecimal price = new BigDecimal("0.1234");
        ShoppingItem item1= new ShoppingItem("foo",price,"unity");
        item1.increase();
        shoppingItems.add(item1);

        BigDecimal price2 = new BigDecimal("0.0001");
        ShoppingItem item2= new ShoppingItem("bar",price2,"unity");
        item2.increase();
        item2.increase();
        shoppingItems.add(item2);

        BigDecimal rate = new BigDecimal("2.001");
        BigDecimal priceCalculated = CurrencyConverter.calculateTotalInGbp(shoppingItems);
        //0.1234
        //0.0001x2=0.0002
        //=0.1236
        BigDecimal expected=new BigDecimal("0.1236");
        assertEquals(expected, priceCalculated);
        //Litmus
        assertTrue(expected.toString().equalsIgnoreCase(priceCalculated.toString()));
    }

    @Test
    public void fromGbpToCurr()  {
        ArrayList<ShoppingItem> shoppingItems= new ArrayList<>();
        BigDecimal price = new BigDecimal("0.1234");
        BigDecimal rate = new BigDecimal("2.001");
        BigDecimal priceCalculated = CurrencyConverter.fromGbpToCurr(price,rate);

        BigDecimal expected=new BigDecimal("0.2469");
        assertEquals(expected, priceCalculated);
        //Litmus
        assertTrue(expected.toString().equalsIgnoreCase(priceCalculated.toString()));
    }
    @Test
    public void test2(){
        BigDecimal priceinGbp = new BigDecimal("0.1111");
        ShoppingItem shoppingItem= new ShoppingItem("foo",priceinGbp,"bar");
        shoppingItem.increase();
        shoppingItem.increase();
        BigDecimal rate= new BigDecimal("1");
        BigDecimal priceInCurr = CurrencyConverter.fromGbpToCurr(priceinGbp, rate);
        assertEquals(priceInCurr.toString(),"0.1111");
        BigDecimal result = CurrencyConverter.itemFromGbpToCurr(shoppingItem, rate);
        assertEquals(result.toString(),"0.2222");
    }

}