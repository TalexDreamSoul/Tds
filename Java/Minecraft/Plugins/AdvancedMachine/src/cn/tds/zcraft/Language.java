package cn.tds.zcraft;

public enum Language {
	Chinese("简体中文"), English("English");
	
	private String lang;
	
	private Language(String thisLang) {
		this.lang = thisLang;
	}
	public String getLanguage() {
		return lang;
	}
}
