
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class File {
    String name;
    int size;
    List<File> children;
    File parent;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public File(String name) {
        this.name = name;
        this.size = 0;
        this.children = new ArrayList<>();
        this.parent = null;
    }
}

public class Main {

    static Map<File, Integer> SIZES = new HashMap<>();

    public static void main(String[] args) throws Exception {
        File FS = new File("/");
        File pwd = FS;
        BufferedReader reader = new BufferedReader(new FileReader("day7.in"));
        String line = reader.readLine();
        List<String> lines = new ArrayList<>();
        while (line != null) {
            line = line.strip();
            lines.add(line);
            line = reader.readLine();

        }
        reader.close();
        lines = lines.stream()
                .filter(l -> !l.isEmpty())
                .collect(Collectors.toList());

        for (String l : lines) {
            if (l.startsWith("$")) {
                String[] parts = l.split(" ");
                String cmd = parts[1];
                if (cmd.equals("cd")) {
                    String dir = parts[2];
                    if (dir.equals("..")) {
                        pwd = pwd.parent;
                    } else {
                        File file = new File(dir);
                        file.parent = pwd;
                        pwd.children.add(file);
                        pwd = file;
                    }
                }
            } else {
                String[] split = l.split(" ");

                if (!split[0].equals("dir")) {
                    File file = new File(split[1], Integer.parseInt(split[0]));
                    pwd.children.add(file);
                }
            }
        }

        long USED = dir_size(FS);
        int sum = SIZES.values()
                .stream()
                .filter(s -> s < 100000)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Part 1: " + sum);

        long AVAILABLE = 70000000;
        long NEED = 30000000;
        long UNUSED = AVAILABLE - USED;

        List<Integer> values = SIZES.values()
                .stream()
                .sorted()
                .collect(Collectors.toList());

        for (int size : values) {
            if (UNUSED + size >= NEED) {
                System.out.println("Part 2: " + size);
                break;
            }
        }
    }

    public static int dir_size(File file) {
        if (file.children.isEmpty()) {
            return file.size;
        }

        int total_size = 0;
        for (File child : file.children) {
            total_size += dir_size(child);
        }
        SIZES.put(file, total_size);
        return total_size;
    }

}
