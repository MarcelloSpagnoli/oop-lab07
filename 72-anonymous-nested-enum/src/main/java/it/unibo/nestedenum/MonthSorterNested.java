package it.unibo.nestedenum;

import static it.unibo.functional.Transformers.transform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31),
        AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int days;

        private Month(final int days) {
            this.days = days;   
        } 

        /**
         * This function, given a string, returns the month that is most likely to be 
         * the month the string refers at
         * @param name of the month, even uncomplete
         * @return Month which name refers at
         */
        public static Month fromString(final String name) {
            /*il prof mette come parametro in toUpperCase Locale.ROOT, serve per garantire
             * una conversione uniforme a prescindere dalla lingua
             * al posto di contains usa startswith
            */
            final String nameUpper = name.toUpperCase(); 
            try{
                return valueOf(nameUpper);
            }catch(IllegalArgumentException e){
                List<Month> okMonths = new LinkedList<>();
                for (Month month : Month.values()) {
                    if (month.toString().contains(nameUpper)) {
                        okMonths.add(month);
                    }
                }
                if (okMonths.size() == 1) {
                    return okMonths.get(0);
                } else if (okMonths.size() == 0){
                    throw new IllegalArgumentException("There is no month which name is " + name);
                } else {
                    throw new IllegalArgumentException("Ambiguity: could be " + okMonths.toString());
                }
            }
        }

        public int getDays() {
            return this.days;
        }

    }

    @Override
    public Comparator<String> sortByDays() {
        return new sortByDays();
    }

    public final static class sortByDays implements Comparator<String> {
        public sortByDays(){}
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).days, Month.fromString(o2).days);
        }
        
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new sortByOrder();
    }

    public final static class sortByOrder implements Comparator<String> {
        public sortByOrder(){}
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).ordinal(), Month.fromString(o2).ordinal());
        }
    }
}
