package young.refactoring.ch4.model;

import java.util.ArrayList;
import java.util.List;

public class Province {
    private String name;
    List<Producer> producers = new ArrayList<>();
    private int totalProduction;
    private int demand;
    private int price;
}