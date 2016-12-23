package com.canmeizhexue.javademo.io;

import com.canmeizhexue.javademo.utils.Base64;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.*;

/**jar文件操作，主要注意路径分隔符
 * Created by silence on 2016-12-23.
 */
public class JarDemo {
    public static void main(String[]args){
//        extract("C:\\Users\\zengyaping\\IdeaProjects\\apkpatch-1.0.3.jar","C:\\Users\\zengyaping\\IdeaProjects\\test");
        String path = "C:\\Users\\zengyaping\\IdeaProjects\\test\\";
        String jarFile = "C:\\Users\\zengyaping\\IdeaProjects\\test.jar";
        String outPath="C:\\Users\\zengyaping\\IdeaProjects\\silence\\";
        createJar(path,jarFile);
        extract(jarFile,outPath);
    }
    public static void createJar(String folder,String jarFileName){
        try {
            File dir = new File(folder);
            File jarFile = new File(jarFileName);
            File jarFileDir = jarFile.getParentFile();
            if(!jarFileDir.exists()){
                jarFileDir.mkdirs();
            }
            if(!jarFile.exists()){
                jarFile.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(jarFileName);
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,"1.0");
            JarOutputStream jarOutputStream = new JarOutputStream(fileOutputStream/*, manifest*/);
            doCreateJar(dir,jarOutputStream,dir.getPath());
            jarOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static byte[]buffer = new byte[256];
    private static int length = 0;
    private static void doCreateJar(File dir,JarOutputStream jarOutputStream,String baseAddr){
        try {
            BufferedInputStream bufferedInputStream=null;
            if(dir.isDirectory()){
                String name = dir.getPath().replace("\\","/");
                if(!name.isEmpty()){
                    if (!name.endsWith("/")) {
                        name += "/";
                    }
                    name = name.substring(baseAddr.length());
                    JarEntry jarEntry = new JarEntry(name);
                    jarEntry.setTime(dir.lastModified());
                    jarOutputStream.putNextEntry(jarEntry);
                    jarOutputStream.closeEntry();
                    for(File nestedFile:dir.listFiles()){
                        doCreateJar(nestedFile,jarOutputStream,baseAddr);
                    }
                    return;

                }
            }
            String middleName = dir.getPath().replace("\\","/").substring(baseAddr.length());
            JarEntry jarEntry = new JarEntry(middleName);
            jarEntry.setTime(dir.lastModified());
            jarOutputStream.putNextEntry(jarEntry);
            bufferedInputStream = new BufferedInputStream(new FileInputStream(dir));
            while (true){
                length = bufferedInputStream.read(buffer);
                if(length==-1){
                    break;
                }
                jarOutputStream.write(buffer,0,length);
            }
            jarOutputStream.closeEntry();
            bufferedInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void extract(String jarFileName,String outFolder){
        byte[] block = new byte[256];
        int length;
        try{
            File folder = new File(outFolder);
            if(!folder.exists()){
                folder.mkdirs();
            }
            JarFile jarFile = new JarFile(jarFileName);
            Enumeration<JarEntry> enumeration = jarFile.entries();
            while (enumeration.hasMoreElements()){
                JarEntry jarEntry = enumeration.nextElement();

                InputStream inputStream = jarFile.getInputStream(jarEntry);
                String newFileName = jarEntry.getName();

                int lastIndex = newFileName.lastIndexOf("/");
                if(lastIndex>=0){
                    File subFolder = new File(outFolder,newFileName.substring(0,lastIndex));
                    if(!subFolder.exists()){
                        subFolder.mkdirs();
                    }
                }
                if(jarEntry.isDirectory()){
                    //目录也会存在一个entry
                    System.out.println("jarentry name is "+newFileName+"---------"+jarEntry.isDirectory());
                    continue;
                }

                File file = new File(outFolder,newFileName);
                if(file!=null){
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((length=inputStream.read(block))>0){
                    fileOutputStream.write(block,0,length);
                }
                inputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
