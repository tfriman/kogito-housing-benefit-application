package demosoft.rules;

import java.util.Objects;

public class MaintenanceCostSurplus {
    private Double amount;

    public MaintenanceCostSurplus() {
    }

    public MaintenanceCostSurplus(Integer amount) {
        this.amount = Double.valueOf(amount);
        System.out.println("MaintenanceCostSurplus created with amount = " + amount);
    }

    public MaintenanceCostSurplus(Double amount) {
        this.amount = amount;
        System.out.println("MaintenanceCostSurplus created with amount = " + amount);
    }

    public boolean isActive() {
        return Objects.nonNull(amount) && amount.doubleValue() > 0;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MaintenanceCostSurplus{" +
                "amount=" + amount +
                '}';
    }
}
