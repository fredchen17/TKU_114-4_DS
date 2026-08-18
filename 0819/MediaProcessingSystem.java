public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] mediaFiles = {
            new ImageFile("photo.jpg", 3500, "1920x1080"),
            new AudioFile("song.mp3", 8200, 210),
            new VideoFile("movie.mp4", 154000, 7200, "1080p")
        };

        System.out.println("=== 媒體檔案處理系統測試 ===");
        for (MediaFile file : mediaFiles) {
            System.out.println("----------------------------------------");
            file.displayInfo();

            if (file instanceof Playable playable) {
                playable.play();
            }

            if (file instanceof Compressible compressible) {
                compressible.compress();
            }
        }
    }
}

abstract class MediaFile {
    private String fileName;
    private int fileSizeKb;

    public MediaFile(String fileName, int fileSizeKb) {
        this.fileName = fileName;
        this.fileSizeKb = fileSizeKb < 0 ? 0 : fileSizeKb;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFileSizeKb() {
        return fileSizeKb;
    }

    public void setFileSizeKb(int fileSizeKb) {
        this.fileSizeKb = fileSizeKb < 0 ? 0 : fileSizeKb;
    }

    public abstract void displayInfo();
}

interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

class ImageFile extends MediaFile implements Compressible {
    private String resolution;

    public ImageFile(String fileName, int fileSizeKb, String resolution) {
        super(fileName, fileSizeKb);
        this.resolution = resolution;
    }

    @Override
    public void displayInfo() {
        System.out.println("[圖片檔案] 檔名: " + getFileName() + " | 大小: " + getFileSizeKb() + " KB | 解析度: " + resolution);
    }

    @Override
    public void compress() {
        int originalSize = getFileSizeKb();
        int newSize = (int) (originalSize * 0.7);
        setFileSizeKb(newSize);
        System.out.println("  └─ [壓縮圖片] 影像優化壓縮完成： " + originalSize + " KB -> " + getFileSizeKb() + " KB");
    }
}

class AudioFile extends MediaFile implements Playable, Compressible {
    private int durationSeconds;

    public AudioFile(String fileName, int fileSizeKb, int durationSeconds) {
        super(fileName, fileSizeKb);
        this.durationSeconds = durationSeconds < 0 ? 0 : durationSeconds;
    }

    @Override
    public void displayInfo() {
        System.out.println("[音訊檔案] 檔名: " + getFileName() + " | 大小: " + getFileSizeKb() + " KB | 時長: " + durationSeconds + " 秒");
    }

    @Override
    public void play() {
        System.out.println("  └─ [播放音訊] 正在解碼播放音樂音訊...");
    }

    @Override
    public void compress() {
        int originalSize = getFileSizeKb();
        int newSize = (int) (originalSize * 0.5);
        setFileSizeKb(newSize);
        System.out.println("  └─ [壓縮音訊] 音訊轉碼壓縮完成： " + originalSize + " KB -> " + getFileSizeKb() + " KB");
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {
    private int durationSeconds;
    private String resolution;

    public VideoFile(String fileName, int fileSizeKb, int durationSeconds, String resolution) {
        super(fileName, fileSizeKb);
        this.durationSeconds = durationSeconds < 0 ? 0 : durationSeconds;
        this.resolution = resolution;
    }

    @Override
    public void displayInfo() {
        System.out.println("[影片檔案] 檔名: " + getFileName() + " | 大小: " + getFileSizeKb() + " KB | 時長: " + durationSeconds + " 秒 | 解析度: " + resolution);
    }

    @Override
    public void play() {
        System.out.println("  └─ [播放影片] 正在進行視訊串流播放與渲染...");
    }

    @Override
    public void compress() {
        int originalSize = getFileSizeKb();
        int newSize = (int) (originalSize * 0.4);
        setFileSizeKb(newSize);
        System.out.println("  └─ [壓縮影片] H.264 視訊壓碼完成： " + originalSize + " KB -> " + getFileSizeKb() + " KB");
    }
}