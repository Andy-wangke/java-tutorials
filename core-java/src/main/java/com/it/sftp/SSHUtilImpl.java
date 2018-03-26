package com.it.sftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.SftpStatVFS;

public class SSHUtilImpl implements SSHUtil {

    //private Logger logger = LoggerFactory.getLogger(SSHUtilImpl.class);
    private final Logger logger = Logger.getLogger(SSHUtilImpl.class);
    private static final String ENCODING_FORMAT = "UTF-8";
    private static String password;

    private JSch jsch = new JSch();
    private Session session = null;
    private ChannelSftp c = null;
    boolean isConnected = false;


    public SSHUtilImpl(String hostname, int port, String username, String password, boolean sftpOpenFlg) {
        SSHUtilImpl.password = password;
        logger.info("before jsch.getSession... user: " + username + "|host: " + hostname + "|port: " + port);
        isConnected = false;
        try {
            session = jsch.getSession(username, hostname, port);
            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(72000000);//20 hours
            session.connect(72000000);

            // open the channel for the session
            if (sftpOpenFlg) {
                Channel channel = session.openChannel("sftp");
                channel.connect();
                c = (ChannelSftp) channel;
                c.setFilenameEncoding("UTF-8");
            }
            isConnected = true;
        } catch (JSchException e) {
            logger.info("SSHUtilImpl - JSchException: " + e.getMessage());
            // throw new MDCPCoreException(e);
        } catch (SftpException e) {
            logger.info(e.getMessage());
        }
        logger.info("after jsch.getSession...");
    }


    public static void sshSftp(String ip, int port, String user, String psw) {
        // System.out.println("开始用户名密码方式登陆");
        Session session = null;

        JSch jsch = new JSch();

        try {
            if (port <= 0) {
                // connect server, user default port
                session = jsch.getSession(user, ip);
            } else {
                // 采用指定的端口连接服务器
                session = jsch.getSession(user, ip, port);
            }

            // 如果服务器连接不上，则抛出异常
            if (session == null) {
                // throw new MdmException("session is null");
            }

            // 设置登陆主机的密码
            session.setPassword(psw);// 设置密码
            // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(72000000);
            session.connect(72000000);
        } catch (Exception e) {
            // throw new MdmException(e);
        }

        // System.out.println("sftp成功");
    }

    /**
     * get connection use key
     * 
     * @param keyFile
     * @param passKey
     * @param port
     * @param user
     * @param hostname
     */
    public SSHUtilImpl(String keyFile, String passKey, int port, String user, String hostname) {
        logger.info("before jsch.getSession... user: " + user + "|host: " + hostname + "|port: " + port + "|keyFile: " + keyFile);

        isConnected = false;
        try {
            if ((null != passKey) && (passKey.trim().length() > 0)) {
                jsch.addIdentity(keyFile, passKey);
            } else {
                jsch.addIdentity(keyFile);
            }
            // jsch.addIdentity(keyFile, "export");
            // jsch.setKnownHosts(null);
            // session.setPassword();
            session = jsch.getSession(user, hostname, port);
            // session.setConfig("PreferredAuthentications",
            // "publickey,keyboard-interactive,password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("userauth.gssapi-with-mic", "no");
            session.setTimeout(72000000);
            session.connect(600000);

            // open the channel for the session
            Channel channel = session.openChannel("sftp");
            channel.connect();
            c = (ChannelSftp) channel;
            isConnected = true;
        } catch (JSchException e) {
            logger.info("SSHUtilImpl - JSchException: " + e.getMessage());
            // throw new MDCPCoreException(e);
        }
        logger.info("after jsch.getSession...");
    }

    public boolean isConnected() {
        return isConnected;
    }

    public List<LsEntry> ls(String path) throws SftpException {
        List<LsEntry> entries = new ArrayList<LsEntry>();
        try {
            @SuppressWarnings("rawtypes")
            List vv = c.ls(path);
            System.out.println("vv.size() = " + vv.size());

            for (Object entry : vv) {
                System.out.println(entry.toString());
                if (entry instanceof LsEntry) {
                    LsEntry e = (LsEntry) entry;
                    String name = e.getFilename();
                    if (name.length() > 0 && ".".equals(name.substring(0, 1))) {
                        continue;
                    }
                    entries.add((LsEntry) entry);
                }
            }
        } catch (SftpException e) {
            logger.info("SSHUtilImpl - JSchException: " + e.getMessage());
            throw e;
        }
        return entries;
    }


    public void execute(String command) {
        try {
            logger.info("sftp>{}" + command);
            Vector<String> cmd = new Vector<String>();
            int level = 0;

            cmd.addAll(Arrays.asList(command.trim().split(" ")));

            String order = cmd.elementAt(0);

            if (order.equals("quit")) {
                c.quit();
                session.disconnect();
                return;
            }
            if (order.equals("exit")) {
                c.exit();
                session.disconnect();
                return;
            }
            if (order.equals("rekey")) {
                session.rekey();
                return;
            }
            if (order.equals("compression")) {
                if (cmd.size() < 2) {
                    logger.info("compression level: " + level);
                    return;
                }
                try {
                    level = Integer.parseInt((String) cmd.elementAt(1));
                    if (level == 0) {
                        session.setConfig("compression.s2c", "none");
                        session.setConfig("compression.c2s", "none");
                    } else {
                        session.setConfig("compression.s2c", "zlib@openssh.com,zlib,none");
                        session.setConfig("compression.c2s", "zlib@openssh.com,zlib,none");
                    }
                } catch (Exception e) {
                    logger.error("execute - {} Exception" + command);
                    logger.error(e.toString());
                }
                session.rekey();
                return;
            }
            if (order.equals("cd") || order.equals("lcd")) {
                if (cmd.size() < 2)
                    return;
                String path = (String) cmd.elementAt(1);
                try {
                    if (order.equals("cd"))
                        c.cd(path);
                    else
                        c.lcd(path);
                } catch (SftpException e) {
                    logger.error("execute - {} Exception" + command);
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("rm") || order.equals("rmdir") || order.equals("mkdir")) {
                if (cmd.size() < 2)
                    return;
                String path = (String) cmd.elementAt(1);
                try {
                    if (order.equals("rm"))
                        c.rm(path);
                    else if (order.equals("rmdir"))
                        c.rmdir(path);
                    else
                        c.mkdir(path);
                } catch (SftpException e) {
                    logger.error("execute - {} Exception" + command);
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("chgrp") || order.equals("chown") || order.equals("chmod")) {
                if (cmd.size() != 3)
                    return;
                String path = (String) cmd.elementAt(2);
                int foo = 0;
                if (order.equals("chmod")) {
                    byte[] bar = ((String) cmd.elementAt(1)).getBytes();
                    int k;
                    for (int j = 0; j < bar.length; j++) {
                        k = bar[j];
                        if (k < '0' || k > '7') {
                            foo = -1;
                            break;
                        }
                        foo <<= 3;
                        foo |= (k - '0');
                    }
                    if (foo == -1)
                        return;
                } else {
                    try {
                        foo = Integer.parseInt((String) cmd.elementAt(1));
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                        return;
                    }
                }
                try {
                    if (order.equals("chgrp")) {
                        c.chgrp(foo, path);
                    } else if (order.equals("chown")) {
                        c.chown(foo, path);
                    } else if (order.equals("chmod")) {
                        c.chmod(foo, path);
                    }
                } catch (SftpException e) {
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("pwd") || order.equals("lpwd")) {
                String str = (order.equals("pwd") ? "Remote" : "Local");
                str += " working directory: ";
                if (order.equals("pwd"))
                    str += c.pwd();
                else
                    str += c.lpwd();
                logger.info(str);
                return;
            }
            if (order.equals("ls") || order.equals("dir")) {
                String path = ".";
                if (cmd.size() == 2)
                    path = (String) cmd.elementAt(1);
                try {
                    @SuppressWarnings("rawtypes")
                    Vector vv = c.ls(path);
                    if (vv != null) {
                        for (int ii = 0; ii < vv.size(); ii++) {
                            // logger.info(vv.elementAt(ii).toString());

                            Object obj = vv.elementAt(ii);
                            if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
                                logger.info(((com.jcraft.jsch.ChannelSftp.LsEntry) obj).getLongname());
                            }
                        }
                    }
                } catch (SftpException e) {
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("lls") || order.equals("ldir")) {
                String path = ".";
                if (cmd.size() == 2)
                    path = (String) cmd.elementAt(1);
                try {
                    java.io.File file = new java.io.File(path);
                    if (!file.exists()) {
                        logger.info(path + ": No such file or directory");
                        return;
                    }
                    if (file.isDirectory()) {
                        String[] list = file.list();
                        for (int ii = 0; ii < list.length; ii++) {
                            logger.info(list[ii]);
                        }
                        return;
                    }
                    logger.info(path);
                } catch (Exception e) {
                    logger.error("execute - {} Exception" + command);
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("get") || order.equals("get-resume") || order.equals("get-append") || order.equals("put")
                    || order.equals("put-resume") || order.equals("put-append")) {
                if (cmd.size() != 2 && cmd.size() != 3)
                    return;
                String p1 = (String) cmd.elementAt(1);
                String p2 = ".";
                if (cmd.size() == 3)
                    p2 = (String) cmd.elementAt(2);
                try {
                    // SftpProgressMonitor monitor = new MyProgressMonitor();
                    if (order.startsWith("get")) {
                        int mode = ChannelSftp.OVERWRITE;
                        if (order.equals("get-resume")) {
                            mode = ChannelSftp.RESUME;
                        } else if (order.equals("get-append")) {
                            mode = ChannelSftp.APPEND;
                        }
                        // c.get(p1, p2, monitor, mode);
                        c.get(p1, p2);
                    } else {
                        int mode = ChannelSftp.OVERWRITE;
                        if (order.equals("put-resume")) {
                            mode = ChannelSftp.RESUME;
                        } else if (order.equals("put-append")) {
                            mode = ChannelSftp.APPEND;
                        }
                        c.put(p1, p2, mode);
                    }
                } catch (SftpException e) {
                    logger.error("execute - {} Exception" + command);
                    logger.info(e.getMessage());
                    throw e;
                }
                return;
            }
            if (order.equals("ln") || order.equals("symlink") || order.equals("rename") || order.equals("hardlink")) {
                if (cmd.size() != 3)
                    return;
                String p1 = (String) cmd.elementAt(1);
                String p2 = (String) cmd.elementAt(2);
                try {
                    if (order.equals("hardlink")) {
                        c.hardlink(p1, p2);
                    } else if (order.equals("rename"))
                        c.rename(p1, p2);
                    else
                        c.symlink(p1, p2);
                } catch (SftpException e) {
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("df")) {
                if (cmd.size() > 2)
                    return;
                String p1 = cmd.size() == 1 ? "." : (String) cmd.elementAt(1);
                SftpStatVFS stat = c.statVFS(p1);

                long size = stat.getSize();
                long used = stat.getUsed();
                long avail = stat.getAvailForNonRoot();
                long root_avail = stat.getAvail();
                long capacity = stat.getCapacity();

                System.out.println("Size: " + size);
                System.out.println("Used: " + used);
                System.out.println("Avail: " + avail);
                System.out.println("(root): " + root_avail);
                System.out.println("%Capacity: " + capacity);

                return;
            }
            if (order.equals("stat") || order.equals("lstat")) {
                if (cmd.size() != 2)
                    return;
                String p1 = (String) cmd.elementAt(1);
                SftpATTRS attrs = null;
                try {
                    if (order.equals("stat"))
                        attrs = c.stat(p1);
                    else
                        attrs = c.lstat(p1);
                } catch (SftpException e) {
                    logger.error(e.getMessage());
                }
                if (attrs != null) {
                    logger.info(attrs.toString());
                } else {
                }
                return;
            }
            if (order.equals("readlink")) {
                if (cmd.size() != 2)
                    return;
                String p1 = (String) cmd.elementAt(1);
                String filename = null;
                try {
                    filename = c.readlink(p1);
                    logger.info(filename);
                } catch (SftpException e) {
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("realpath")) {
                if (cmd.size() != 2)
                    return;
                String p1 = (String) cmd.elementAt(1);
                String filename = null;
                try {
                    filename = c.realpath(p1);
                    logger.info(filename);
                } catch (SftpException e) {
                    logger.error("execute - {} Exception" + command);
                    logger.error(e.getMessage());
                }
                return;
            }
            if (order.equals("version")) {
                logger.info("SFTP protocol version " + c.version());
                return;
            }
            if (order.equals("help") || order.equals("?")) {
                logger.info(help);
                return;
            }
            logger.info("unimplemented command: " + order);
        } catch (Exception e) {
            logger.error(e.getMessage());
            // throw new MdmException(e);
        }
    }

    public void put(InputStream src, String dst, SftpProgressMonitor monitor, int mode) {
        try {
            c.put(src, dst, monitor, mode);
        } catch (SftpException e) {
            // throw new MDCPCoreException(e);
        }
    }

    public void rename(String oldpath, String newpath) {
        try {
            c.rename(oldpath, newpath);
        } catch (SftpException e) {
            // throw new MDCPCoreException(e);
        }
    }

    public String executeWithReturn(String command) throws JSchException, IOException {
        InputStream in = null;
        InputStream err = null;
        BufferedReader inReader = null;
        BufferedReader errReader = null;
        boolean isFinished = false;

        String result = null;
        String current = null;

        logger.info("Execute [" + command + "]");

        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(null);
        in = channel.getInputStream();
        err = ((ChannelExec) channel).getErrStream();
        channel.connect();
        inReader = new BufferedReader(new InputStreamReader(in, ENCODING_FORMAT));
        errReader = new BufferedReader(new InputStreamReader(err, ENCODING_FORMAT));
        while (true) {
            current = errReader.readLine();
            if (current != null) {
                result = null;
            } else {
                isFinished = true;
                break;
            }
        }
        while (true) {
            current = inReader.readLine();
            if (current != null) {
                result = current;
            } else {
                isFinished = true;
                break;
            }
        }
        
        logger.info("Command [" + command + "] finished.");
        inReader.close();
        errReader.close();
        channel.disconnect();
        session.disconnect();
        return result;
    }

    private static String help = "      Available commands:\n" + "      * means unimplemented command.\n"
            + "cd path                       Change remote directory to 'path'\n"
            + "lcd path                      Change local directory to 'path'\n"
            + "chgrp grp path                Change group of file 'path' to 'grp'\n"
            + "chmod mode path               Change permissions of file 'path' to 'mode'\n"
            + "chown own path                Change owner of file 'path' to 'own'\n"
            + "df [path]                     Display statistics for current directory or\n"
            + "                              filesystem containing 'path'\n"
            + "help                          Display this help text\n"
            + "get remote-path [local-path]  Download file\n"
            + "get-resume remote-path [local-path]  Resume to download file.\n"
            + "get-append remote-path [local-path]  Append remote file to local file\n"
            + "hardlink oldpath newpath      Hardlink remote file\n"
            + "*lls [ls-options [path]]      Display local directory listing\n"
            + "ln oldpath newpath            Symlink remote file\n"
            + "*lmkdir path                  Create local directory\n"
            + "lpwd                          Print local working directory\n"
            + "ls [path]                     Display remote directory listing\n"
            + "*lumask umask                 Set local umask to 'umask'\n"
            + "mkdir path                    Create remote directory\n" + "put local-path [remote-path]  Upload file\n"
            + "put-resume local-path [remote-path]  Resume to upload file\n"
            + "put-append local-path [remote-path]  Append local file to remote file.\n"
            + "pwd                           Display remote working directory\n"
            + "stat path                     Display info about path\n" + "exit                          Quit sftp\n"
            + "quit                          Quit sftp\n" + "rename oldpath newpath        Rename remote file\n"
            + "rmdir path                    Remove remote directory\n"
            + "rm path                       Delete remote file\n"
            + "symlink oldpath newpath       Symlink remote file\n"
            + "readlink path                 Check the target of a symbolic link\n"
            + "realpath path                 Canonicalize the path\n"
            + "rekey                         Key re-exchanging\n"
            + "compression level             Packet compression will be enabled\n"
            + "version                       Show SFTP version\n" + "?                             Synonym for help";
}

