import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;

public class ClientsResponse {
	
	private final String id;
	private final String text;
	private final String type;
	private final String user;
	private final Integer upvotes;
	
	public ClientsResponse(
			@JsonProperty("id") String id,
			@JsonProperty("text") String text,
			@JsonProperty("type") String type,
			@JsonProperty("user") String user,
			@JsonProperty("upvotes") int upvotes
	) throws UnsupportedEncodingException {
		this.id = id;
		this.text = text = new String (text.getBytes("UTF-8"),"cp1251");
		this.type = type;
		this.user = user;
		this.upvotes = upvotes;
	}
	
	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
	
	public String getUser() {
		return user;
	}
	
	public Integer getUpvotes() {
		return upvotes;
	}
	
	@Override
	public String toString() {
		return "ClientsResponse{" +
				"id=" + id +
				", text='" + text + '\'' +
				", type='" + type + '\'' +
				", user='" + user + '\'' +
				", upvotes='" + upvotes + '\'' +
				'}';
	}
}