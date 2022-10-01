package old.safenar.util;

import java.io.File;
import java.util.Objects;

public class Directory extends File implements Container<File> {

    public Directory(File file) {
        super(file.getPath());
    }

    public Directory(String pathname) {
        super(pathname);
    }

    public Directory(String parent, String child) {
        super(parent, child);
    }

    public Directory(File parent, String child) {
        super(parent, child);
    }

    public boolean add(File file) {
        if (file.isDirectory()) {
            return false;
        }
        return file.renameTo(new File(this, file.getName()));
    }

    public boolean remove(File file) {
        return file.delete();
    }

    public void create() {
        if (!exists()) {
            mkdirs();
        }
    }

    public boolean delete() {
        if (exists()) {
            return deleteRecursively();
        }
        return !exists();
    }

    private boolean deleteRecursively() {
        if (isDirectory()) {
            for (File file : Objects.requireNonNull(listFiles())) {
                return remove(file);
            }
        }
        return delete();
    }

    boolean isEmpty() {
        return listFiles() == null || listFiles().length == 0;
    }

    @Override
    public boolean contains(File file) {
        return file.getParentFile().equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(getPath(), directory.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath());
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public String toString() {
        return getPath();
    }
}
