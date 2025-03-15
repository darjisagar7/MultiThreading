package practice.oddeven;

public class OddEvenJavaThreading {

    public static void main(String[] args) {
        final State state = new State(PrinterType.ODD);

        final Printer oddPrinter = new Printer(1, 2, state, PrinterType.ODD, PrinterType.EVEN, 50);
        final Printer evemPrinter = new Printer(2, 2, state, PrinterType.EVEN, PrinterType.ODD, 50);

        final Thread oddThread = new Thread(oddPrinter);
        final Thread evenThread = new Thread(evemPrinter);

        oddThread.start();
        evenThread.start();
    }
}
