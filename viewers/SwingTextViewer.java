import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.rentals.NoSuchPageException;
import services.viewer.Viewer;

public class SwingTextViewer extends Viewer {

	private String text;

	public SwingTextViewer() {

		super("swing");
	}

	@Override
	protected void loadMimeTypes() {

		List<String> mimeTypes = new ArrayList<String>(1);
		mimeTypes.add("text/plain");
		setMimeTypes(mimeTypes);
	}

	@Override
	public void setDocument(File file) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String s;

		while ((s = in.readLine()) != null)
			sb.append(s + " ");

		text = sb.toString();
		in.close();
	}

	@Override
	public Object getPage(int pageNum, int width, int height)
			throws NoSuchPageException {

		Image image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);

		int hRatio = height / 18;
		int wRatio = width / 6;

		int charNumber = hRatio * wRatio;
		int startpoint = 0;
		int count = 1;

		try{
		
		while (count != pageNum) {
			startpoint += charNumber;
			count++;
		}

		if (startpoint > text.length())
			throw new NoSuchPageException();

		int lines = 1;

		while (lines <= hRatio && startpoint + wRatio < text.length()) {
			if (startpoint < text.length())
				graphics.drawString(
						text.substring(startpoint, startpoint + wRatio), 25,
						25 * lines);

			startpoint = startpoint + wRatio;
			lines++;
		}

		graphics.drawString(
				text.substring(startpoint, text.length()), 25,
				25 * lines);
		
		}catch(java.lang.StringIndexOutOfBoundsException e){
			
		}

		return image;
	}

	@Override
	public boolean canSlideshow() {

		return true;
	}
}
