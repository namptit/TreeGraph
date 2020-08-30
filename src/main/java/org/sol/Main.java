package org.sol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static StringBuilder renderRootNode(HashMap<Integer, Node>nodeBuild, int rootId, int level, StringBuilder sb, boolean isLast, String prefix)
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
                renderRootNode(nodeBuild, nodeBuild.get(childrenId.get(i)).getId() ,level + 1, sb, last, prefixTmp);
            }else{
                renderChildNode(nodeBuild, nodeBuild.get(childrenId.get(i)).getId(), level + 1, sb, last, prefixTmp);
            }
        }
        return sb;
    }

    private static StringBuilder renderChildNode(HashMap<Integer, Node>nodeBuild, int rootId, int level, StringBuilder sb, boolean isLast, String prefix)
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

    public static void main(String[] args) throws IOException {

        String json = FileUtils.readFileToString(new File("src/main/resources/tree.json"), StandardCharsets.UTF_8);
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

        renderRootNode(nodeBuild, rootId, level, sb, isLast, prefix);
        System.out.println(sb);
    }
}
