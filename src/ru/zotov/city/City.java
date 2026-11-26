package ru.zotov.city;

import java.util.HashMap;
import java.util.Map;

public class City {
    private String name;

    private Map<String, Road> roads;

    private static class Road {
        City destination;
        int cost;

        Road(City destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public City(String name) {
        this.name = name;
        this.roads = new HashMap<>();
    }

    public Map<City, Integer> getRoads() {
        Map<City, Integer> result = new HashMap<>();
        for (Road road : roads.values()) {
            result.put(road.destination, road.cost);
        }
        return result;
    }

    public void addRoad(City destination, int cost) {
        this.roads.put(destination.getName(), new Road(destination, cost));
    }

    public void removeRoad(City destination) {
        this.roads.remove(destination.getName());
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
            for (Road road : roads.values()) {
                if (!first) sb.append(", ");
                sb.append(road.destination.getName()).append(":").append(road.cost);
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
        for (Map.Entry<String, Road> entry : this.roads.entrySet()) {
            String destName = entry.getKey();
            int myCost = entry.getValue().cost;

            Road otherRoad = other.roads.get(destName);
            if (otherRoad == null || otherRoad.cost != myCost) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Map.Entry<String, Road> entry : roads.entrySet()) {
            hash += entry.getKey().hashCode() + Integer.hashCode(entry.getValue().cost);
        }
        return hash;
    }
}