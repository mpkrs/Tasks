package com.company;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize = 0;
    private int maxSize = Integer.MAX_VALUE;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file);// размер файла: content.length
        if (file.getFileName().toString().contains(partOfName) &&
                Files.readAllBytes(file).toString().contains(partOfContent) &&
                content.length > minSize &&
                content.length < maxSize) {
            foundFiles.add(file);
        } else {
                return FileVisitResult.CONTINUE;
        }

//        if ((content.length < maxSize) && maxSize > 0) {
//            return FileVisitResult.CONTINUE;
//        } else if ((content.length > minSize) && minSize > 0) {
//            return FileVisitResult.CONTINUE;
//        } else if (file.getFileName().toString().contains(partOfName) && !partOfName.equals(null)) {
//            return FileVisitResult.CONTINUE;
//        } else if (Files.readAllLines(file).contains(partOfContent) && !partOfContent.equals(null)) {
//            return FileVisitResult.CONTINUE;
//        } else {
//            foundFiles.add(file);
//        }
        return super.visitFile(file, attrs);
    }
}
