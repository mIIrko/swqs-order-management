package de.htwg.swqs.order.payment;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CurrencyConverterServiceTest {

    private CurrencyConverterService currencyConverterService;

    @Before
    public void setUp() {
        this.currencyConverterService = new CurrencyConverterServiceImpl();
    }

    @Test
    public void testConvertToWithTwoDifferentCurrencies() {
        // setup
        Currency currencyFrom = Currency.getInstance("EUR");
        Currency currencyTo = Currency.getInstance("USD");
        BigDecimal amount = new BigDecimal("4.14");

        // execute
        BigDecimal retrievedValue = this.currencyConverterService.convertTo(currencyFrom, currencyTo, amount);

        // verify
        assertNotEquals(amount, retrievedValue);
    }

    @Test
    public void testConvertToWithTwoSameCurrencies() {
        // setup
        String currencyCode = "EUR";
        Currency currencyFrom = Currency.getInstance(currencyCode);
        Currency currencyTo = Currency.getInstance(currencyCode);
        BigDecimal amount = new BigDecimal("4.14");

        // execute
        BigDecimal retrievedValue = this.currencyConverterService.convertTo(currencyFrom, currencyTo, amount);

        // verify
        assertEquals(amount, retrievedValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToWithNegativeAmount() {
        // setup
        Currency currencyFrom = Currency.getInstance("EUR");
        Currency currencyTo = Currency.getInstance("USD");
        BigDecimal amount = new BigDecimal("-3.14");
        // execute
        BigDecimal retrievedValue = this.currencyConverterService.convertTo(currencyFrom, currencyTo, amount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToWithZeroAmount() {
        // setup
        Currency currencyFrom = Currency.getInstance("EUR");
        Currency currencyTo = Currency.getInstance("USD");
        BigDecimal amount = new BigDecimal("0.00");
        // execute
        BigDecimal retrievedValue = this.currencyConverterService.convertTo(currencyFrom, currencyTo, amount);
    }

}
