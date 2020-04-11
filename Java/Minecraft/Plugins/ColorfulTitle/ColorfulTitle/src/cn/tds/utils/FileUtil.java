package cn.tds.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import cn.tds.colorfultitle.Main;

public class FileUtil extends MessageUtil {

	public static File FolderPath = null;
	public static YamlConfiguration langYaml = new YamlConfiguration();
	public static YamlConfiguration bcYaml = new YamlConfiguration();
	public static YamlConfiguration ignoreYaml = new YamlConfiguration();
	
	
	public static void DefaultFiles() throws MalformedURLException, IOException, InvalidConfigurationException {

		FolderPath = Main.getPlugin().getDataFolder();

		//导出文件
		if(!hasFile(FolderPath + "/lang/Chinese.yml")) {
			Main.getPlugin().saveResource("lang/Chinese.yml",false);
			sendExternalMessage(0,"§b语言文件导出完毕 ...",true);
		}
		if(!hasFile(FolderPath + "/broadcast.yml")) {
			Main.getPlugin().saveResource("broadcast.yml",true);
			sendExternalMessage(0,"§b配置文件导出完毕 ...",true);
		}
		if(!hasFile(FolderPath + "/Ignores.yml")) {
			Main.getPlugin().saveResource("Ignores.yml",true);
			sendExternalMessage(0,"§b脏话文件导出完毕 ...",true);
		}
		langYaml = YamlConfiguration.loadConfiguration(new File(FolderPath + "/lang/Chinese.yml"));
		bcYaml = YamlConfiguration.loadConfiguration(new File(FolderPath + "/broadcast.yml"));
		ignoreYaml = YamlConfiguration.loadConfiguration(new File(FolderPath + "/Ignores.yml"));
		
		Main.Ignore = ignoreYaml.getBoolean("Enabled");
		
		sendExternalMessage(0,"§a语言文件类型: " + langYaml.getString("Settings.name") + " 作者: " +langYaml.getString("Settings.Author")  ,true);
		sendExternalMessage(0,"§a文件加载完成 ...",true);
		
	}


	
	public static boolean hasFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		} else {
			return true;
		}
	}
	

	// 重写
	public static boolean createFileOrDir(String path) {
		return createFileOrDir(new File(path));
	}

	// 重写创建目录
	private static boolean createFileOrDir(File file) {
		System.out.println(file.getPath());
		if (file.isDirectory()) {
			return file.mkdirs();
		}
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			System.out.println(parentFile.getPath());
			boolean mkdirs = parentFile.mkdirs();
			if (!mkdirs)
				return false;
		} else {
			if (!parentFile.isDirectory()) {
				boolean delete = parentFile.delete();
				boolean mkdirs = parentFile.mkdirs();
				if (!delete || !mkdirs)
					return false;
			}
		}
		try {
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

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
