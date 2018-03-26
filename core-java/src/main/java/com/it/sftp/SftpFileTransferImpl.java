package com.it.sftp;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import org.apache.log4j.Logger;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;

public class SftpFileTransferImpl implements SftpFileTransfer {
	private final Logger logger = Logger.getLogger(SftpFileTransferImpl.class);
   // private Logger logger = LoggerFactory.getLogger(SftpFileTransferImpl.class);
    private SSHUtil ssh;
    private boolean ready = false;
    private int port;
    
    public boolean isReady() {
        return ready;
    }
    
    public SftpFileTransferImpl(int port){
    	this.port = port;
    }

    public void mkdir(String user, String pwd, String hostname, String root, String childFolder) {
        ssh = getConnection(hostname, user, pwd);
        String cmd = "mkdir " + root + "/" + childFolder;
        ssh.execute(cmd);
        closeConnection();
    }

    public void mkdir(String keyFile, String passKey, String user, String hostname, String root, String childFolder)
 {
        ssh = getConnection(keyFile, passKey, user, hostname);
        String cmd = "mkdir  " + root + "/" + childFolder;
        ssh.execute(cmd);
        closeConnection();
    }

    public void rmdir(String user, String pwd, String hostname, String root, String childFolder) {
        ssh = getConnection(hostname, user, pwd);
        String cmd = "rmdir " + root + "/" + childFolder;
        ssh.execute(cmd);
        logger.info("remove directory - directory removed.");
        closeConnection();
    }

    public void rmdir(String keyFile, String passKey, String user, String hostname, String root, String childFolder)
 {
        ssh = getConnection(keyFile, passKey, user, hostname);
        String cmd = "rmdir " + root + "/" + childFolder;
        ssh.execute(cmd);
        logger.info("remove directory - directory removed.");
        closeConnection();
    }

    public void putFile(String user, String pwd, String hostname, String fileName, String targetDir) throws Exception {
        ssh = getConnection(hostname, user, pwd);
        String cmd = "put " + fileName + " " + targetDir;
        try {
            long start = System.currentTimeMillis();
            logger.debug("putFile: " + cmd);
            ssh.execute(cmd);
            logger.info("Upload successful, costs {}s" + (System.currentTimeMillis() - start) / 1000 );
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection();
        }
    }
    
    /**
     * transfer file use key
     */
    public void putFile(String keyFile, String passKey, String user, String hostname, String fileName, String targetDir)
 {
        ssh = getConnection(keyFile, passKey, user, hostname);
        String cmd = "put " + fileName + " " + targetDir;
        logger.debug("putFile: " + cmd);
        ssh.execute(cmd);
        logger.debug("putFile: ready");
        closeConnection();
    }

    public void getFile(String user, String pwd, String hostname, String srcFile, String trgFile) {
        ssh = getConnection(hostname, user, pwd);
        File file = new File(trgFile);
        if (!file.exists()) {
            File f = new File(trgFile);
            f.mkdirs();
        }
        String cmd = "get " + srcFile + " " + trgFile;
        ssh.execute(cmd);
        closeConnection();
    }

    public void getFile(String keyFile, String passKey, String user, String hostname, String srcFile, String trgFile)
 {
        ssh = getConnection(keyFile, passKey, user, hostname);
        File file = new File(trgFile);
        if (!file.exists()) {
            File f = new File(trgFile);
            f.mkdirs();
        }
        String cmd = "get " + srcFile + " " + trgFile;
        ssh.execute(cmd);
        closeConnection();
    }

    public List<LsEntry> listRemote(String user, String pwd, String hostname, String targetDir) {
        ssh = getConnection(hostname, user, pwd);
        List<LsEntry> files = null;
        try {
            files = ssh.ls(targetDir);
        } catch (SftpException e) {
            // throw new MdmException(e);
        }
        closeConnection();
        return files;
    }

    public List<LsEntry> listRemote(String keyFile, String passKey, String user, String hostname, String targetDir)
 {
        ssh = getConnection(keyFile, passKey, user, hostname);
        List<LsEntry> files = null;
        try {
            files = ssh.ls(targetDir);
        } catch (SftpException e) {
            // throw new MdmException(e);
        }
        closeConnection();
        return files;
    }

    public void removeFile(String user, String pwd, String hostname, String trgFile) {
        ssh = getConnection(hostname, user, pwd);
        String cmd = "rm " + trgFile;
        ssh.execute(cmd);
        closeConnection();
    }

    public void removeFile(String keyFile, String passKey, String user, String hostname, String trgFile)
 {
        ssh = getConnection(keyFile, passKey, user, hostname);
        String cmd = "rm " + trgFile;
        ssh.execute(cmd);
        closeConnection();
    }

    private SSHUtil getConnection(String hostname, String user, String passwd) {
        ssh = new SSHUtilImpl(hostname, port, user, passwd, true);
        if (ssh.isConnected()) {
            ready = true;
        }
        return ssh;
    }

    private SSHUtil getConnection(String keyFile, String passKey, String user, String hostname) {
        ssh = new SSHUtilImpl(keyFile, passKey, port, user, hostname);
        if (ssh.isConnected()) {
            ready = true;
        }
        return ssh;
    }

    public void closeConnection() {
        ssh.execute("exit");
    }

    /**
     * put file use username and password
     */
    public void putFile(String user, String keyFile, String passphrase, String hostname, InputStream inputStream, String absolutePath, String fileName)
 {
        //ssh = getConnection(hostname, user, pwd);
        ssh = getConnection(keyFile, passphrase, user, hostname);
        //check file whether in destination server
        List<LsEntry> files = null;
        try {
            files = ssh.ls(absolutePath);
            for(LsEntry file : files){
                if(fileName.equals(file.getFilename())){
                    closeConnection();
                    // throw new MdmException(fileName + " has already been loaded, please use a different filename!");
                }
            }
            ssh.put(inputStream, absolutePath + fileName, null, ChannelSftp.OVERWRITE);
            String cmd = "chmod 666 "+absolutePath + fileName;
            ssh.execute(cmd);
            closeConnection();
            inputStream.close();
        } catch (Exception e) {
            // throw new MdmException(e);
        }
    }

 
    public void rename(String user, String pwd, String hostname, String oldpath, String newpath) {
        ssh = getConnection(hostname, user, pwd);
        logger.debug("rename begin ");
        ssh.rename(oldpath, newpath);
        logger.debug("rename end");
        closeConnection();
    }
    
    //get and set method
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
