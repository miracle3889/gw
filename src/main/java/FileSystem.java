import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FileSystem {
    private FileNode root;
    class FileNode{

        String fileName;
        int dirOrFile;
        List<FileNode> childNodeList;
        String content;
        public FileNode(String fileName,int dirOrFile) {
            this.fileName = fileName;
            this.dirOrFile = dirOrFile;
            if(dirOrFile == 0)
                childNodeList = new ArrayList<>();
        }

        public FileNode getFileNode(String fileName){
            for (FileNode fileNode : childNodeList) {
                if(fileNode.fileName.equals(fileName))
                    return fileNode;
            }
            return null;
        }
    }

    public FileSystem() {
        root = new FileNode("/",0);
    }

    public List<String> ls(String path) {
        String[] fileNames = path.split("/");
        if(fileNames.length!=0&&fileNames[0].length()==0) {
            String[] newFileNames = new String[fileNames.length - 1];
            System.arraycopy(fileNames, 1, newFileNames, 0, fileNames.length - 1);
            fileNames = newFileNames;
        }
        FileNode fileNode = root;
        for (int i = 0; i < fileNames.length; i++)
            fileNode = fileNode.getFileNode(fileNames[i]);
        if(fileNode.dirOrFile==1)
            return Arrays.asList(fileNode.fileName);
        else{
            String[] gs = new String[fileNode.childNodeList.size()];
            for (int i = 0; i < gs.length; i++)
                gs[i] = fileNode.childNodeList.get(i).fileName;
            Arrays.sort(gs);
            return Arrays.asList(gs);
        }
    }

    public void mkdir(String path) {
        String[] fileNames = path.split("/");
        if(fileNames.length!=0&&fileNames[0].length()==0) {
            String[] newFileNames = new String[fileNames.length - 1];
            System.arraycopy(fileNames, 1, newFileNames, 0, fileNames.length - 1);
            fileNames = newFileNames;
        }
        FileNode fileNode = root;
        int i = 0;
        for (; i < fileNames.length; i++) {
            FileNode temp = fileNode.getFileNode(fileNames[i]);
            if(temp == null)
                break;
            else
                fileNode = temp;
        }
        while (i<fileNames.length){
            FileNode newDir = new FileNode(fileNames[i++],0);
            fileNode.childNodeList.add(newDir);
            fileNode = newDir;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] fileNames = filePath.split("/");
        if(fileNames.length!=0&&fileNames[0].length()==0) {
            String[] newFileNames = new String[fileNames.length - 1];
            System.arraycopy(fileNames, 1, newFileNames, 0, fileNames.length - 1);
            fileNames = newFileNames;
        }
        FileNode fileNode = root;
        int i = 0;
        for (; i < fileNames.length; i++) {
            FileNode temp = fileNode.getFileNode(fileNames[i]);
            if(temp == null)
                break;
            else
                fileNode = temp;
        }
        if(i==fileNames.length){
            fileNode.content = fileNode.content+content;
            return;
        }
        while (i<fileNames.length-1){
            FileNode newDir = new FileNode(fileNames[i++],0);
            fileNode.childNodeList.add(newDir);
            fileNode = newDir;
        }
        FileNode newFile = new FileNode(fileNames[i],1);
        fileNode.childNodeList.add(newFile);
        newFile.content = content;
    }

    public String readContentFromFile(String filePath) {
        String[] fileNames = filePath.split("/");
        if(fileNames.length!=0&&fileNames[0].length()==0) {
            String[] newFileNames = new String[fileNames.length - 1];
            System.arraycopy(fileNames, 1, newFileNames, 0, fileNames.length - 1);
            fileNames = newFileNames;
        }
        FileNode fileNode = root;
        int i = 0;
        for (; i < fileNames.length; i++) {
            fileNode = fileNode.getFileNode(fileNames[i]);
        }
        return fileNode.content;
    }

    public static void main(String[] args) {
        FileSystem obj = new FileSystem();
        List<String> param_1 = obj.ls("/");
        System.out.println(param_1);
        obj.mkdir("/a/b/c");
        obj.addContentToFile("/a/b/c/d","hello");
        obj.addContentToFile("/a/b/c/d","hello");
        String param_4 = obj.readContentFromFile("/a/b/c/d");
        System.out.println(param_4);
//        System.out.println(Arrays.toString("/1".split("/")));
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