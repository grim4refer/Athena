package org.yanille;

public class StringUtils {


    public static String[] split(RSFontSystem font, String string, int maxWidth) {
        int width = font.getTextWidth(string);
        if (width <= maxWidth)
            return new String[] { string };
        else {
            int columns = (int) Math.ceil(((double) width) / ((double) maxWidth));
            String[] contents = new String[columns];
            int col = 0;
            for (String word : string.split(" ")) {
                int word_width = font.getTextWidth(word);
                int current_width = font.getTextWidth(contents[col]);
                if ((word_width + current_width) > maxWidth) {
                    col++;
                }
                if (col >= contents.length)
                    break;
                if (contents[col] == null)
                    contents[col] = word;
                else
                    contents[col] += " " + word;
            }
            return contents;
        }
    }

    public static String[] split(RSFontSystem font, String string, int maxWidth, String delimiter) {
        int width = font.getTextWidth(string);
        if (width <= maxWidth)
            return new String[] { string };
        else {
            try {
                String[] newLineDelimiter = string.split(delimiter);
                int columns = (int) Math.ceil(((double) width) / ((double) maxWidth));
                String[] contents = new String[columns + (newLineDelimiter != null ? newLineDelimiter.length : 0)];
                int col = 0;
                if (string.contains(" ")) {
                    int populated = 0;
                    for (String word : string.split(" ")) {
                        int word_width = font.getTextWidth(word);
                        if (word_width < maxWidth
                                && word.contains(delimiter)) {
                            word_width = maxWidth;
                            word = word.replace(delimiter, "");
                        }
                        int current_width = font.getTextWidth(contents[col]);
                        if ((word_width + current_width) > maxWidth) {
                            col++;
                        }
                        if (col >= contents.length)
                            break;
                        if (contents[col] == null) {
                            contents[col] = word;
                            populated++;
                        } else
                            contents[col] += " " + word;
                    }
                    String[] newContent = new String[populated];
                    System.arraycopy(contents, 0, newContent, 0, populated);
                    return newContent;
                } else {
                    for (String word : string.split("")) {
                        int word_width = font.getTextWidth(word);
                        int current_width = font.getTextWidth(contents[col]);
                        if ((word_width + current_width) > maxWidth) {
                            col++;
                        }
                        if (col >= contents.length)
                            break;
                        if (contents[col] == null)
                            contents[col] = word;
                        else
                            contents[col] += "" + word;
                    }
                }
                return contents;
            } catch (Exception e) {
                e.printStackTrace();
                return new String[] { };
            }
        }
    }
}
