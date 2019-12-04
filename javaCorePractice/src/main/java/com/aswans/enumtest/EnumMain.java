package com.aswans.enumtest;

public class EnumMain {
	public static void main(String[] args) {
		showSex(EnumSex.MAN);
	}

	public static void showSex(EnumSex EnumSex) {
		switch (EnumSex) {
		case MAN:
			System.out.println("this is a boy");
			break;
		case WOMAN:
			System.out.println("this is a girl");
			break;
		default:
            System.out.println("�Ҳ�֪������Ա�");
            break;
		}
	}
}
