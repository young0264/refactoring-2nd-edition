package young.refactoring.ch4.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Province {
    //지역 이름
    private String name;

    //생산자
    List<Producer> producerList = new ArrayList<>();

    // 총 생산량
    protected int totalProduction;

    // 수요
    private int demand;

    // 가격
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

    // 생산자 목록 및 갯수 추가
    public void addProducer(Producer producer) {
        this.producerList.add(producer);
        this.totalProduction += 1;
    }

    // 생산 부족을 계산하는 메서드
    public int getShortfall() {
        return this.demand - this.totalProduction;
    }

    // 수익을 얻는 메서드
    public int getProfit(){
        return this.satisfiedDemand()*this.price;
    }

    // (안정적) 수요량 계산
    private int satisfiedDemand() {
        return Math.min(this.demand, this.totalProduction);
    }

    // 수요량 얻기
    public int getDemandCost() {
        //todo: 필드 제거 리팩터링 필요
        int remainingDemand = this.demand;
        int result = 0;

        // js: sort((a,b)=> a.cost-b.cost)
        // cost 순서대로 정렬
        this.producerList.sort(Comparator.comparingInt(p -> p.cost));
        for(Producer producer : this.producerList) {
            // todo: 남은(?) satisfied 남은 수요량 계 메서드 추출
            int contribution = Math.min(remainingDemand, producer.production);
            remainingDemand -= contribution; // todo: 메서드 추출
            result += contribution * producer.cost; // todo: 의미상 메서드 추출
        }
        return result;
    }

}