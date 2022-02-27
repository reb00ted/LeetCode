// https://leetcode.com/problems/design-in-memory-file-system/

import java.util.Optional;

class FileSystem {

    // 문제점 - 파일과 디렉터리의 구분이 명확하지 않음
    // -> 파일이 디렉터리처럼 하위 경로를 가질 수도 있고, 디렉터리가 content 를 가질 수 있음
    // -> 다른 사람들 코드를 보니 class Node {}, class File extends Node {...}, class Directory extends Node {...} 와 같이
    // 상속 구조를 이용하면 명확하게 구분하여 구현할 수 있는 듯
    class File {
        String name;
        TreeMap<String, File> children = new TreeMap<>();
        StringBuilder content;

        File() {}

        File(String name) {
            this.name = name;
        }
    }

    File root;

    public FileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        Optional<File> dir = changeDirectory(path);
        if (dir.isEmpty()) return List.of();

        File file = dir.get();
        // 디렉터리가 아닌 파일인 경우, 파일 이름 리턴
        if (file.content != null) return List.of(file.name);
        return file.children.values().stream()
                .map(f -> f.name).collect(Collectors.toList());
    }

    public void mkdir(String path) {
        makeDirectory(path);
    }

    public void addContentToFile(String filePath, String content) {
        File file = makeDirectory(filePath);
        if (file.content == null) {
            file.content = new StringBuilder();
        }
        file.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        Optional<File> file = changeDirectory(filePath);
        if (file.isEmpty()) return "";

        File f = file.get();
        return f.content == null ? f.name : f.content.toString();
    }

    private File makeDirectory(String path) {
        String[] paths = path.split("/");
        File iter = root;

        for (int i = 1; i < paths.length; i++) {
            String next = paths[i];
            if (!iter.children.containsKey(next)) {
                iter.children.put(next, new File(next));
            }
            iter = iter.children.get(next);
        }

        return iter;
    }

    private Optional<File> changeDirectory(String path) {
        String[] paths = path.split("/");
        File iter = root;

        for (int i = 1; i < paths.length; i++) {
            String next = paths[i];
            if (!iter.children.containsKey(next)) {
                return Optional.empty();
            }
            iter = iter.children.get(next);
        }

        return Optional.of(iter);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */