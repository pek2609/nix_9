package ua.com.alevel;

public class MathSet extends MyUniqueList<Number> {

    public MathSet() {
        super();
    }

    public MathSet(int maxCapacity) {
        super(maxCapacity);
    }

    public MathSet(Number[] numbers) {
        super(numbers);
    }

    public MathSet(Number[]... numbers) {
        this(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            MathSet other = new MathSet(numbers[i]);
            join(other);
        }
    }

    public MathSet(MathSet numbers) {
        super(numbers.toArray());
        setMaxCapacity(numbers.getMaxCapacity());
    }

    public MathSet(MathSet... numbers) {
        this(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            join(numbers[i]);
        }
    }

    public void add(Number number) {
        super.add(number);
    }

    public void add(Number... numbers) {
        for (Number number : numbers) {
            add(number);
        }
    }

    public void join(MathSet ms) {
        for (Number number : ms.toArray()) {
            try {
                add(number);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Warn! You can lose some numbers from joined MathSet, because the capacity is full");
            }
        }
    }

    public void join(MathSet... mss) {
        for (MathSet ms : mss) {
            join(ms);
        }
    }

    public void intersection(MathSet ms) {
        for (Number number : this) {
            if (!ms.contains(number)) {
                remove(number);
            }
        }
    }

    public void intersection(MathSet... mss) {
        for (MathSet ms : mss) {
            intersection(ms);
        }
    }


}
