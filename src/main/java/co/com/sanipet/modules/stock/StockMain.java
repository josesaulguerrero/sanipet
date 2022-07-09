package co.com.sanipet.modules.stock;

import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;

public class StockMain {
    public static void main() {
        Integer selectedOption = Integer.valueOf(ConsoleMenu.renderAndVerify(
                (option) -> NumberUtils.isParsable(option) && Range.between(1,4).contains(Integer.parseInt(option.trim())),
                "Enter one of the following options:",
                "1. Add new element to stock", "2. Remove element from stock",
                "3. Modify the amount of available units for an element",
                "4. Print a list with all the elements in stock."
        ));
    }

    private static void pickOption(Integer option) {
        if(option.equals(1)) {
            // addElementToStock();
        } else if (option.equals(2)) {
            // removeElementFromStock();
        } else if (option.equals(3)) {
            // modifyAmountOfUnits();
        } else {
            // printListOfElements();
        }
    }
}
