package com.athena;

import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

import javax.imageio.ImageIO;

import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Jframe extends Client implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static TrayIcon trayIcon;

	Image icon = Toolkit.getDefaultToolkit().getImage(signlink.findcachedir()+"/Interfaces/icon.png");

	public Jframe(String[] args, int width, int height, boolean resizable) {
		super();
		toolkit = Toolkit.getDefaultToolkit();
		setTray();
		try {
			signlink.startpriv(InetAddress.getByName(Configuration.HOST));
			initUI(width, height, resizable);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void setTray() {
		if (SystemTray.isSupported()) {
			trayIcon = new TrayIcon(icon, Configuration.CLIENT_NAME);
			trayIcon.setImageAutoSize(true);
			try {
				SystemTray tray = SystemTray.getSystemTray();
				tray.add(trayIcon);
				trayIcon.displayMessage(Configuration.CLIENT_NAME, Configuration.CLIENT_NAME + " has been launched!",
						TrayIcon.MessageType.INFO);

				final MenuItem minimizeItem = new MenuItem("Hide " + Configuration.CLIENT_NAME);
				new MenuItem("-");
				MenuItem exitItem = new MenuItem("Quit");
				ActionListener minimizeListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (frame.isVisible()) {
							frame.setVisible(false);
							minimizeItem.setLabel("Show 1# " + Configuration.CLIENT_NAME + ".");
						} else {
							frame.setVisible(true);
							minimizeItem.setLabel("Hide 1# " + Configuration.CLIENT_NAME + ".");
						}
					}
				};
				minimizeItem.addActionListener(minimizeListener);
				ActionListener exitListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				};
				exitItem.addActionListener(exitListener);
			} catch (AWTException e) {
				System.err.println(e);
			}
		}
	}
	public void initUI(int width, int height, boolean resizable) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);

			frame = new JFrame(Configuration.CLIENT_NAME);
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setIconImage(icon);

			frame.setAlwaysOnTop(ALWAYS_ON_TOP);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent we) {
					String options[] = {"Yes", "No"};
					int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Volantis",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options , options[1]);
					if(userPrompt == JOptionPane.YES_OPTION) {
						// openURL("http://LiquidX.net");
						System.exit(-1);
						System.exit(0);
					} else {

					}
				}
			});
			setFocusTraversalKeysEnabled(false);
			JPanel gamePanel = new JPanel();
			this.getInsets();
			super.setPreferredSize(new Dimension(width - 10, height - 10));
			frame.setLayout(new BorderLayout());
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setBackground(Color.white);
			initializeMenuBar();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			frame.setResizable(resizable);
			init();
			graphics = getGameComponent().getGraphics();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rebuildFrame(int width, int height, boolean resizable, boolean undecorated) {


		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		frame = new JFrame(Configuration.CLIENT_NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String options[] = {"Yes", "No"};
				int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Volantis",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options , options[1]);
				if(userPrompt == JOptionPane.YES_OPTION) {
					//	openURL("http://LiquidX.net");
					System.exit(-1);
					System.exit(0);
				} else {

				}
			}
		});
		frame.setUndecorated(undecorated);
		setFocusTraversalKeysEnabled(false);
		JPanel gamePanel = new JPanel();
		this.getInsets();
		super.setPreferredSize(new Dimension(width - 10, height - 10));
		frame.setLayout(new BorderLayout());
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(this, BorderLayout.CENTER);		gamePanel.setBackground(Color.BLACK);
		if(!undecorated) {
			frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
		}
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(resizable);
		//init();
		graphics = getGameComponent().getGraphics();
		frame.setLocation((screenWidth - width) / 2, ((screenHeight - height) / 2) - screenHeight == getMaxHeight() ? 0 : undecorated ? 0 : 70);
		frame.setVisible(true);


		frame.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				Dimension dimension = new Dimension(frame.getWidth(), frame.getHeight());

				gamePanel.setMinimumSize(dimension);
				gamePanel.setPreferredSize(dimension);
				gamePanel.setSize(dimension);

				Jframe.super.setPreferredSize(new Dimension(frame.getWidth() - 5, frame.getHeight() - 10));
				Jframe.super.revalidate();
				Jframe.super.repaint();

				Jframe.super.graphics = getGameComponent().getGraphics();

			}

		});

	}

	public void setClientIcon() {
		Image img = resourceLoader.getImage("icon");
		if (img == null)
			return;
		frame.setIconImage(img);

	}

	/**
	 * Our jpanel for the menu bar
	 */
	private static JPanel menuPanel;

	/**
	 * Initializes the menu bar
	 */
	public void initializeMenuBar() {
	}

	/**
	 * Creates a button on the menu panel
	 *
	 * @param title
	 *            The Title of the button
	 * @param image
	 *            The image to display
	 * @param tooltip
	 *            The tooltip when hovering over the button
	 * @return The created button
	 */
	private JButton createButton(String title, String image, String tooltip) {
		JButton button = new JButton(title);
		try {
			if (image != null)
				button.setIcon(new ImageIcon(ResourceLoader.loadImage(image)));
		} catch (Exception e) {

		}
		button.addActionListener(this);
		return button;
	}

	private JButton createButton(String title, String tooltip) {
		JButton button = new JButton(title);
		button.addActionListener(this);
		return button;
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + Configuration.HOST + "/");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
		return "";
	}

	public static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				switch (cmd) {
				case "Home":
					openURL("http://www.yanille.net");
					break;
				case "Forum":
					openURL("http://www.yanille.net/forums");
					break;
				case "Knowledge Base":
					openURL("http://www.yanille.net/forums");
					break;
				case "Store":
					openURL("http://www.yanille.net/store");
					break;
				case "Vote":
					openURL("http://www.yanille.net/vote");
					break;
				case "Hiscores":
					openURL("https://yanille.net/hiscores/index.php?skill=Overall");
					break;
				case "Join Discord":
					// String nickname = (Client.instance.getMyUsername() !=
					// null && Client.loggedIn &&
					// Client.instance.getMyUsername().length() > 2) ?
					// TextClass.fixName(Client.instance.getMyUsername().replaceAll("
					// ", "%20")) : "ForumGuest";
					openURL("https://discord.gg/4e56pEk");
					break;
				case "Screenshot":
					makeScreenshot();
					break;
				case "History":
					Desktop.getDesktop().open(new File(signlink.findcachedir(), "screenshots"));
					break;
				}

			}
		} catch (Exception e) {
		}
	}

	public final void makeScreenshot() {
		try {
			Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
			Point point = window.getLocationOnScreen();
			Robot robot = new Robot(window.getGraphicsConfiguration().getDevice());
			Rectangle rectangle = new Rectangle((int) point.getX() + 3, (int) point.getY() + 57, window.getWidth() - 6,
					window.getHeight() - 60);
			BufferedImage img = robot.createScreenCapture(rectangle);
			Path path = Paths.get(signlink.findcachedir(), "screenshots");
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			DateFormat format = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss a");
			File file = new File(path.toFile(), format.format(new Date()) + ".png");
			ImageIO.write(img, "png", file);

		} catch (IOException | AWTException ioe) {
			System.out.println(ioe.toString());
		}
	}

	/**
	 * Opens a URL in your default web browser
	 *
	 * @param url
	 *            The url of the website to open
	 */
	static void openURL(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	int screenWidth = (int) screenSize.getWidth();
	int screenHeight = (int) screenSize.getHeight();

}