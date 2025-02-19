package young.refactoring.ch4.model;

import java.util.ArrayList;
import java.util.List;

public class Province {
    private String name;
    List<Producer> producerList = new ArrayList<>();
    protected int totalProduction;
    private int demand;
    private int price;

    public Province(String name, List<Producer> producerList, int demand, int price) {
        this.name = name;
        this.producerList = producerList;
        this.demand = demand;
        this.price = price;
    }

    public Province(String name, int totalProduction, int demand, int price) {
        this.name = name;
        this.totalProduction = totalProduction;
        this.demand = demand;
        this.price = price;
    }

    public void addProducer(Producer producer) {
        this.producerList.add(producer);
        this.totalProduction += 1;
    }

}