// https://www.hackerrank.com/challenges/castle-on-the-grid/problem

package queues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class CastleOnTheGrid {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        String[] grid = getGrid(length);
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();
        Grid solution = new Grid(grid, new Position(startX, startY), new Position(endX, endY));
        System.out.println(solution.shortestPath());
    }

    private static class Position {
        private final int x;
        private final int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean notBlocked(String[] grid) {
            return grid[x].charAt(y) != 'X';
        }

        boolean isValidPosition(String[] grid) {
            return x < grid.length
                    && x >= 0
                    && y < grid[0].length()
                    && y >= 0;
        }

        Position goUp() {
            return new Position(x - 1, y);
        }

        Position goRight() {
            return new Position(x, y + 1);
        }

        Position goDown() {
            return new Position(x + 1, y);
        }

        Position goLeft() {
            return new Position(x, y - 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" +
                    "" + x +
                    ", " + y +
                    ')';
        }
    }

    private static class Grid {
        private final String[] grid;
        private final Position source;
        private final Position destination;
        private final LateralMovement up = new UpLateralMovement();
        private final LateralMovement right = new RightLateralMovement();
        private final LateralMovement down = new DownLateralMovement();
        private final LateralMovement left = new LeftLateralMovement();

        Grid(String[] grid, Position source, Position destination) {
            this.grid = grid;
            this.source = source;
            this.destination = destination;
        }

        int shortestPath() {
            Map<Position, Position> prefixPositions = new HashMap<>();
            prefixPositions.put(source, source);
            Queue<Position> queue = new LinkedList<>();
            queue.add(source);

            while (!queue.isEmpty()) {
                Position top = queue.poll();
                if (top.equals(destination)) {
                    break;
                }

                addLateralVerticesToQueue(top, prefixPositions, queue);
            }

            return shortestPath(prefixPositions);
        }

        private abstract class LateralMovement {
            private void addLateralVerticesToQueue(Position position, Map<Position, Position> prefixes, Queue<Position> queue) {
                Position current = position;
                while (current.isValidPosition(grid) && current.notBlocked(grid)) {
                    if (!prefixes.containsKey(current)) {
                        prefixes.put(current, position);
                        queue.add(current);
                    }
                    current = lateralVertice(current);
                }
            }

            abstract Position lateralVertice(Position position);
        }

        private class UpLateralMovement extends LateralMovement {
            @Override
            Position lateralVertice(Position position) {
                return position.goUp();
            }
        }

        private class RightLateralMovement extends LateralMovement {
            @Override
            Position lateralVertice(Position position) {
                return position.goRight();
            }
        }

        private class DownLateralMovement extends LateralMovement {
            @Override
            Position lateralVertice(Position position) {
                return position.goDown();
            }
        }

        private class LeftLateralMovement extends LateralMovement {
            @Override
            Position lateralVertice(Position position) {
                return position.goLeft();
            }
        }

        private void addLateralVerticesToQueue(Position position, Map<Position, Position> prefixes, Queue<Position> queue) {
            up.addLateralVerticesToQueue(position, prefixes, queue);
            right.addLateralVerticesToQueue(position, prefixes, queue);
            down.addLateralVerticesToQueue(position, prefixes, queue);
            left.addLateralVerticesToQueue(position, prefixes, queue);
        }

        private int shortestPath(Map<Position, Position> prefixPositions) {
            return shortestPath(prefixPositions, this.destination);
        }

        private int shortestPath(Map<Position, Position> prefixPositions, Position position) {
            if (position.equals(this.source)) {
                return 0;
            }

            return 1 + shortestPath(prefixPositions, prefixPositions.get(position));
        }
    }

    private static String[] getGrid(int length) {
        String[] array = new String[length];
        for (int index = 0 ; index < length ; index++) {
            array[index] = scanner.next();
        }
        return array;
    }
}
