package flights;

public class Client {
	private Integer code;

	public Client(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Client [code=" + code + "]";
	}
}
