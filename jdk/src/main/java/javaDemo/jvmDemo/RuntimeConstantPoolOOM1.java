package javaDemo.jvmDemo;
/**
 * jdk6 都是false
 * jdk7 true  false
 * @author xlj
 *这段代码在JDK 1.6中运行，会得到两个false，而在JDK 1.7中运行，会得到一个true和一
个false。产生差异的原因是：在JDK 1.6中，intern（）方法会把首次遇到的字符串实例复制
到永久代中，返回的也是永久代中这个字符串实例的引用，而由StringBuilder创建的字符串
实例在Java堆上，所以必然不是同一个引用，将返回false。而JDK 1.7（以及部分其他虚拟
机，例如JRockit）的intern（）实现不会再复制实例，只是在常量池中记录首次出现的实例
引用，因此intern（）返回的引用和由StringBuilder创建的那个字符串实例是同一个。对str2比
较返回false是因为“java”这个字符串在执行StringBuilder.toString（）之前已经出现过，字符串
常量池中已经有它的引用了，不符合“首次出现”的原则，而“计算机软件”这个字符串则是首
次出现的，因此返回true。
 */
public class RuntimeConstantPoolOOM1 {

	public static void main(String[] args) {
 		String str1 = new StringBuilder("中国").append("钓鱼岛").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}	
}


