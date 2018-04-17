package de.htwg.swqs.order.shippingcost;

import de.htwg.swqs.order.model.CustomerInfo;
import de.htwg.swqs.order.model.OrderItem;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShippingCostServiceImpl implements ShippingCostService {

  private static final String DEFAULT_COUNTRY = "DE";

  /**
   * Calculates the shipping costs for a given list of ShoppingItems and a CustomerInfo. If the
   * customer lives outside of the default country, an additional charge for international shipping
   * will be added
   *
   * @param customerInfo The info about the name and address of the customer
   * @param orderItems A list with the ordered ShoppingCartItems
   * @return A BigDecimal containing the shipping costs
   */
  public BigDecimal calculateShippingCosts(CustomerInfo customerInfo, List<OrderItem> orderItems) {

    int amountOfItems = orderItems.size();
    BigDecimal internationalShippingExtraCost =
        customerInfo.getIsoCountryCode().equals(DEFAULT_COUNTRY) ? new BigDecimal("0")
            : new BigDecimal("10.99");
    BigDecimal shippingCost = new BigDecimal("0.00");

    if (amountOfItems < 3) {
      shippingCost = new BigDecimal("3.99");
    } else if (amountOfItems < 6) {
      shippingCost = new BigDecimal("5.99");
    } else if (amountOfItems < 10) {
      shippingCost = new BigDecimal("8.99");
    } else {
      shippingCost = new BigDecimal("13.99");
    }

    return internationalShippingExtraCost.add(shippingCost);
  }
}
