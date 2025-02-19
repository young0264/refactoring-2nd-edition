package young.refactoring.ch4.model;

public class Producer {
    protected String name;
    protected int cost;
    protected int production;
    protected Province province;

    public Producer(String name, int cost, int production) {
        this.name = name;
        this.cost = cost;
        this.production = production;
    }

    public Producer(String name, int cost, int production, Province province) {
        this.name = name;
        this.cost = cost;
        this.production = production;
        this.province = province;
    }

    public void setProduction(int production) {
        this.province.totalProduction += production - this.production;
        this.production = production;
    }

}
