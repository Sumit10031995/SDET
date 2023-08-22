package com.api.responseDTO;

public class PostAPIResponseDTO {
	private String status;
	private Data data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data {
		private String name;
		private String salary;
		private String age;
		private int id;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSalary() {
			return salary;
		}

		public void setSalary(String salary) {
			this.salary = salary;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Data :{name=" + name + ", salary=" + salary + ", age=" + age + ", id=" + id + "}";
		}
		
	}

	@Override
	public String toString() {
		return "PostAPIResponseDTO :{status=" + status + ", data=" + data + "}";
	}
	
	
}
