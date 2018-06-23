package edu.neu.cs5200.models;

public class Enum {

	public enum RoleEnum {
		OWNER(1), ADMIN(2), WRITER(3), EDITOR(4), REVIEWER(5);

		private int value;

		private RoleEnum(int value) {
			this.value = value;
		}
		
		public int value() {
			return value;
		}

	}
	
	public enum TypeEnum {
		
		HEADING(1), HTML(2), IMAGE(3), YOUTUBE(4);

		private int value;

		private TypeEnum(int value) {
			this.value = value;
		}
		
		public int value() {
			return value;
		}

	}
	
	public static void main(String[] args) {
		System.out.println(TypeEnum.HEADING.name());
	}
	
	public enum PriviledgeEnum {
		CREATE(1), READ(2), UPDATE(3), DELETE(4);

		private int value;

		private PriviledgeEnum(int value) {
			this.value = value;
		}
		
		public int value() {
			return value;
		}

	}
}
