import java.io.File;

public class OrdenaBot {

    public static void main(String[] args) {
        OrdenaBot program = new OrdenaBot();
        program.start();
    }

    public void start() {

        String home = System.getProperty("user.home");
        String path = home + File.separator + "Desktop";

        File desktop = new File(path);
        File images = new File(path + File.separator + "Images");
        File videos = new File(path + File.separator + "Videos");
        File docs = new File(path + File.separator + "Docs");

        images.mkdir();
        videos.mkdir();
        docs.mkdir();

        System.out.println("Moving files...");

        int countVideos = 0;
        int countDocs = 0;
        int countImages = 0;

        if (desktop.isDirectory()) {
            File[] files = desktop.listFiles();

            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    if (isAnImage(file.getName())) {
                        if (move(file, images)) {
                            countImages++;
                        }

                    } else if (isAnDoc(file.getName())) {
                        if (move(file, docs)) {
                            countDocs++;
                        }

                    } else if (isAnVideo(file.getName())) {
                        if (move(file, videos)) {
                            countVideos++;
                        }
                    }
                }
            }
            if(countImages>0){
                System.out.println("Moved " + countImages + " images.");
            }
            if(countVideos>0){
                System.out.println("Moved " + countVideos + " videos.");
            }
            if(countDocs>0){
                System.out.println("Moved " + countDocs + " docs.");
                
            }if((countDocs+countImages+countVideos)>0) {
                System.out.println("Total: " + (countDocs + countImages + countVideos) + " files.");
            }else{
                System.out.println("There are not files to move.");
            }

        }


    }

    /**
     * Method to move files renaming it paths
     * @param source file to move
     * @param target destination path
     * @return new path route
     */
    private boolean move(File source, File target) {
        String abs = target.getAbsolutePath();
        String name = source.getName();
        File c = new File(abs + File.separator + name);
        return source.renameTo(c);
    }

    /**
     * Reader extension method case sensitive
     * @param n String: file name
     * @return boolean: true if its .png .jpg false either
     */
    private boolean isAnImage(String n) {
        int i = n.lastIndexOf(".");
        if (i != -1) {
            String sub = n.substring(i).toLowerCase();
            if (sub.equals(".png")) {
                return true;
            }else if(sub.equals(".jpg")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reader extension method case sensitive
     * @param n String: file name
     * @return boolean: true if its .pdf .txt false either
     */
    private boolean isAnDoc(String n) {
        int i = n.lastIndexOf(".");
        if (i != -1) {
            String sub = n.substring(i).toLowerCase();
            if (sub.equals(".pdf")) {
                return true;
            }else if(sub.equals(".txt")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reader extension method case sensitive
     * @param n String: file name
     * @return boolean: true if its .webm .mov .mp4 .avi false either
     */
    private boolean isAnVideo(String n) {
        int i = n.lastIndexOf(".");
        if (i != -1) {
            String sub = n.substring(i).toLowerCase();
            if (sub.equals(".mov")) {
                return true;
            }else if(sub.equals(".webm")) {
                return true;
            }else if(sub.equals(".mp4")) {
                return true;
            }else if(sub.equals(".avi")) {
                return true;
            }
        }
        return false;
    }
}
