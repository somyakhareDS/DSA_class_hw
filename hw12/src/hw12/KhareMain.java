package hw12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Program for generating kanji component dependency order via topological sort.
 * 
 * @author Somya Khare, Acuna
 * @version 10/01/21
 */
public class KhareMain {
    
    /**
     * Entry point for testing.
     * @param args the command line arguments
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    private static BetterDiGraph directedGraph;

    public static void main(String[] args) {


        directedGraph = new BetterDiGraph();
        try {
        	BufferedReader indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File
                    ("data-kanji.txt")), "UTF-8"));
            HashMap<Integer, Integer> dataKanji = new HashMap<>();
            String firstCharacter;
            while ((firstCharacter = indexReader.readLine()) != null) {
                if (!firstCharacter.contains("#")) {
                    String[] split = firstCharacter.split("\t");

                    int node = Integer.parseInt(split[0]);
                    int edgeTo = Character.codePointAt(split[1], 0);
                    dataKanji.put(node, edgeTo);
                    directedGraph.addVertex(node);

                }
            }

            indexReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File("data-components.txt")), StandardCharsets.UTF_8));

            while ((firstCharacter = indexReader.readLine()) != null) {
                if (!firstCharacter.contains("#")) {
                    String[] split = firstCharacter.split("\t");

                    int node = Integer.parseInt(split[0]);
                    int edgeTo = Integer.parseInt(split[1]);

                    directedGraph.addEdge(node, edgeTo);

                }
            }

            indexReader.close();
            for (Integer i : directedGraph.vertices()) {
                System.out.print(String.valueOf(Character.toChars(dataKanji.get(i))));
            }
            System.out.println("");
            TopologicalSort topSort = new IntuitiveTopological(directedGraph);
            for(Integer i : topSort.order()){
                System.out.print(String.valueOf(Character.toChars(dataKanji.get(i))));
            }




        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }
}