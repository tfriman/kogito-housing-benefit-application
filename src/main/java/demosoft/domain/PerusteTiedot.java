package demosoft.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public abstract class PerusteTiedot {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate alkuPvm;

    public LocalDate getAlkuPvm() {
        return alkuPvm;
    }

    public void setAlkuPvm(LocalDate alkuPvm) {
        this.alkuPvm = alkuPvm;
    }

}
