package practice.oddeven;

import lombok.SneakyThrows;

public class Printer implements Runnable {
    private int currentValue;
    private final int jump;
    private final State state;
    private final PrinterType printerType;
    private final PrinterType nextPrinterType;
    private final int maxValue;

    public Printer(final int startValue,
                   final int jump,
                   final State state,
                   final PrinterType printerType,
                   final PrinterType nextPrinterType,
                   final int maxValue) {
        this.currentValue = startValue;
        this.jump = jump;
        this.state = state;
        this.printerType = printerType;
        this.nextPrinterType = nextPrinterType;
        this.maxValue = maxValue;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (currentValue <= maxValue) {
            synchronized (state) {
                while (this.printerType != state.getLastPrinterType()) {
                    state.wait();
                }

                System.out.println(printerType.toString() + " : " + currentValue);
                currentValue += jump;
                state.setLastPrinterType(nextPrinterType);
                Thread.sleep(1000);
                state.notifyAll();
            }
        }
    }
}
