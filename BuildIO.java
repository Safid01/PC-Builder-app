import java.io.*;
import java.util.ArrayList;

public class BuildIO {

    public static void saveBuild(String build) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("SavedBuilds.txt", true))) {
            bw.write(build);
            bw.newLine();
            bw.write("-----");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> loadBuilds() {
        ArrayList<String> builds = new ArrayList<>();
        File file = new File("SavedBuilds.txt");
        if (!file.exists()) {
            return builds;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder build = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("-----")) {
                    builds.add(build.toString());
                    build = new StringBuilder();
                } else {
                    build.append(line).append("\n");
                }
            }
            if (build.length() > 0) {
                builds.add(build.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builds;
    }

    public static void deleteBuild(String buildToDelete) {
        ArrayList<String> builds = loadBuilds();
        builds.remove(buildToDelete);
        try (PrintWriter writer = new PrintWriter("SavedBuilds.txt")) {
            for (String build : builds) {
                writer.print(build);
                writer.println("-----");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}