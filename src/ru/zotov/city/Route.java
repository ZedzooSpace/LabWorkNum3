package ru.zotov.city;

import java.util.*;

public class Route {
    private City start;
    private City end;

    public Route(City start, City end) {
        setStart(start);
        setEnd(end);
    }

    public void setStart(City start) {
        if (start == null) throw new IllegalArgumentException("Точка начала не может быть null");
        this.start = start;
    }

    public void setEnd(City end) {
        if (end == null) throw new IllegalArgumentException("Точка конца не может быть null");
        this.end = end;
    }

    public City[] getRouteArray() {
        if (start.equals(end)) {
            return new City[]{start};
        }

        Queue<City> queue = new LinkedList<>();
        Map<City, City> cameFrom = new HashMap<>();
        Set<City> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;

        while (!queue.isEmpty()) {
            City current = queue.poll();

            if (current.equals(end)) {
                found = true;
                break;
            }

            for (City neighbor : current.getRoads().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!found) {
            return new City[0];
        }

        List<City> path = new ArrayList<>();
        City current = end;
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);

        return path.toArray(new City[0]);
    }

    @Override
    public String toString() {
        City[] routeArray = getRouteArray();
        if (routeArray.length == 0) {
            return "Путь между " + start.getName() + " и " + end.getName() + " не найден.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < routeArray.length; i++) {
            sb.append(routeArray[i].getName());
            if (i < routeArray.length - 1) {
                sb.append(" ➔ ");
            }
        }
        return sb.toString();
    }
}
