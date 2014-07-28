package com.yesun.sample.datatype;

public class EnumSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	//常量型枚举
	public enum Color {
		Red, Blue, Gray
	}
	
	//扩充方法
	public enum FontColor {
		
		Red("红色",1), Blue("蓝色",2), Gray("灰色",3);
		
		String name;
		int value;
		
		private FontColor(String name, int value){
			this.name = name;
			this.value = value;
		}
		
		public static String getName(int value) {
			for(FontColor color : FontColor.values()) {
				if(color.value == value) {
					return color.name;
				}
			}
			return null;
		}
	}
	
	public interface Behaviour {
        void print();
        String getInfo();
    }

	
	//枚举也可以实现接口
	public enum BgColor implements Behaviour {
		
		Red,Blue,Gray;

		@Override
		public void print() {
		}

		@Override
		public String getInfo() {
			return null;
		}
		
	}

	
}
