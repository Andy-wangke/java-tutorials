package com.it.sftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public interface SSHUtil {

    void execute(String cmd);

    List<LsEntry> ls(String path) throws SftpException;

    boolean isConnected();

    void put(InputStream src, String dst, SftpProgressMonitor monitor, int mode);
    
    void rename(String oldpath, String newpath);

    String executeWithReturn(String command) throws JSchException, IOException;
}
