package cn.tds.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import cn.tds.zcraft.Machines;
import cn.tds.zcraft.Main;

public class TalexFiles extends MessagesUtil {

	public static YamlConfiguration langYaml = null;
	

//	private static boolean ExportResource(String resourceName,String exportPath) throws IOException {
//		
//		
//		URL url = Main.getPlugin().getClass().getClassLoader().getResource(resourceName);
//		sendExternalMessage(0,"§b导出资源文件: " + resourceName,true);
//		if(url == null) {
//			sendExternalMessage(0,"§c无法从Jar文件中读取 §o" + resourceName + "§c文件,请检查文件是否被修改！插件已自动卸载！",true);
//			Main.setPluginEnabled(false);
//			//throw new FileNotFoundException("Chinese.yml");
//			return false;
//		}
//		
//		InputStream in = Main.class.getResourceAsStream(resourceName);
//		
//		
////		JarURLConnection conn = (JarURLConnection) url.openConnection();
////		InputStream in = conn.getInputStream();
//		sendExternalMessage(0,"§aInputStream 创建完毕！",true);
//		String str = file2String(in);
//		str = Encode_UTF8(str);
//		File langFile = new File(exportPath + resourceName);	
//		writeFileByBytes(langFile,str);
//		return true;
//	}
	
	public static boolean hasFile(String path) {
		File file = new File(path);
		return file.exists();
	}
	
//	private static String file2String(InputStream input) {
//		
//		String text = null;
//		try {
////			sendExternalMessage(0,"§4Begin",true);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            String str;
////            sendExternalMessage(4,"§4Start while",true);
//            while ((str = reader.readLine()) != null) {
//            	sendExternalMessage(0,str,true);
//                text = text + str;
//            }
////            sendExternalMessage(0,"§4While null",true);
//        } catch (IOException e) {
//        	e.printStackTrace();
//        }
////		sendExternalMessage(0,"§4Wrong",true);
//		return text;
//	}
//	
//	public static void writeFileByBytes(File file,String content) { //以字节为单位写入文件
//		OutputStream out =null;
//		try {
//			out=new FileOutputStream(file);  //打开文件输出流
//			byte[] bytes= content.getBytes();  //读取输出流中的字节
//			out.write(bytes);     //写入文件
//			sendExternalMessage(4, "写文件"+file.getAbsolutePath()+"成功！", true);
//		} catch(IOException e) {
//			sendExternalMessage(4, "写文件"+file.getAbsolutePath()+"失败！", true);
//			e.printStackTrace();
//		} finally {      //内容总执行
//			if(out!=null) {
//				try {
//					out.close();  //关闭输出文件流
//				}catch(IOException el) {
//				}
//			}
//		}
//	}

	// 重写
//	public static boolean createFileOrDir(String path) {
//		return createFileOrDir(new File(path));
//	}

	// 重写创建目录
//	private static boolean createFileOrDir(File file) {
//		System.out.println(file.getPath());
//		if (file.isDirectory()) {
//			return file.mkdirs();
//		}
//		File parentFile = file.getParentFile();
//		if (!parentFile.exists()) {
//			System.out.println(parentFile.getPath());
//			boolean mkdirs = parentFile.mkdirs();
//			if (!mkdirs)
//				return false;
//		} else {
//			if (!parentFile.isDirectory()) {
//				boolean delete = parentFile.delete();
//				boolean mkdirs = parentFile.mkdirs();
//				if (!delete || !mkdirs)
//					return false;
//			}
//		}
//		try {
//			return file.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	// 主方法 , 创建文件 (自动创建上级目录与父目录)
	public static void createFile(String path) {
		createFile(new File(path));
	}

	// 自动创建目录
	private static void createFile(File file) {
		if (file.exists() && file.isFile()) {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		File parentFile = file.getParentFile();
		if (parentFile.exists()) {
			if (parentFile.isFile()) {
				parentFile.delete();
				parentFile.mkdirs();
			}
		} else {
			parentFile.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 创建根目录
	public static void mkdirs(File file) {
		if (file.exists() && file.isDirectory()) {
			return;
		}
		if (file.exists()) {
			file.delete();
			file.mkdirs();
		} else {
			file.mkdirs();
		}
	}

}
