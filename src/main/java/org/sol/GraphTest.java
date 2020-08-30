package org.sol;

import java.io.File;

public class GraphTest {


    public static void main(String[] args) throws Exception
    {
        File folder = new File((args.length >= 1 && args[0].length() > 0) ? args[0]: ".");
        if (!folder.isDirectory())
        {
            throw new IllegalArgumentException("Invalid directory: " + folder.getName());
        }
        int level = 0;
        String prefix = "";
        System.out.println(renderFolder(folder, level, new StringBuilder(), false, prefix));
    }

    private static StringBuilder renderFolder(File folder, int level, StringBuilder sb, boolean isLast, String prefix)
    {
        indent(sb, level, isLast, prefix).append(folder.getName()).append("\n");
        File[] objects = folder.listFiles();
        String prefixTmp = prefix;
        if(level > 0){
            if(isLast){
                prefixTmp = prefixTmp + "   ";
            }else{
                prefixTmp = prefixTmp + "|  ";
            }
        }

        for (int i = 0; i < objects.length; i++){
            boolean last = ((i + 1) == objects.length);
            if (objects[i].isDirectory()){
                renderFolder(objects[i], level + 1, sb, last, prefixTmp);
            }else{
                renderFile(objects[i], level + 1, sb, last, prefixTmp);
            }
        }
        return sb;
    }

    private static StringBuilder renderFile(File file, int level, StringBuilder sb, boolean isLast, String prefix)
    {
        return indent(sb, level, isLast, prefix).append(file.getName()).append("\n");
    }

    private static StringBuilder indent(StringBuilder sb, int level, boolean isLast, String prefix)
    {
        sb.append(prefix);
        if (level > 0){
            sb.append(isLast ? "|__" : "|--");
        }
        return sb;
    }
}
