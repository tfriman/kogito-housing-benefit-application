package demosoft.rules;

import java.math.BigDecimal;

public class MaximumWaterPrice {
    private BigDecimal maximum;

    public MaximumWaterPrice(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public MaximumWaterPrice(double d) {
        this.maximum = BigDecimal.valueOf(d);
    }

    public BigDecimal getMaximum() {
        return maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    @Override
    public String toString() {
        return "MaximumWaterPrice{" +
                "maximum=" + maximum +
                '}';
    }
}
