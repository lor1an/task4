package ua.kpi.logic;

public class ChboxIter {

    final String CHECKBOX_NAME = "check";
    final String CHECKBOX_VALUE = "checkbox";
    Integer firstIterator;
    Integer secondIterator;
    Integer thirdIterator;

    public void setFirstIterator(Integer i) {
        this.firstIterator = i;
    }

    public void setSecondIterator(Integer j) {
        this.secondIterator = j;
    }

    public void setThirdIterator(Integer j) {
        this.thirdIterator = j;
    }

    public String getCheckboxName() {
        return CHECKBOX_NAME + firstIterator.toString();
    }

    public String getCheckboxNamee() {
        return CHECKBOX_NAME + thirdIterator.toString();
    }

    public String getCheckboxValue() {
        return CHECKBOX_VALUE + secondIterator.toString();
    }

    public String getSIterator() {
        return "siterator" + secondIterator.toString();
    }
}
