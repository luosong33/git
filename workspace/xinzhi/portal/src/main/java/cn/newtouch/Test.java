package cn.newtouch;

import lombok.Data;

@Data
public class Test {
	private String aaa;
	 
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("12345");
		System.out.println(s.reverse());
		
	}
}
