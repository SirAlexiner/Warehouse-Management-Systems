package warehouse.gui.utilities;

import lombok.experimental.UtilityClass;

/**
 * The toTitleCase method is used to convert a string to TitleCase.
 * The code was copied from:
 * <a href="https://stackoverflow.com/questions/1086123/is-there-a-method-for-string-conversion-to-title-case">
 * "Is there a method for String conversion to Title Case?"
 * </a>
 */
@UtilityClass
public class StringTitle {

  /**
   * We iterate through the characters of the string and capitalize the first letter of each word.
   *
   * @param input The string to be converted to title case.
   * @return A string that is capitalized.
   */
  public String toTitleCase(String input) {
    StringBuilder titleCase = new StringBuilder(input.length());
    boolean nextTitleCase = true;
    for (char c : input.toCharArray()) {
      if (Character.isSpaceChar(c)) {
        nextTitleCase = true;
      } else if (nextTitleCase) {
        c = Character.toTitleCase(c);
        nextTitleCase = false;
      }
      titleCase.append(c);
    }
    return titleCase.toString();
  }
}
