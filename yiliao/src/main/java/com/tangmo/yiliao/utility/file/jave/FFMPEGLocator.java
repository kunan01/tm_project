package com.tangmo.yiliao.utility.file.jave;

public abstract class FFMPEGLocator {
    public FFMPEGLocator() {
    }

    protected abstract String getFFMPEGExecutablePath();

    FFMPEGExecutor createExecutor() {
        return new FFMPEGExecutor(this.getFFMPEGExecutablePath());
    }
}
