package de.michaelevelt.toolcollection.javafx.media;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtils {

	private static final String FILE_FORMAT = "png";

	public Image readImage(byte[] bytes) throws IOException {

		if (bytes == null) {
			return null;
		}
		InputStream in = new BufferedInputStream(new ByteArrayInputStream(bytes));
		BufferedImage bufferedImage = ImageIO.read(in);
		return SwingFXUtils.toFXImage(bufferedImage, null);
	}

	public byte[] readFileAsByteArray(String fileName) throws IOException {

		if (StringUtils.isBlank(fileName)) {
			return null;
		}

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		if (inputStream == null) {
			return null;
		}

		inputStream = new BufferedInputStream(inputStream);

		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes);

		return bytes;
	}

	public byte[] byteArrayFromImageView(ImageView imageView) throws IOException {
		if (imageView == null || imageView.getImage() == null) {
			return null;
		}
		BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ImageIO.write(bImage, FILE_FORMAT, outputStream);
		outputStream.close(); //especially if you are using a different output stream.

		return outputStream.toByteArray();
	}
}
