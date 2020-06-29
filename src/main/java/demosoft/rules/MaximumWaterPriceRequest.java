package demosoft.rules;

import java.util.Date;

public class MaximumWaterPriceRequest {
    public Date alkuPvm;

    public MaximumWaterPriceRequest() {
    }

    public MaximumWaterPriceRequest(Date alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    public Date getAlkuPvm() {
        return alkuPvm;
    }

    public void setAlkuPvm(Date alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

    @Override
    public String toString() {
        return "MaximumWaterPriceRequest{" +
                "alkuPvm=" + alkuPvm +
                '}';
    }
}
