package com.example.currencyexchangeervice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}")
    // where {fromCurrency} and {toCurrency} are path
    // variable
    // fromCurrency can be USD,EUR,AUD,INR and toCurrency
    // can be the opposite of any fromCurrency
    public ExchangeValue retrieveExchangeValue(@PathVariable String fromCurrency,
                                               @PathVariable String toCurrency)
    {
        // Here we need to write all of our business logic
        BigDecimal conversionMultiple = null;
        ExchangeValue exchangeValue = new ExchangeValue();
        if (fromCurrency != null && toCurrency != null) {
            if (fromCurrency.equalsIgnoreCase("USD")
                    && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(78);
            }
            if (fromCurrency.equalsIgnoreCase("USD")
                    && toCurrency.equalsIgnoreCase("VND")) {
                conversionMultiple = BigDecimal.valueOf(23000);
            }
            if (fromCurrency.equalsIgnoreCase("INR")
                    && toCurrency.equalsIgnoreCase("USD")) {
                conversionMultiple
                        = BigDecimal.valueOf(0.013);
            }
            if (fromCurrency.equalsIgnoreCase("EUR")
                    && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(82);
            }
            if (fromCurrency.equalsIgnoreCase("AUD")
                    && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(54);
            }
        }
        // setting the port
        exchangeValue = new ExchangeValue(
                1000L, fromCurrency, toCurrency,
                conversionMultiple);
        exchangeValue.setPort(Integer.parseInt(
                environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
