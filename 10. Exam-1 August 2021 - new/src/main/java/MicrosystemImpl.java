import java.util.ArrayList;
import java.util.List;

public class MicrosystemImpl implements Microsystem {
    private List<Computer> computers;

    public MicrosystemImpl() {
        this.computers = new ArrayList<>();
    }

    @Override
    public void createComputer(Computer computer) {
        if (this.getComputer(computer.getNumber()) != null)
            throw new IllegalArgumentException();

        this.computers.add(computer);
    }

    @Override
    public boolean contains(int number) {
        return this.getComputer(number) != null;
    }

    @Override
    public int count() {
        return this.computers.size();
    }

    @Override
    public Computer getComputer(int number) {
        return this.computers
                .stream()
                .filter(computer -> computer.getNumber() == number)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void remove(int number) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeWithBrand(Brand brand) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void upgradeRam(int ram, int number) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Computer> getAllFromBrand(Brand brand) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Computer> getAllWithScreenSize(double screenSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Computer> getAllWithColor(String color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Computer> getInRangePrice(double minPrice, double maxPrice) {
        throw new UnsupportedOperationException();
    }
}
