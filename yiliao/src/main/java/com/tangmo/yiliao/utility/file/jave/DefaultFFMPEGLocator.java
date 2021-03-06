package com.tangmo.yiliao.utility.file.jave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DefaultFFMPEGLocator extends FFMPEGLocator {
    private static final int myEXEversion = 1;
    private String path;

    public DefaultFFMPEGLocator() {
        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows;
        if (os.indexOf("windows") != -1) {
            isWindows = true;
        } else {
            isWindows = false;
        }

        File temp = new File(System.getProperty("java.io.tmpdir"), "jave-1");
        if (!temp.exists()) {
            temp.mkdirs();
            temp.deleteOnExit();
        }

        String suffix = isWindows ? ".exe" : "";
        File exe = new File(temp, "ffmpeg" + suffix);
        if (!exe.exists()) {
            this.copyFile("ffmpeg" + suffix, exe);
        }

        if (isWindows) {
            File dll = new File(temp, "pthreadGC2.dll");
            if (!dll.exists()) {
                this.copyFile("pthreadGC2.dll", dll);
            }
        }

        if (!isWindows) {
            Runtime runtime = Runtime.getRuntime();

            try {
                runtime.exec(new String[]{"/bin/chmod", "755", exe.getAbsolutePath()});
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        this.path = exe.getAbsolutePath();
    }

    protected String getFFMPEGExecutablePath() {
        return this.path;
    }

    private void copyFile(String path, File dest) throws RuntimeException {
        InputStream input = null;
        FileOutputStream output = null;

        try {
            input = this.getClass().getResourceAsStream(path);
            output = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];

            int l;
            while((l = input.read(buffer)) != -1) {
                output.write(buffer, 0, l);
            }
        } catch (IOException var18) {
            throw new RuntimeException("Cannot write file " + dest.getAbsolutePath());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Throwable var17) {
                    ;
                }
            }

            if (input != null) {
                try {
                    input.close();
                } catch (Throwable var16) {
                    ;
                }
            }

        }

    }
}
