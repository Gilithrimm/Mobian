package old.safenar.unused;

import java.lang.reflect.Field;

public class MainAnno {

	public static void main(String[] args) {
		MainAnno mainAnno = new MainAnno();
		mainAnno.run();
	}

	public void run() {
		User user = new User();

		Field[] fields = user.getClass().getDeclaredFields();

		for (Field field : fields) {
			if(field.isAnnotationPresent(DatabaseField.class)) {
				DatabaseField databaseField = field.getAnnotation(DatabaseField.class);
				System.out.println("Field name: "+field.getName() + " | Column name: " + databaseField.columnName());
			}
		}
	}

}
