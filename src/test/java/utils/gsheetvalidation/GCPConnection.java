package utils.gsheetvalidation;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GCPConnection {
	private static Logger logger = LogManager.getLogger(GCPConnection.class);

	public void getGCloudData(String bucketName, String fileNameInBucket, String filePathToStore) throws IOException {
		try {
			Storage storage = StorageOptions.getDefaultInstance().getService();
			Blob blob = storage.get(bucketName, fileNameInBucket);
			ReadChannel readChannel = blob.reader();
			File file = new File(filePathToStore);

			if (file.exists()) {
				if (file.delete()) {
					logger.info("File Successfully Deleted");
				} else {
					logger.info("Failed to delete the file");
				}
			}
			FileOutputStream fileOuputStream = new FileOutputStream(file);
			fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadObjectToGCloud(String bucketName, String objectName, String filePath) {
		{
			try {
				Storage storage = StorageOptions.newBuilder()
						.setProjectId(StorageOptions.getDefaultInstance().getProjectId()).build().getService();
				BlobId blobId = BlobId.of(bucketName, objectName);
				BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
				storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath))).toBuilder().build();
				logger.info("File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void uploadCSVFiletoGCPBucket(String projectId, String bucketName, String objectName, String filePath)
			throws IOException {
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
		logger.info("File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
	}

	public static void deleteObject(String bucketName, String objectName) {
		try {
			Storage storage = StorageOptions.newBuilder()
					.setProjectId(StorageOptions.getDefaultInstance().getProjectId()).build().getService();
			Blob blob = storage.get(bucketName, objectName);
			if (blob == null) {
				System.out.println("The object " + objectName + " wasn't found in " + bucketName);
				return;
			}
			Storage.BlobSourceOption precondition = Storage.BlobSourceOption.generationMatch(blob.getGeneration());
			storage.delete(bucketName, objectName, precondition);
			logger.info("Object " + objectName + " was deleted from " + bucketName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
