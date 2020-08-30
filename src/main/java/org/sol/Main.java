package org.sol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static StringBuilder renderFolder(HashMap<Integer, Node>nodeBuild, int rootId, int level, StringBuilder sb, boolean isLast, String prefix)
    {
        indent(sb, level, isLast, prefix).append(nodeBuild.get(rootId).getName()).append("\n");
        String prefixTmp = prefix;
        if(level > 0){
            if(isLast){
                prefixTmp = prefixTmp + "   ";
            }else{
                prefixTmp = prefixTmp + "|  ";
            }
        }

        Node root = nodeBuild.get(rootId);
        List<Integer> childrenId= root.getChildren();
        for (int i = 0; i < childrenId.size(); i++){
            boolean last = ((i + 1) == childrenId.size());
            if (nodeBuild.get(childrenId.get(i)).getChildren().size() > 0){
                renderFolder(nodeBuild, nodeBuild.get(childrenId.get(i)).getId() ,level + 1, sb, last, prefixTmp);
            }else{
                renderFile(nodeBuild, nodeBuild.get(childrenId.get(i)).getId(), level + 1, sb, last, prefixTmp);
            }
        }
        return sb;
    }

    private static StringBuilder renderFile(HashMap<Integer, Node>nodeBuild, int rootId, int level, StringBuilder sb, boolean isLast, String prefix)
    {
        return indent(sb, level, isLast, prefix).append(nodeBuild.get(rootId).getName()).append("\n");
    }

    private static StringBuilder indent(StringBuilder sb, int level, boolean isLast, String prefix)
    {
        sb.append(prefix);
        if (level > 0){
            sb.append(isLast ? "└─" : "|--");
        }
        return sb;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String json = "[" +
                        "{" +
                            "\"id\": 52," +
                            "\"name\": \"BU-FRANCE\"," +
                            "\"parent\": 964," +
                            "\"children\": [58]" +
                        "}," +
                        "{" +
                            "\"id\": 53," +
                            "\"name\": \"BU-GERMANY\"," +
                            "\"parent\": 964," +
                            " \"children\": [61, 62, 63]" +
                        "}," +
                        "{" +
                            "\"id\": 58," +
                            "\"name\": \"BU-QUAL\"," +
                            "\"parent\": 52," +
                            "\"children\": [59, 60]" +
                        "}," +
                        "{" +
                            "\"id\": 59," +
                            "\"name\": \"BU-BUSINESS\"," +
                            "\"parent\": 58," +
                            "\"children\": []" +
                        "}," +
                        "{" +
                            "\"id\": 60," +
                            "\"name\": \"BU-HR\"," +
                            "\"parent\": 58," +
                            "\"children\": []" +
                        "}," +
                        "{" +
                            "\"id\": 61," +
                            "\"name\": \"BU-QUAL1\"," +
                            "\"parent\": 53," +
                            "\"children\": []" +
                        "}," +
                        "{" +
                            "\"id\": 62," +
                            "\"name\": \"BU-BUSINESS1\"," +
                            "\"parent\": 53," +
                            "\"children\": []" +
                        "}," +
                        "{" +
                            "\"id\": 63," +
                            "\"name\": \"BU-HR1\"," +
                            "\"parent\": 53," +
                            "\"children\": []" +
                        "}," +
                        "{" +
                            "\"id\": 964," +
                            "\"name\": \"CUSTOMER GROUP\"," +
                            "\"parent\": null," +
                            "\"children\": [52,53]" +
                        "}" +
                    "]";

        ObjectMapper mapper = new ObjectMapper();
        List<Node> nodes = Arrays.asList(mapper.readValue(json, Node[].class));
        HashMap<Integer, Node> nodeBuild = new HashMap<Integer, Node>();

        int rootId = 0;
        for(int i = 0; i < nodes.size(); i++){
            nodeBuild.put(nodes.get(i).getId(), nodes.get(i));
            if(nodes.get(i).getParent() == 0){
                rootId =  nodes.get(i).getId();
            }
        }
        System.out.println("Root is: "+ nodeBuild.get(rootId).getName());

        int level = 0;
        String prefix = "";
        boolean isLast = false;
        StringBuilder sb = new StringBuilder();

        renderFolder(nodeBuild, rootId, level, sb, isLast, prefix);
        System.out.println(sb);
    }
}
