package core;

import model.File;
import model.SampleFile;
import shared.FileManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class FileExplorer implements FileManager {
    private File root;
    private ArrayDeque<File> buffer;

    public FileExplorer() {
        this.root = new SampleFile(1, "Root");
        this.buffer = new ArrayDeque<>();
    }

    @Override
    public void addInDirectory(int directorNumber, File file) {
        File directory = this.findFile(directorNumber);

        if (directory == null)
            throw new IllegalStateException("Directory not found");

        directory.getChildren().add(file);
    }

    private File findFile(int number) {
        ArrayDeque<File> deque = new ArrayDeque<>();

        deque.offer(this.root);

        while (!deque.isEmpty()){
            File file = deque.poll();

            if (file.getNumber() == number)
                return file;

            file.getChildren().forEach(deque::offer);
        }

        return null;
    }

    private File findParentFile(int number) {

        ArrayDeque<File> deque = new ArrayDeque<>();

        deque.offer(this.root);

        while (!deque.isEmpty()){
            File file = deque.poll();

            for (File child : file.getChildren()){
                if (child.getNumber() == number)
                    return file;
                deque.offer(child);
            }
        }

        return null;
    }
    @Override
    public File getRoot() {
        return this.root;
    }

    @Override
    public File get(int number) {
        File file = findFile(number);

        if (file == null)
            throw new IllegalStateException("File not found");

        return file;
    }

    @Override
    public Boolean deleteFile(File file) {
        if (file.getNumber() == this.root.getNumber()) {
            this.root = null;
            return true;
        }

        File parent = findParentFile(file.getNumber());

        if (parent == null)
            return false;

        parent.getChildren().removeIf(child -> child.getNumber() == file.getNumber());
        return true;
    }

    @Override
    public List<File> getFilesInPath(File path) {
        File file = findFile(path.getNumber());

        if (file == null)
            throw new IllegalStateException("File not found");

        return file.getChildren();
    }

    @Override
    public void move(File file, File destination) {
        if (file.getNumber() == this.root.getNumber())
            throw new IllegalStateException("Root cannot be moved");

        this.deleteFile(file);

        File destinationFile = findFile(destination.getNumber());

        if (destinationFile == null)
            throw new IllegalStateException("File not found");

        destinationFile.getChildren().add(file);
    }

    @Override
    public Boolean contains(File file) {
        return findFile(file.getNumber()) != null;
    }

    @Override
    public List<File> getInDepth() {
        List<File> files = new ArrayList<>();
        getInDepth(this.root, files);
        return files;
    }

    private void getInDepth(File current, List<File> files) {
        if (current == null)
            return;

        files.add(current);
        current.getChildren().forEach(child -> getInDepth(child, files));
    }

    @Override
    public List<File> getInLevel() {

        List<File> files = new ArrayList<>();
        ArrayDeque<File> deque = new ArrayDeque<>();
        deque.offer(this.root);

        while (!deque.isEmpty()) {
            File current = deque.poll();
            files.add(current);
            current.getChildren().forEach(deque::offer);
        }
        return files;
    }

    @Override
    public void cut(int number) {
        File file = findFile(number);

        if (file == null)
            throw new IllegalStateException("File not found");

        this.deleteFile(file);
        this.buffer.push(file);
    }

    @Override
    public void paste(File destination) {
        if (this.buffer.isEmpty())
            return;

        File file = findFile(destination.getNumber());

        if (file == null)
            throw new IllegalStateException("File not found");

        file.getChildren().add(this.buffer.pop());
    }

    @Override
    public Boolean isEmpty() {
        return this.root.getChildren().isEmpty();
    }

    @Override
    public String getAsString() {
        if (this.root == null)
            return "";

        StringBuilder output = new StringBuilder();
        getAsString(this.root, output, "", "");
        return output.toString().trim();
    }

    private void getAsString(File file, StringBuilder output, String prefix, String childrenPrefix) {
        output.append(prefix);
        output.append(file.getNumber());
        output.append(System.lineSeparator());

        int[] index = new int[]{0, file.getChildren().size() - 1};
        file.getChildren().forEach(
                child -> {
                    if (index[0]++ != index[1])
                        getAsString(child, output, childrenPrefix + "├── ", childrenPrefix + "│   ");
                    else
                        getAsString(child, output, childrenPrefix + "└── ", childrenPrefix + "    ");
                });

    }
}
