package com.aswans.reflect.demo2;
/***
 * @desc 
 * ********* getConstructor()和getDeclaredConstructor()区别：*********

			getDeclaredConstructor(Class<?>... parameterTypes) 
			这个方法会返回指定参数类型的所有构造器，包括public的和非public的，当然也包括private的。
			getDeclaredConstructors()的返回结果就没有参数类型的过滤了。
			
			再来看getConstructor(Class<?>... parameterTypes)
			这个方法返回的是上面那个方法返回结果的子集，只返回制定参数类型访问权限是public的构造器。
			getConstructors()的返回结果同样也没有参数类型的过滤。 
 * @zsj zsj add 2018年11月28日 下午1:54:07
 */
public class Solution {
	private String str;
	private int num;

	public Solution() {

	}

	public Solution(String str, int num) {
		this.str = str;
		this.num = num;
	}

	public Solution(String str) {
		this.str = str;
	}

	public static void main(String[] args) throws Exception {

		Class[] classes = new Class[] { String.class, int.class };
		Solution solution = Solution.class.getConstructor(classes).newInstance("hello1", 10);
		System.out.println(solution.str); // hello1

		Solution solution2 = solution.getClass().getDeclaredConstructor(String.class).newInstance("hello2");
		System.out.println(solution2.str); // hello2

		Solution solution3 = (Solution) Class.forName("com.aswans.reflect.demo2.Solution").getConstructor().newInstance(); // 无参也可用getConstructor()
		System.out.println(solution3 instanceof Solution); // true
	}

}
