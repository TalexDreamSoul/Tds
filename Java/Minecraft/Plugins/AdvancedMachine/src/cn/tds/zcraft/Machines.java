package cn.tds.zcraft;
public enum Machines {
	BlockBreaker("方块破坏者"),
	BlockMaker("方块制造者"),
	IndustryFurnace("工业炉"),
	AdvancedFurnace("高级炉"),
	SuperFurnace("强化炉") ,
	BlockCompress("压缩机") ,
	AdvancedCobbleStoneMaker("高级刷石机"),
	SuperCobbleStoneMaker("超级刷石机"),
	DivineCobbleStoneMaker("神级刷石机"),
	BaseCobbleStoneMaker("基础刷石机"),
	BreakCobbleStoneMaker("残破刷石机"),
	IronMaker("铁矿制造机");
	
	private String Machine;
	
	private Machines(String machine) {
		this.Machine = machine;
	}
	public String getMachine() {
		return Machine;
	}
	
}
