package ru.zotov.city;

import java.util.HashMap;
import java.util.Map;



public class City {
    private String name;
    private Map<City, Integer> roads;

    public City(String name) {
        this.name = name;
        this.roads = new HashMap<>();
    }

    public Map<City, Integer> getRoads() {
        return roads;
    }
    public void addRoad(City destination, int cost) {
        this.roads.put(destination, cost);
    }

    public void removeRoad(City destination) {
        this.roads.remove(destination);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        if (!roads.isEmpty()) {
            sb.append(" [");
            boolean first = true;
            for (Map.Entry<City, Integer> entry : roads.entrySet()) {
                if (!first) sb.append(", ");
                sb.append(entry.getKey().getName()).append(":").append(entry.getValue());
                first = false;
            }
            sb.append("]");
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof City)) return false;
        City other = (City) obj;

        if (this.roads.size() != other.roads.size()) {
            return false;
        }
        for (Map.Entry<City, Integer> entry : this.roads.entrySet()) {
            City myDest = entry.getKey();
            Integer myCost = entry.getValue();

            boolean found = false;
            for (Map.Entry<City, Integer> otherEntry : other.roads.entrySet()) {
                City otherDest = otherEntry.getKey();
                Integer otherCost = otherEntry.getValue();

                if (myDest.getName().equals(otherDest.getName()) && myCost.equals(otherCost)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Map.Entry<City, Integer> entry : roads.entrySet()) {
            hash += entry.getKey().getName().hashCode() + entry.getValue().hashCode();
        }
        return hash;
    }
}

