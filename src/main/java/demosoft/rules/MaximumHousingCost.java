package demosoft.rules;

public class MaximumHousingCost {
    private Double cost;

    public MaximumHousingCost(Integer cost) {
        this.cost = Double.valueOf(cost);
    }
    public MaximumHousingCost(Double cost) {
        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "MaximumHousingCost{" +
                "cost=" + cost +
                '}';
    }
}
