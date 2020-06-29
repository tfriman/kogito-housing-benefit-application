package demosoft.rules;

public class MaintenanceCostAccepted {
    private Double amount;

    public MaintenanceCostAccepted() {
    }

    public MaintenanceCostAccepted(Integer amount) {
        this.amount = Double.valueOf(amount);
    }

    public MaintenanceCostAccepted(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MaintenanceCostAccepted{" +
                "amount=" + amount +
                '}';
    }
}
