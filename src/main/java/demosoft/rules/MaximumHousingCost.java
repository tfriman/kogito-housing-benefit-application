package demosoft.rules;

import java.math.BigDecimal;

public class MaximumHousingCost {
    private BigDecimal cost;

    public MaximumHousingCost(BigDecimal cost) {
        this.cost = cost;
    }

    public MaximumHousingCost(double cost) {
        this.cost = BigDecimal.valueOf(cost);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "MaximumHousingCost{" +
                "cost=" + cost +
                '}';
    }
}
