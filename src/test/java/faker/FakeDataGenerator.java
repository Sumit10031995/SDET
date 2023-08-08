package faker;

import com.github.javafaker.Faker;

public class FakeDataGenerator {
	public static void main(String[] args) {

		Faker faker = new Faker();

		// Generate an integer
		int fakeInt = faker.random().nextInt(1, 100);

		// Generate a double
		double fakeDouble = faker.number().randomDouble(2, 1, 1000);

		// Generate a random string
		String fakeString = faker.lorem().sentence(3);

		// Generate a random user
		String fakeFullName = faker.name().fullName();
		String fakeEmail = faker.internet().emailAddress();
		String fakeUsername = faker.name().username();

		// Generate a random email address
		String fakeEmailAddress = faker.internet().emailAddress();

		// Generate a random password
		String fakePassword = faker.internet().password(4, 4, true);

		// Generate a random brand
		String fakeBrand = faker.company().name();

		System.out.println("Fake Integer: " + fakeInt);
		System.out.println("Fake Double: " + fakeDouble);
		System.out.println("Fake String: " + fakeString);
		System.out.println("Fake Full Name: " + fakeFullName);
		System.out.println("Fake Email: " + fakeEmail);
		System.out.println("Fake Username: " + fakeUsername);
		System.out.println("Fake Email Address: " + fakeEmailAddress);
		System.out.println("Fake Password: " + fakePassword);
		System.out.println("Fake Brand: " + fakeBrand);

	}
}
