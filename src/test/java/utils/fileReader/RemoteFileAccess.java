package utils.fileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.jcraft.jsch.*;

public class RemoteFileAccess {
	private static final String host = "your-ubuntu-hostname-or-ip";
	private static final String username = "your-username";
	private static final String password = "your-password";
	private static final String remoteFilePath =PropertiesReader.getPropertyDetails("remote.file.reader");
     
	public static void readRemoteFile(String remoteFilePath) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no"); // Disable host key checking

            session.connect();

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("cat " + remoteFilePath); // Read the file content

            channel.setInputStream(null);

            InputStream in = channel.getInputStream();
            channel.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

            channel.disconnect();
            session.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }
}
