package com.shop.yi.common.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private final static String FILE_SUFFIX = ".java.drl";

    private final static String FILE_TEMP = "e:/temp/";

    /**
     * 将已存在的drl文件删除
     * 
     * @param ObjectPath
     */
    public static void deleteExistedDRLFile(String ObjectPath) {
        File filePath = new File(ObjectPath);
        if (!filePath.exists()) {
        	if(LOGGER.isDebugEnabled())
				LOGGER.debug("目录不存在!");
        } else {
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith(FILE_SUFFIX);
                    }
                });
                for (int i = 0; i < list.length; i++) {
                    list[i].delete();
                }
            }
        }
    }

    /**
     * 创建文件夹,如果有则不创建
     * 
     * @param ObjectPath
     */
    public static boolean creareDirectory(String ObjectPath) {
        boolean flag = true;

        File filePath = new File(ObjectPath);
        if (!filePath.exists()) {
            filePath.mkdir();
            flag = false;
        }

        return flag;
    }

    /**
     * 查看某文件夹下面是否有文件,有文件则创建一个temp文件夹,将文件拷贝到temp目录下(备份文件) 没有文件怎什么都不做
     * 备份后，把原文件夹里文件删除
     * 
     * @param ObjectPath
     */
    public static void backupFile(String ObjectPath, String dirName) {
        String backupPath;

        if (!FILE_TEMP.endsWith(File.separator)) {
            backupPath = FILE_TEMP + File.separator + dirName;
        } else {
            backupPath = FILE_TEMP + dirName;
        }

        File backupFilePath = new File(backupPath);
        if (!backupFilePath.exists()) {
            backupFilePath.mkdirs();
        }
        File filePath = new File(ObjectPath);
        if (!filePath.exists()) {
        	if(LOGGER.isDebugEnabled())
				LOGGER.debug("目录不存在!");
        } else {
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles();
                if (list != null && list.length != 0) {
                    copyFolder(ObjectPath, backupPath);// 文件备份
                    for (int i = 0; i < list.length; i++) {
                        list[i].delete();
                    }
                }
            }
        }
    }

    /**
     * 复原文件，把文件从备份文件夹拷贝到原来文件夹
     * 
     * @param ObjectPath
     * @param dirName
     */
    public static void recoverFile(String ObjectPath, String dirName) {
        String backupPath;
        if (ObjectPath.endsWith(File.separator)) {
            ObjectPath = new StringBuffer(ObjectPath).append(dirName)
                    .toString();
        } else {
            ObjectPath = new StringBuffer(ObjectPath)
                    .append(File.separatorChar).append(dirName).toString();
        }

        if (!FILE_TEMP.endsWith(File.separator)) {
            backupPath = FILE_TEMP + File.separator + dirName;
        } else {
            backupPath = FILE_TEMP + dirName;
        }
        File backupFilePath = new File(backupPath);
        if (!backupFilePath.exists()) {
            backupFilePath.mkdirs();
        }
        File filePath = new File(ObjectPath);
        if (!filePath.exists()) {
        	if(LOGGER.isDebugEnabled())
				LOGGER.debug("目录不存在!");
        } else {
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles();
                if (list != null && list.length != 0) {
                    copyFolder(backupPath, ObjectPath);// 文件复原
                }
            }
        }
    }

    /**
     * 复制整个文件夹内容
     * 
     * @param oldPath
     *            String 原文件路径 如：c:/fqf
     * @param newPath
     *            String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdir(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());

                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
        	if(LOGGER.isDebugEnabled())
				LOGGER.debug("复制整个文件夹内容操作出错", e);
        }
    }

    /**
     * 删除备份文件和存放备份文件的文件夹
     * 
     * @param ObjectPath
     */
    public static void deleteFileAndDirectory(String dirName) {
        String ObjectPath;
        if (!FILE_TEMP.endsWith(File.separator)) {
            ObjectPath = FILE_TEMP + File.separator + dirName;
        } else {
            ObjectPath = FILE_TEMP + dirName;
        }

        File filePath = new File(ObjectPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
            if(LOGGER.isDebugEnabled())
				LOGGER.debug("目录不存在!");
        } else {
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles();

                for (int i = 0; i < list.length; i++) {
                    list[i].delete();
                }
            }
            filePath.delete();
        }
    }

    /**
     * 判断某文件夹下是否存在文件，存在返回true
     * 
     * @param ObjectPath
     * @return
     */
    public static boolean existFileInDirectory(String ObjectPath) {
        boolean flag = false;
        File filePath = new File(ObjectPath);
        if (filePath.exists()) {

            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles();
                if (list != null && list.length != 0) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    /**
     * 删除某个文件夹
     * @param ObjectPath
     */
    public static void deleteDirectory(String ObjectPath) {

        File filePath = new File(ObjectPath);
        if (filePath.exists()) {
            filePath.delete();
        }
    }
    /**
     * 将已存在的文件删除
     * 
     * @param ObjectPath
     */
    public static boolean deleteExistedFile(String ObjectPath,final String fileName) {
        boolean flag =false;
        File filePath = new File(ObjectPath);
        if (filePath.exists()) {            
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.equals(fileName);
                    }
                });
                for (int i = 0; i < list.length; i++) {
                    list[i].delete();
                }
                flag=true;
            }
        }
         
        return flag;
    }
    
 
    /**
     * 压缩文件
     * 
     * @param filePath
     *            待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zip(String filePath) {
        File target = null;
        File source = new File(filePath);
        if (source.exists()) {
            // 压缩文件名=源文件名.zip
            String zipName = source.getName() + ".zip";
            target = new File(source.getParent(), zipName);
            if (target.exists()) {
                target.delete(); // 删除旧的文件
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                // 添加对应的文件Entry
                addEntry("/", source, zos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                closeQuietly(zos, fos);
            }
        }
        return target;
    }
 
    /**
     * 扫描添加文件Entry
     * 
     * @param base
     *            基路径
     * 
     * @param source
     *            源文件
     * @param zos
     *            Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos)
            throws IOException {
        // 按目录分级，形如：/aaa/bbb.txt
        String entry = base + source.getName();
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                // 递归列出目录下的所有文件，添加文件Entry
                addEntry(entry + "/", file, zos);
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                closeQuietly(bis, fis);
            }
        }
    }
 
    /**
     * 解压文件
     * 
     * @param filePath
     *            压缩文件路径
     */
    public static void unzip(String filePath) {
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null
                        && !entry.isDirectory()) {
                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists()) {
                        // 创建文件父目录
                        target.getParentFile().mkdirs();
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                closeQuietly(zis, bos);
            }
        }
    }
    
    
    /**
     * 关闭一个或多个流对象
     * 
     * @param closeables
     *            可关闭的流对象列表
     * @throws IOException
     */
    public static void close(Closeable... closeables) throws IOException {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        }
    }
 
    /**
     * 关闭一个或多个流对象
     * 
     * @param closeables
     *            可关闭的流对象列表
     */
    public static void closeQuietly(Closeable... closeables) {
        try {
            close(closeables);
        } catch (IOException e) {
            // do nothing
        }
    }
    
    /*public static void main(String[] args) {
        String targetPath = "E:\\Win7壁纸";
        File file = ZipUtil.zip(targetPath);
        System.out.println(file);
        ZipUtil.unzip("F:\\Win7壁纸.zip");
    }*/
}