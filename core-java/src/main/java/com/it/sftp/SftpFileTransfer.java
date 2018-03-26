package com.it.sftp;

import java.io.InputStream;
import java.util.List;
import com.jcraft.jsch.ChannelSftp.LsEntry;

public interface SftpFileTransfer {

    void putFile(String user, String pwd, String hostname, String fileName, String targetDir) throws Exception;
    
    void putFile(String keyFile, String passKey, String user, String hostname, String fileName, String targetDir) throws Exception;

    void getFile(String user, String pwd, String hostname, String srcFile, String trgFile) throws Exception;
    
    void getFile(String keyFile, String passKey, String user, String hostname, String srcFile, String trgFile) throws Exception;

    void removeFile(String user, String pwd, String hostname, String trgFile) throws Exception;
    
    void removeFile(String keyFile, String passKey, String user, String hostname, String trgFile) throws Exception;

    void mkdir(String user, String pwd, String hostname, String root, String childFolder) throws Exception;
    
    void mkdir(String keyFile, String passKey, String user, String hostname, String root, String childFolder) throws Exception;

    void closeConnection() throws Exception;

    boolean isReady();

    void rmdir(String user, String pwd, String hostname, String root, String childFolder) throws Exception;
    
    void rmdir(String keyFile, String passKey, String user, String hostname, String root, String childFolder) throws Exception;

    List<LsEntry> listRemote(String user, String pwd, String hostname, String targetDir) throws Exception;
    
    List<LsEntry> listRemote(String keyFile, String passKey, String user, String hostname, String targetDir) throws Exception;

    void putFile(String user, String keyFile, String passphrase, String hostname, InputStream inputStream, String absolutePath, String fileName)
        throws Exception;

    void rename(String user, String pwd, String hostname, java.lang.String oldpath, java.lang.String newpath)
 throws Exception;
}
