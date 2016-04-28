package com.yzm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
	public Test() {

	}

	/*
	 * public static void main(String[] arg) {
	 * 
	 * int n = 0x12345678;
	 * 
	 * System.out.println("" + (n & 0xff));
	 * 
	 * Test t = new Test();
	 * 
	 * // for(int i=0;i<100;i++){ t.twoJpg(i);}
	 * 
	 * for (int i = 0; i < 100; i++) { t.writeFg(i); }
	 * 
	 * System.out.println(t.fg(t.twoJpg(0)));
	 * 
	 * 
	 * //int wh[][] = null;
	 * 
	 * BufferedImage bufferedImage = null; try { bufferedImage =
	 * ImageIO.read(new File("F:/App閿熸枻鎷�twojpg/" +1 + ".bmp")); int
	 * a=bufferedImage.getWidth(); System.out.print(a); } catch (IOException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * }
	 */

	/*
	 * public void writeFg(int i) { BufferedImage bufferedImage = null; try {
	 * bufferedImage = ImageIO.read(new File("F:/App閿熸枻鎷�twojpg/" + i +
	 * ".bmp")); } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } fg(bufferedImage, i); bufferedImage.flush(); }
	 */

	public BufferedImage twoJpg(BufferedImage buf) {

		/*
		 * BufferedImage bufferedImage = null; try { bufferedImage =
		 * ImageIO.read(new File("F:/App閿熸枻鎷�jpg/" + i + ".jpg")); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		int h = buf.getHeight();

		int w = buf.getWidth();

		// 閿熸彮搴︿紮鎷�

		int[][] gray = new int[w][h];

		for (int x = 0; x < w; x++) {

			for (int y = 0; y < h; y++) {

				int argb = buf.getRGB(x, y);

				int r = (argb >> 16) & 0xFF;
				int g = (argb >> 8) & 0xFF;

				int b = (argb >> 0) & 0xFF;

				int grayPixel = (int) ((b * 29 + g * 150 + r * 77 + 128) >> 8);

				gray[x][y] = grayPixel;

			}

		}

		// 閿熸枻鎷峰�閿熸枻鎷�

		int threshold = ostu(gray, w, h);
		BufferedImage binaryBufferedImage = new BufferedImage(w, h,
				BufferedImage.TYPE_3BYTE_BGR);

		for (int x = 0; x < w; x++) {

			for (int y = 0; y < h; y++) {

				if (gray[x][y] > threshold) {

					gray[x][y] = 0xFFFFFF;

				} else {

					gray[x][y] = 0;

				}

				binaryBufferedImage.setRGB(x, y, gray[x][y]);
			}

		}
		/*
		 * try { ImageIO.write(binaryBufferedImage, "bmp", new File(
		 * "F:/App閿熸枻鎷�twojpg/" + i + ".bmp")); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		return binaryBufferedImage;

	}

	/*
	 * public static boolean isBlack(int colorInt) {
	 * 
	 * Color color = new Color(colorInt);
	 * 
	 * if (color.getRed() + color.getGreen() + color.getBlue() <= 300) { return
	 * true;
	 * 
	 * }
	 * 
	 * return false;
	 * 
	 * }
	 * 
	 * public static boolean isWhite(int colorInt) {
	 * 
	 * Color color = new Color(colorInt);
	 * 
	 * if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
	 * 
	 * return true;
	 * 
	 * }
	 * 
	 * return false;
	 * 
	 * }
	 * 
	 * public static int isBlackOrWhite(int colorInt) {
	 * 
	 * if (getColorBright(colorInt) < 30 || getColorBright(colorInt) > 730) {
	 * 
	 * return 1;
	 * 
	 * }
	 * 
	 * return 0;
	 * 
	 * }
	 */

	public static int getColorBright(int colorInt) {

		Color color = new Color(colorInt);

		return color.getRed() + color.getGreen() + color.getBlue();

	}

	public static int ostu(int[][] gray, int w, int h) {

		int[] histData = new int[w * h];
		// Calculate histogram

		for (int x = 0; x < w; x++) {

			for (int y = 0; y < h; y++) {

				int red = 0xFF & gray[x][y];

				histData[red]++;

			}

		}

		// Total number of pixels

		int total = w * h;

		float sum = 0;

		for (int t = 0; t < 256; t++)

			sum += t * histData[t];

		float sumB = 0;

		int wB = 0;

		int wF = 0;

		float varMax = 0;

		int threshold = 0;

		for (int t = 0; t < 256; t++) {

			wB += histData[t]; // Weight Background

			if (wB == 0)

				continue;

			wF = total - wB; // Weight Foreground

			if (wF == 0)

				break;

			sumB += (float) (t * histData[t]);

			float mB = sumB / wB; // Mean Background
			float mF = (sum - sumB) / wF; // Mean Foreground

			// Calculate Between Class Variance

			float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

			// Check if new maximum found

			if (varBetween > varMax) {

				varMax = varBetween;

				threshold = t;

			}

		}

		return threshold;
	}

	public String fg(BufferedImage buff) {
		BufferedImage binaryBufferedImage = null;
		int w = buff.getWidth();
		int h = buff.getHeight();
		String yzm = "";
		// System.out.println(w+","+h);
		// 閿熷彨闈╂嫹閿熺粸鍢変紮鎷烽敓锟�
		int ix = 0, iy = 0;
		for (int i = 0; i < 4; i++) {
			int cal = 0;
			int top = 0;
			int dop = 0;
			int left = 0;
			int right = 0;

			for (int x = ix; x < w; x++) {
				int js = 0;
				for (int y = iy; y < h; y++) {
					int argb = buff.getRGB(x, y);

					// Sytem.out.println(argb);
					int r = (argb >> 16) & 0xFF;
					// System.out.print(r+",");
					if (r == 0x00) {
						//
						int c = 0, cc = 0, t, tt;
						// System.out.print(x + "," + y + ",");
						if (x == 59) {

							right = 59;
						}

						else {
							c = (buff.getRGB(x + 1, y) >> 16) & 0xFF;
							if (c == 0xFF) {
							} else {
								if (left == 0 || left > x) {

									left = x;

								}

								// js++;
							}
						}
						if (y == 19) {
						} else {

							cc = (buff.getRGB(x, y + 1) >> 16) & 0xFF;

							if (cc == 0xFF) {

							} else {
								if (left > 0) {

									if (top == 0 || top > y) {
										top = y;
									}
								}
							}
						}
						if (x != 0) {
							t = (buff.getRGB(x - 1, y) >> 16) & 0xFF;

							if (t == 0xFF) {

							} else {

								if (right == 0 || right < x) {
									right = x;
								}

							}

						}

						else {
							t = 0;
						}
						if (y != 0) {
							tt = (buff.getRGB(x, y - 1) >> 16) & 0xFF;
							if (tt == 0xFF) {

							} else {

								if (dop == 0 || dop < y) {
									dop = y;
								}

							}
						} else {
							tt = 0;
						}

						if (c == 0xFF && cc == 0xFF && t == 0xFF && tt == 0xFF) {

						} else {
							if (left > 0) {
								js = 1;
							}
						}
					} else {
						if (left == 0) {
							js = 1;
							// System.out.print("閿熸枻鎷�+x+",");
						}

					}

				}
				if (js == 0) {
					break;
				}

			}

			// System.out.println(w+","+h+","+left+","+top+","+right+","+dop);
			/*
			 * binaryBufferedImage = new BufferedImage(right - left + 1, dop -
			 * top + 1, BufferedImage.TYPE_3BYTE_BGR);
			 */

			int wh[][] = GetData.getWh();
			int mm = 0;
			for (int z = 48; z < 122; z++) {
				int nn = 0;
				// System.out.println(wh[0][0]);
				if (right - left + 1 == wh[z][0] && dop - top + 1 == wh[z][1]) {
					// System.out.print(z+",");
					try {
						String classpath = GetData.class.getResource("")
								.getPath();

						String fpath1[] = classpath
								.split("WEB-INF/classes/", 2);
						// String fpath[]=fpath1[0].split("/", 2);
						binaryBufferedImage = ImageIO.read(new File(fpath1[0]
								+ "TYUTjavamodel/" + z + ".bmp"));
						// System.out.println("z是"+fpath[1]);
						if (binaryBufferedImage != null) {
							// System.out.println("get");
						}
						for (int ex = left, mx = 0; ex <= right; ex++, mx++) {
							for (int ey = top, my = 0; ey <= dop; ey++, my++) {

								if (buff.getRGB(ex, ey) == binaryBufferedImage
										.getRGB(mx, my)) {
									// System.out.print(binaryBufferedImage.getRGB(mx,
									// my));

									nn++;
								}
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

				}

				if (nn > mm) {

					cal = z;
					mm = nn;
					// System.out.println("mm閿熸枻鎷�"+mm);
				}
			}
			// System.out.print(cal+",");
			System.out.print((char) cal + ",");
			yzm += "" + (char) cal;
			/*
			 * for (int ex = left, mx = 0; ex <= right; ex++, mx++) { for (int
			 * ey = top, my = 0; ey <= dop; ey++, my++) {
			 * 
			 * binaryBufferedImage.setRGB(mx, my, buff.getRGB(ex, ey)); } }
			 */
			/*
			 * try { ImageIO.write(binaryBufferedImage, "bmp", new File(
			 * "F:/App閿熸枻鎷�twobmp/" + ui + i + ".bmp")); } catch (IOException e)
			 * { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
			ix = right + 1;
			// value[i][0]=left

		}
		System.out.println("");
		return yzm;
	}

	public void sb() {

	}
}
