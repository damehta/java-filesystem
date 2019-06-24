package com.dam.fs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystem {
    FileNode root;
    public FileSystem(){
        root = new FileNode("",true);
    }

    /**
     *
     * @param path  Full qualifying (absolute) path
     * @return
     */
    public boolean mkdir(String path){
        if(path == null || path.isEmpty() || path.equals("/") || !path.startsWith("/")) {
            return false;
        }
        String[] tokens = path.split("/");
        FileNode curr = root;
        boolean isCreated = false;
        for(int i=1; i<tokens.length; i++){
            if(!curr.children.containsKey(tokens[i])){
                curr.children.put(tokens[i], new FileNode(tokens[i], true));
                isCreated = true;
            }
            curr = curr.children.get(tokens[i]);
        }
        return isCreated;
    }

    /**
     *
     * @param path  Full qualifying (absolute) path
     * @return
     */
    public List<String> ls(String path){
        List<String> list = new ArrayList<>();
        FileNode curr = goToCurrDir(path);
        list.addAll(curr.children.keySet());
        Collections.sort(list);
        return list;
    }

    /**
     *
     * @param path  Full qualifying (absolute) path
     * @return      current directory
     */
    private FileNode goToCurrDir(String path) {
        if(path == null || path.isEmpty() || path.equals("/") || !path.startsWith("/")) {
            throw new IllegalArgumentException("Invalid path. Please provide valid absolute path");
        }
        FileNode curr = root;
        String[] tokens = path.split("/");
        for(int i=1; i<tokens.length; i++){
            if(!curr.children.containsKey(tokens[i])){
                throw new IllegalArgumentException("Invalid path. Please provide valid absolute path");
            }
            curr = curr.children.get(tokens[i]);
        }
        return curr;
    }

    /**
     *
     * @param path      Full qualifying (absolute) path
     * @param fileName  existing file name
     * @param content   file content
     */
    public void appendToFile(String path, String fileName, String content){
        if(path == null || path.isEmpty() || path.equals("/") || !path.startsWith("/")) {
            throw new IllegalArgumentException("Invalid path. Please provide valid absolute path");
        }
        FileNode curr = goToCurrDir(path).children.get(fileName);
        if(curr==null){
                throw new IllegalArgumentException("File not found");
        }
        curr.content.append(content);
    }

    /**
     *
     * @param path      Full qualifying (absolute) path
     * @param fileName  new file name
     * @param content   file content
     */
    public void createFile(String path, String fileName, String content){
        FileNode curr = goToCurrDir(path);
        if(curr.children.containsKey(fileName)){
            appendToFile(path, fileName, content);
        }else{
            curr.children.put(fileName, new FileNode(fileName, false));
            appendToFile(path, fileName, content);
        }
    }

    /**
     *
     * @param path      Full qualifying (absolute) path
     * @param fileName  existing file name
     * @return          File content
     */
    public String catFile(String path, String fileName) {
        FileNode curr = goToCurrDir(path);
        return curr.children.get(fileName).content.toString();
    }
}
