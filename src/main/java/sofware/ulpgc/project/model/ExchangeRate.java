package sofware.ulpgc.project.model;

import java.time.LocalDate;

public record ExchangeRate(LocalDate moment, Currency from, Currency to,double rate) {

}
