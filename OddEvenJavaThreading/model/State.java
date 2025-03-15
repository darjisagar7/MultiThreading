package practice.oddeven;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State {
    private PrinterType lastPrinterType;

    public State(PrinterType printerType) {
        lastPrinterType = printerType;
    }
}
