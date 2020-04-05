package org.young.commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.young.commons.exception.DatahubException;

public class ZipUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * zip压缩
	 * @param outputStream 输出流
	 * @param inputDir 待压缩的目录
	 */
	public static void zip(OutputStream outputStream, File inputDir) {
		ArchiveOutputStream out = null;
		FileInputStream input = null;
		try {
			out = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, outputStream);
			String zipDir = inputDir.getPath();
			Iterator<File> files = FileUtils.iterateFiles(inputDir, null, true);
			while (files.hasNext()) {
				File file = files.next();
				try {
					input = new FileInputStream(file);
					String path = file.getPath().substring(zipDir.length()+1);
					ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, path);
					out.putArchiveEntry(zipArchiveEntry);
					IOUtils.copy(input, out);
					
				} finally {
					out.closeArchiveEntry();
					IOUtils.closeQuietly(input);
				}
			}
			out.finish();
		} catch (ArchiveException e) {
			LOGGER.error("ArchiveException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(input);
		}

	}
	
	/***
	 * 通过Java运行时命令解压文件<br/> 
	 * 已过时, 使用ZipUtil.unzipByStream()替代<br/> 
	 * 
	 * @param zip 仅支持*.zip, *.gz, *.tar, *.tar.gz文件
	 * @param deleteSrc 是否删除源文件
	 * @throws DatahubException 
	 */
	@Deprecated
	public static void unzipByCmd(File zip, String path, boolean deleteSrc) throws DatahubException{
		if(zip==null){
			return;
		}
		if(StringUtils.isBlank(path)){
			path = "./";
		}
		File pathF = new File(path);
		if(!pathF.isDirectory()){
			pathF.mkdirs();
		}
		String ext = FilenameUtils.getExtension(zip.getName());
		StringBuffer cmd = new StringBuffer(0);
		if("zip".equalsIgnoreCase(ext)){
			cmd.append("unzip -oO CP936 ");
			cmd.append(zip.getPath());
			cmd.append(" -d ");
			cmd.append(path);
			try {
				executeCmd(cmd.toString());
			} catch (DatahubException de) {
				// 解决没有大写O参数版本的unzip命令
				LOGGER.info("the unzip command has no -O parameters and decompresses");
				cmd.setLength(0);
				cmd.append("unzip -o ");
				cmd.append(zip.getPath());
				cmd.append(" -d ");
				cmd.append(path);
				executeCmd(cmd.toString());
			} finally {
				if(deleteSrc && zip.exists()){
					zip.delete();
				}
			}
			return ;
		}
		else if ("tar".equalsIgnoreCase(ext) || zip.getName().toLowerCase().endsWith(".tar.gz")) {
			cmd.append("tar -xvf ");
			cmd.append(zip.getPath());
			cmd.append(" -C ");
			cmd.append(path);
		}
		else if ("gz".equalsIgnoreCase(ext)) {
			cmd.append("gunzip -df ");
			cmd.append(zip.getPath());
//			cmd.append(" -c > ");
//			cmd.append(new File(path, FilenameUtils.getBaseName(zip.getPath())));
		}
		// 非压缩格式
		else{
			return ;
		}
		
		try {
			
			executeCmd(cmd.toString());
		} finally {
			if(deleteSrc && zip.exists()){
				zip.delete();
			}
		}
		
		
	}
	
	/**
	 * Java运行时执行命令
	 * @param cmd 命令
	 * @throws DatahubException
	 */
	public static void executeCmd(String cmd) throws DatahubException{
		BufferedReader error = null;
		try {
			if(StringUtils.isNotBlank(cmd)){
				LOGGER.info(cmd);
				Process pro = Runtime.getRuntime().exec(cmd);
				int res = pro.waitFor();
				error = new BufferedReader(new InputStreamReader(pro.getErrorStream()));

				if(res!=0){
					LOGGER.error(error.readLine());
					while (error.ready()) {
						LOGGER.error(error.readLine());
					}
					throw new DatahubException(String.format("execute command exception [%s]", cmd));
				}
			}
		} catch (DatahubException de) {
			throw de;
		} catch (Exception e) {
			throw new DatahubException(e, "the Java runtime executes the command exception\n%s", cmd);
		} finally {
			IOUtils.closeQuietly(error);
		}
		
	}
	
	/**
	 * 通过流的方式解压文件, 支持*.zip, *.tar, *.tar.gz, *.gz
	 * 
	 * @param zip zip 文件
	 * @param outDir 输出路径
	 * @param deleteSrc 输出源文件
	 * @throws DatahubException
	 */
	public static void unzipByStream(File zip, String outDir, boolean deleteSrc) throws DatahubException{
		if(zip==null){
			return;
		}
		String ext = FilenameUtils.getExtension(zip.getName());
		if("zip".equalsIgnoreCase(ext)){
			unzip(zip, outDir, deleteSrc);
		}
		else if ("tar".equalsIgnoreCase(ext) || StringUtils.endsWithIgnoreCase(zip.getName(), ".tar.gz")) {
			untar(zip, outDir, deleteSrc);
		}
		else if ("gz".equalsIgnoreCase(ext)) {
			ungzip(zip, outDir, deleteSrc);
		}
		// 非压缩格式
		else{
			return ;
		}
		
	}
	
	/**
	 * 解压zip文件
	 * 
	 * @param zip zip 文件
	 * @param outDir 输出路径
	 * @param deleteSrc 输出源文件
	 * @throws DatahubException
	 */
	public static void unzip(File zip, String outDir, boolean deleteSrc) throws DatahubException{
		
		if(zip==null || !zip.exists()){
			return;
		}
		
		String ext = FilenameUtils.getExtension(zip.getName());
		if (!"zip".equalsIgnoreCase(ext)) {
			return;
		}
		
		if(StringUtils.isBlank(outDir)){
			outDir = zip.getParent();
		}
		
		File dir = new File(outDir);
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		
		ZipFile zipFile = null;
		try {  
			
            zipFile =  new ZipFile(zip, Charset.defaultCharset());
  
            Enumeration<?> enums = zipFile.entries();
            LOGGER.info("unzip files {}", zip.getPath());
            while(enums.hasMoreElements()){  
                ZipEntry entry = (ZipEntry) enums.nextElement();
                LOGGER.info(FileUtil.concat(outDir, entry.getName()));
                
                String filename = entry.getName();
                String name = FilenameUtils.getBaseName(filename);
    			String exte = FilenameUtils.getExtension(filename);
    			if(StringUtils.isNotBlank(exte)){
    				filename = name + "." + exte.toLowerCase();
    			}
                File file = new File(outDir, filename);
                // 对象是目录且目录不存在
                if(entry.isDirectory()){
                	if(!file.isDirectory()){
                		file.mkdirs();
                	}
                }
                
                // 对象是文件
                else {
                	
					if(!file.getParentFile().isDirectory()){
						file.getParentFile().mkdirs();
					}
					// 写文件
					InputStream fin 	= null;
					OutputStream fout 	= null;
					try {
						fin = zipFile.getInputStream(entry);
						fout = new FileOutputStream(file);
						
						byte[] buf=new byte[1024];
						IOUtils.copyLarge(fin, fout, buf);
						fout.flush();
						
					} catch (IOException e) {
						throw e;
					} finally {
						IOUtils.closeQuietly(fin);
						IOUtils.closeQuietly(fout);
					}
				}  
            }  
              
        }catch (Exception e){  
			throw new DatahubException(e, "file decompression exception\n%s", zip);
		} finally {
			IOUtils.closeQuietly(zipFile);
			if(deleteSrc){
				FileUtils.deleteQuietly(zip);
			}
		}

	}
	
	/**
	 * 解压tar或tar.gz文件
	 * 
	 * @param zip zip 文件
	 * @param outDir 输出路径
	 * @param deleteSrc 输出源文件
	 * @throws DatahubException
	 */
	public static void untar(File zip, String outDir, boolean deleteSrc) throws DatahubException {
		
		if(zip==null || !zip.exists()){
			return;
		}
		
		boolean flg1 = StringUtils.endsWithIgnoreCase(zip.getName(), ".tar.gz");
		boolean flg2 = StringUtils.endsWithIgnoreCase(zip.getName(), ".tar");
		
		if(!(flg1||flg2)){
			return;
		}
		
		if(StringUtils.isBlank(outDir)){
			outDir = zip.getParent();
		}
		
		File dir = new File(outDir);
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		TarArchiveInputStream in 	= null;
		FileOutputStream fout 	= null;
		try {  
			//建立解压工作流
			in = new TarArchiveInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(zip), 1024)));   
			
			TarArchiveEntry entry = null;
			LOGGER.info("unzip files {}", zip.getPath());
			
			while( (entry = in.getNextTarEntry()) != null ){
				
				LOGGER.info(FileUtil.concat(outDir, entry.getName()));
				// 对象是目录且不存在
				File file = new File(dir, entry.getName());
				if(entry.isDirectory()){
					if(!file.isDirectory()){
                		file.mkdirs();
                	}
				}
				
				// 对象是文件
				else{
					if(!file.getParentFile().isDirectory()){
						file.getParentFile().mkdirs();
					}
					
					try {
						fout = new FileOutputStream(file);
						
						byte[] buf=new byte[1024];
						IOUtils.copyLarge(in, fout, buf);
						fout.flush();
						
					} catch (IOException e) {
						throw e;
					} finally {
						IOUtils.closeQuietly(fout);
					}
				}
			}
			
		} catch (Exception e){  
			throw new DatahubException("file decompression error: "+zip.getAbsolutePath(), e);
		} finally {
			IOUtils.closeQuietly(in);
			if(deleteSrc){
				FileUtils.deleteQuietly(zip);
			}
		}
		
	}

	/**
	 * 解压gzip文件
	 * 
	 * @param zip zip 文件
	 * @param outDir 输出路径
	 * @param deleteSrc 输出源文件
	 * @throws DatahubException
	 */
	public static File ungzip(File zip, String outDir, boolean deleteSrc) throws DatahubException {
		
		if(zip==null || !zip.exists()){
			return null;
		}
		
		String ext = FilenameUtils.getExtension(zip.getName());
		if (!"gz".equalsIgnoreCase(ext)) {
			return null;
		}
		
		if(StringUtils.isBlank(outDir)){
			outDir = zip.getParent();
		}
		
		File dir = new File(outDir);
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		
		GZIPInputStream gzin 	= null;
		FileOutputStream fout 	= null;
		File file = null;
		try {  
			//建立gzip解压工作流
			gzin = new GZIPInputStream(new FileInputStream(zip));
			file = new File(dir, FilenameUtils.getBaseName(zip.getName()));
			//建立解压文件输出流  
			fout = new FileOutputStream(file);   
			LOGGER.info("unzip files {}", zip.getPath());
			byte[] buf =new byte[1024];
			IOUtils.copyLarge(gzin, fout, buf);
			LOGGER.info(file.getPath());
			fout.flush();
			
		} catch (Exception e){
		    throw new DatahubException("file decompression error: "+zip.getAbsolutePath(), e);
		} finally {
			IOUtils.closeQuietly(gzin);
			IOUtils.closeQuietly(fout);
			if(deleteSrc){
				FileUtils.deleteQuietly(zip);
			}
		}
		
		return file;
	}

}
