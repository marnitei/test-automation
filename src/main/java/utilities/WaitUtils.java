package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

public enum WaitUtils {

    visible(ExpectedConditions::visibilityOfElementLocated),
    clickable(ExpectedConditions::elementToBeClickable),
    invisible(ExpectedConditions::invisibilityOfElementLocated);

    WaitUtils(Function<By, ExpectedCondition<?>> type) {
        this.type = type;
    }

    public Function<By, ExpectedCondition<?>> getType() {
        return type;
    }

    private final Function<By, ExpectedCondition<?>> type;
}
