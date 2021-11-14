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
        for (int i = 0; i < size(); i++) {
            if (!ms.contains(get(i))) {
                remove(i);
                i--;
            }
        }
    }

    public void intersection(MathSet... mss) {
        for (MathSet ms : mss) {
            intersection(ms);
        }
    }

    private int getIndex(Number number) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(number)) {
                return i;
            }
        }
        throw new RuntimeException("Can't find number = " + number);
    }

    private void swap(Number[] src, int firstIndex, int secondIndex) {
        Number tmp = src[firstIndex];
        src[firstIndex] = src[secondIndex];
        src[secondIndex] = tmp;
    }

    private void sort(boolean isAsc, int firstIndex, int lastIndex) {
        Number[] numbers = toArray();
        for (int i = lastIndex; i >= firstIndex + 1; i--) {
            for (int j = firstIndex; j < i; j++) {
                if (isAsc) {
                    if (numbers[j].doubleValue() > numbers[j + 1].doubleValue()) {
                        swap(numbers, j, j + 1);
                    }
                } else {
                    if (numbers[j].doubleValue() < numbers[j + 1].doubleValue()) {
                        swap(numbers, j, j + 1);
                    }
                }
            }
        }
        clear();
        add(numbers);
    }

    public void sortDesc() {
        sort(false, 0, size() - 1);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        checkIndexes(firstIndex, lastIndex);
        sort(false, firstIndex, lastIndex);
    }

    public void sortDesc(Number number) {
        int firstIndex = getIndex(number);
        sort(false, firstIndex, size() - 1);
    }

    public void sortAsc() {
        sort(true, 0, size() - 1);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        checkIndexes(firstIndex, lastIndex);
        sort(true, firstIndex, lastIndex);
    }

    public void sortAsc(Number number) {
        int firstIndex = getIndex(number);
        sort(true, firstIndex, size() - 1);
    }

    public Number getMax() {
        MathSet other = new MathSet(this);
        other.sortAsc();
        return other.get(other.size() - 1);
    }

    public Number getMin() {
        MathSet other = new MathSet(this);
        other.sortAsc();
        return other.get(0);
    }

    public Number getAverage() {
        double sum = 0;
        for (Number number : this) {
            sum += number.doubleValue();
        }
        return sum / size();
    }

    public Number getMedian() {
        MathSet other = new MathSet(this);
        other.sortAsc();
        int medianIndex = size() / 2;
        Number medianNumber;
        if (medianIndex % 2 == 1) {
            medianNumber = (other.get(medianIndex).doubleValue() + other.get(medianIndex - 1).doubleValue()) / 2;
        } else {
            medianNumber = other.get(medianIndex);
        }
        return medianNumber;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        checkIndexes(firstIndex, lastIndex);
        Number[] src = toArray();
        int newSize = lastIndex - firstIndex + 1;
        Number[] dest = new Number[newSize];
        System.arraycopy(src, firstIndex, dest, 0, newSize);
        return dest;
    }

    public Number[] toArray() {
        Object[] objects = super.toArray();
        Number[] numbers = new Number[objects.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (Number) objects[i];
        }
        return numbers;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        checkIndexes(firstIndex, lastIndex);
        MathSet toReturn = new MathSet(toArray(firstIndex, lastIndex));
        for (int i = firstIndex; i <= lastIndex; i++) {
            remove(i);
        }
        return toReturn;
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            remove(number);
        }
    }
}
