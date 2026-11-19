package ru.zotov.city;

public class TwoWayCity extends City {

    public TwoWayCity(String name) {
        super(name);
    }

    @Override
    public void addRoad(City destination, int cost) {
        super.addRoad(destination, cost);

        if (!destination.getRoads().containsKey(this) || destination.getRoads().get(this) != cost) {
            destination.addRoad(this, cost);
        }
    }
}
