import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public final class Main {

    private static List<String> lines;

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

        File file = new File("input.txt");
        lines = Files.readAllLines(file.toPath());

        int part1Solution = part1();
        int part2Solution = part2();

        long timeTaken = System.nanoTime() - startTime;

        System.out.printf("Time: %sms\n", timeTaken / 1_000_000);
        System.out.printf("Visible Trees: %s\n", part1Solution);
        System.out.printf("Highest Scenic Score: %s\n", part2Solution);
    }

    private static final int part2() {
        int size = lines.size();

        int highestScenicScore = -1;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int treeHeight = getTreeHeightAtXY(x, y);

                int rightScore = 0;
                for (int right = x + 1; right < size; right++) {
                    int height = getTreeHeightAtXY(right, y);
                    if (height >= treeHeight) {
                        rightScore++;
                        break;
                    }
                    rightScore++;
                }

                int leftScore = 0;
                for (int left = x - 1; left > -1; left--) {
                    int height = getTreeHeightAtXY(left, y);
                    if (height >= treeHeight) {
                        leftScore++;
                        break;
                    }
                    leftScore++;
                }

                int upScore = 0;
                for (int up = y - 1; up > -1; up--) {
                    int height = getTreeHeightAtXY(x, up);
                    if (height >= treeHeight) {
                        upScore++;
                        break;
                    }
                    upScore++;
                }

                int downScore = 0;
                for (int down = y + 1; down < size; down++) {
                    int height = getTreeHeightAtXY(x, down);
                    if (height >= treeHeight) {
                        downScore++;
                        break;
                    }
                    downScore++;
                }

                int scenicScore = rightScore * leftScore * upScore * downScore;
                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }
            }
        }

        return highestScenicScore;
    }

    private static final int part1() {
        int size = lines.size();

        int notVisibleCount = 0;

        HashMap<Integer, HashMap<Integer, Integer>> notVisible = new HashMap<>();

        int tallest;
        for (int y = 0; y < size; y++) {
            tallest = -1;
            notVisible.putIfAbsent(y, new HashMap<>());

            for (int x = 0; x < size; x++) {
                int treeHeight = getTreeHeightAtXY(x, y);

                if (treeHeight <= tallest) {
                    notVisibleCount++;
                    HashMap<Integer, Integer> xHashMap = notVisible.get(y);
                    xHashMap.put(x, x);
                    notVisible.put(y, xHashMap);
                    continue;
                }

                tallest = treeHeight;
            }
        }

        for (int y = 0; y < size; y++) {
            tallest = -1;

            for (int x = size - 1; x > -1; x--) {
                int treeHeight = getTreeHeightAtXY(x, y);

                if (treeHeight <= tallest) {
                    continue;
                }

                if (notVisible.get(y).get(x) != null) {
                    notVisible.get(y).remove(x);
                    notVisibleCount--;
                }

                tallest = treeHeight;
            }
        }

        for (int x = 0; x < size; x++) {
            tallest = -1;
            for (int y = 0; y < size; y++) {
                String c = lines.get(y).substring(x, x + 1);
                int treeHeight = Integer.parseInt(c);

                if (treeHeight <= tallest) {
                    continue;
                }

                if (notVisible.get(y).get(x) != null) {
                    notVisible.get(y).remove(x);
                    notVisibleCount--;
                }

                tallest = treeHeight;
            }
        }

        for (int x = 0; x < size; x++) {
            tallest = -1;
            for (int y = size - 1; y > -1; y--) {
                int treeHeight = getTreeHeightAtXY(x, y);

                if (treeHeight <= tallest) {
                    continue;
                }

                if (notVisible.get(y).get(x) != null) {
                    notVisible.get(y).remove(x);
                    notVisibleCount--;
                }

                tallest = treeHeight;
            }
        }

        int visible = size * size - notVisibleCount;

        return visible;
    }

    private static final int getTreeHeightAtXY(int x, int y) {
        return Integer.parseInt(lines.get(y).substring(x, x + 1));
    }
}