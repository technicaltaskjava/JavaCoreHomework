package filemanager.filesystemviewer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Alexey Ushakov
 */
class ViewerVisitor implements FileVisitor<Path> {

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes)
            throws IOException {

        if (path.toFile().isHidden()) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        if (basicFileAttributes.isDirectory()) {
            PathTree.getInstance().addPathToTree(path);
            return FileVisitResult.CONTINUE;
        }

        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes)
            throws IOException {

        if (path.toFile().isHidden()) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        if (basicFileAttributes.isRegularFile()) {
            PathTree.getInstance().addPathToTree(path);
            return FileVisitResult.CONTINUE;
        }

        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException e)
            throws IOException {

        if (e != null) {
            return FileVisitResult.SKIP_SIBLINGS;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException e)
            throws IOException {

        return FileVisitResult.CONTINUE;
    }
}
