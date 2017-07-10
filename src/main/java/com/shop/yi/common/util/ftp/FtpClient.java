package com.shop.yi.common.util.ftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @ClassName: FtpClient
 * @Description: TODO ftp客户端
 * @author: yanguo(****@email.com)
 * @date: 2017年5月29日 下午8:31:58
 * @Version: 1.0.0
 */
public class FtpClient {
	private FTPClient client;

	public FtpClient(String host, String userName, String password) throws SocketException, IOException {
		initFtpClient(host, 21, userName, password);
	}
	public FtpClient(String host, int port, String userName, String password) throws SocketException, IOException {
		initFtpClient(host, port, userName, password);
	}
	/**
	 * 登录
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @throws SocketException
	 * @throws IOException
	 */
	public void initFtpClient(String host, int port, String userName, String password) throws SocketException,
			IOException {
		client = new FTPClient();
		client.connect(host, port);
		client.login(userName, password);
	}
	/**
	 * 得到所有目录
	 * @param remotePath
	 * @return
	 * @throws IOException
	 */
	public FTPFile[] listFiles(String remotePath) throws IOException {
		if (client == null)
			return null;
		client.changeWorkingDirectory(remotePath);
		return client.listFiles();
	}
	/**
	 * 上传
	 * @param localPath 本地路径
	 * @param remotePath ftp路径
	 * @return 上传是否成功
	 * @throws IOException
	 */
	public boolean upload(String localPath, String remotePath) throws IOException {
		if (client == null)
			return false;
		boolean res = false;
		FileInputStream fileInputStream = new FileInputStream(localPath);
		int index = remotePath.lastIndexOf('/');
		if (index != -1) {
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.changeWorkingDirectory(remotePath.substring(0, index));
			res = client.storeFile(remotePath.substring(index + 1), fileInputStream);
		}
		fileInputStream.close();
		return res;
	}
	/**
	 * 下载
	 * @param remotePath ftp路径
	 * @param localPath 本地路径
	 * @return 下载是否成功
	 * @throws IOException
	 */
	public boolean download(String remotePath, String localPath) throws IOException {
		if (client == null)
			return false;
		boolean res = false;
		FileOutputStream fileOutputStream = new FileOutputStream(localPath);
		res = client.retrieveFile(remotePath, fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
		return res;
	}
	/**
	 * 删除文件
	 * @param remotePath ftp端路径
	 * @return
	 * @throws IOException
	 */
	public boolean delete(String remotePath) throws IOException {
		if (client == null)
			return false;
		return client.deleteFile(remotePath) || deleteDirectory(remotePath);
	}
	/**
	 * 创建目录
	 * @param remotePath
	 * @throws IOException
	 */
	public boolean makeDirectory(String remotePath) throws IOException {
		if (client == null)
			return false;
		String[] item = remotePath.split("/");
		String currentPath = "";
		for (int i = 0; i < item.length - 1; i++) {
			currentPath = currentPath + "/" + item[i];
			client.makeDirectory(currentPath);
		}
		return client.makeDirectory(remotePath);
	}
	/**
	 * 删除文件
	 * @param remotePath ftp端路径
	 * @return
	 * @throws IOException
	 */
	private boolean deleteDirectory(String remotePath) throws IOException {
		FTPFile[] files = listFiles(remotePath);
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				deleteDirectory(remotePath + "/" + files[i].getName());
			} else {
				client.deleteFile(remotePath + "/" + files[i].getName());
			}
		}
		return client.removeDirectory(remotePath);
	}
	/**
	 * 重命名
	 * @param remoteOldPath
	 * @param remoteNewPath
	 * @return
	 * @throws IOException
	 */
	public boolean rename(String remoteOldPath, String remoteNewPath) throws IOException {
		if (client == null)
			return false;
		return client.rename(remoteOldPath, remoteNewPath);
	}
	/**
	 * 退出登录
	 * @throws IOException
	 */
	public void close() throws IOException {
		if (client != null)
			client.logout();
	}
}
