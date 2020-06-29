package demosoft.rules;

import java.time.LocalDate;

public class MaximumWaterPriceRequest {
    public LocalDate alkuPvm;

    public MaximumWaterPriceRequest() {
    }

    public MaximumWaterPriceRequest(LocalDate alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    public LocalDate getAlkuPvm() {
        return alkuPvm;
    }

    public void setAlkuPvm(LocalDate alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    @Override
    public String toString() {
        return "MaximumWaterPriceRequest{" +
                "alkuPvm=" + alkuPvm +
                '}';
    }
}
