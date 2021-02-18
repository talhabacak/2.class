package Report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MessageCollector {//it will include some messages of changes in the system that done by user 
												//like failed and succeed operations
	
	private String systemMessage;
	private String lastMessage;
	
	public MessageCollector() {
		this.setSystemMessage("");
		this.setLastMessage("");
	}
	
	public void toFile() throws IOException {
	        File file = new File("report.txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter fileWriter = new FileWriter(file, false);
	        BufferedWriter bWriter = new BufferedWriter(fileWriter);
	        bWriter.write(systemMessage);
	        bWriter.close();
	}
	
	public void addMessage(String newMessage) {
		systemMessage = systemMessage + "\n" + newMessage;
		lastMessage = newMessage;
	}
	
	public String getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

}
