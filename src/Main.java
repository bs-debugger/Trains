import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        String s = "/a/../../b/../c//.//";
        //"20EE:Fb8:85a3:0:0:8A2E:0370:7334"
        //"2001:0db8:85a3:0:0:8A2E:0370:7334"
//        Integer i = Integer.parseInt("FGb8",16);
//        System.out.println(i);
//        System.out.println(m.validIPAddress(s));
//        System.out.println(m.longestPalindrome("a"));
//        Stack<String> stack = new Stack<>();
//        s = s.replaceAll("[/]+", "/");
//        s = s.substring(1);
//        String[] array = s.split("/");
//        for (String str : array) {
//            if (str.equals(".")) {
//                continue;
//            } else if (str.equals("..")) {
//                if (stack.size() > 0) {
//                    stack.pop();
//                }
//            } else {
//                stack.push(str);
//            }
//        }
//        StringBuffer sb = new StringBuffer("/");
//        for (String ss : stack) {
//            sb.append(ss);
//            sb.append("/");
//        }
//        System.out.println(sb.toString().substring(0, sb.length() - 1));
        System.out.println(m.myAtoi("    0000000000000   "));
    }

    public String validIPAddress(String IP) {
        IP = IP.replaceAll("\\.", "-");
        IP = IP.replaceAll(":", "-");
        if (IP.endsWith("-")) {
            return "Neither";
        }
        String[] array = IP.split("-");
        if (array.length == 4) {
            return validIPv4(array);
        } else if (array.length == 8) {
            return validIPv6(array);
        } else {
            return "Neither";
        }
    }

    public String validIPv4(String[] array) {
        for (String s : array) {
            if (s.length() == 0) {
                return "Neither";
            }
            if (s.startsWith("0")) {
                return "Neither";
            }
            try {
                Integer number = new Integer(s);
                if (number > 255) {
                    return "Neither";
                }
            } catch (Exception e) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    public String validIPv6(String[] array) {
        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            if (s.length() == 0) {
                return "Neither";
            }
            if (s.length() > 4) {
                return "Neither";
            }
            try {
                Integer number = Integer.parseInt(s);
            } catch (Exception e) {
                return "Neither";
            }


        }
        return "IPv6";
    }

    public String longestPalindrome(String s) {
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[][] array = new int[length][length];
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < length; i++) {
            char c1 = s.charAt(i);
            for (int j = 0; j < length; j++) {
                char c2 = reverse.charAt(j);
                if (c1 == c2) {
                    if (i == 0 || j == 0) {
                        array[i][j] = 1;
                    } else {
                        array[i][j] = array[i - 1][j - 1] + 1;
                        int temp = array[i][j];
                        if (temp > maxLength) {
                            int _start = length - j - 1;
                            int end = _start + (temp - 1);
                            if (end == i) {
                                maxLength = temp;
                                start = _start;
                            }
                        }
                    }
                }
            }
        }
        if (maxLength >= 0) {
            return s.substring(start, start + maxLength);
        }
        return "";
    }

    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        if (str.startsWith("-")) {
            return toInt(str.substring(1), false);
        } else if (str.startsWith("+")) {
            return toInt(str.substring(1), true);
        } else {
            return toInt(str, true);
        }
    }

    public int toInt(String str, boolean isPositive) {
        int number = 0;
        if (isPositive) {
            number = Integer.MAX_VALUE;
        } else {
            number = Integer.MIN_VALUE;
        }
        if (str.length() == 0) {
            return 0;
        }
        String first = "" + str.charAt(0);
        Pattern pattern = Pattern.compile("[0-9]");
        if (!pattern.matcher(first).matches()) {
            return 0;
        }
        int index = 0;
        int start = 0;
        if(first.equals("0")) {
            for (int i = 0; i < str.length() - 1; i++) {
                String temp = "" + str.charAt(i);
                String _temp = "" + str.charAt(i + 1);
                Pattern p = Pattern.compile("[1-9]");
                if(temp.equals("0") && _temp.equals("0")) {
                    start = i;
                }
                if(temp.equals("0") && !_temp.equals("0")) {
                    start = i+1;
                    break;
                }
            }
        }
        for (int i = start; i < str.length(); i++) {
            String temp = "" + str.charAt(i);
            if (!pattern.matcher(temp).matches()) {
                break;
            }
            index = i;
        }
        String numStr = str.substring(start, index + 1);
        if(numStr.length() == 0) {
            return 0;
        }
        if (!isPositive) {
            numStr = "-" + numStr;
        }
        if (isPositive && numStr.length() > 10) {
            return number;
        }
        if (!isPositive && numStr.length() > 11) {
            return number;
        }
        Long l = new Long(numStr);
        if (isPositive && l > (long) number) {
            return number;
        }
        if (!isPositive && l < (long) number) {
            return number;
        }

        return new Integer(numStr);
    }
}