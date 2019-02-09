package src.sample.callbac.com.fighters;

public interface ElementPower {
    enum Elements {
        FIRE(0b0001), WATER(0b0010), EARTH(0b0100), WIND(0b1000);
        private int value;

        Elements(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    int getElementPower();
}
