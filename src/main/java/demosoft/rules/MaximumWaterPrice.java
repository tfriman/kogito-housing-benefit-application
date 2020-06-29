package demosoft.rules;

public class MaximumWaterPrice {
    private Double maximum;

    public MaximumWaterPrice(Integer in) {
        this.maximum = Double.valueOf(in);
    }
    public MaximumWaterPrice(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    @Override
    public String toString() {
        return "MaximumWaterPrice{" +
                "maximum=" + maximum +
                '}';
    }
}
