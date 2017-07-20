package com.zivy009.demo.springbootshirodwz.common.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

	public static boolean notEmpty(Object o) {
		return o != null && !o.toString().trim().equals("");
	}

	public static boolean empty(Object o) {
		return o == null || o.toString().trim().equals("");
	}

	public static   String filterEmoji(String str) {  
        
        if(str.trim().isEmpty()){  
            return str;  
        }  
        String pattern="[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";  
        String reStr="";  
        Pattern emoji=Pattern.compile(pattern);  
        Matcher  emojiMatcher=emoji.matcher(str);  
        str=emojiMatcher.replaceAll(reStr);  
        return str;  
    } 

	/***************************************************************************
	 * 使用split连接count个c字符,返回连接后的字符串
	 * 
	 * @param c
	 *            字符
	 * @param split
	 *            分隔符
	 * @param count
	 *            个数
	 * @return 返回字符串
	 * @author jiangfl
	 */
	public static String getJoinSplitChar(String c, String split, Integer count) {
		String[] chars = new String[count];
		for (int i = 0; i < count; i++) {
			chars[i] = c;
		}
		return join(chars, split);
	}

	/***
	 * 把字符串str根据split字符分割，然后返回对应的cls类型数据
	 * 
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @param cls
	 *            被分割后的值的类型
	 * @return
	 * @author jiangfl
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object[] split(String str, String split, Class cls) {
		if (str != null) {
			try {
				Object[] splits = str.split(split);
				// 如果是字符串
				if (cls != String.class) {
					Object result[] = new Object[splits.length];
					Constructor constructor;
					constructor = cls.getConstructor(String.class);
					for (int i = 0; i < splits.length; i++) {
						result[i] = constructor.newInstance(splits[i]);
					}
					return result;
				} else {
					return splits;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * 将数组 array 使用 split连接 成 字符串
	 * 
	 * @param array
	 * @param split
	 * @return
	 * @author jiangfl
	 */
	public static String join(Object[] array, String split) {
		StringBuffer sb = new StringBuffer();
		for (Object o : array) {
			sb.append(o).append(split);
		}
		if (sb.length() > 1) {
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

	/***
	 * 将数组 array 的内容添加到集合list中去
	 * 
	 * @param list
	 * @param array
	 * @author jiangfl
	 */
	public static void ListAddArray(List<Object> list, Object array[]) {
		for (Object obj : array) {
			list.add(obj);
		}
	}

	/**
	 * 传入 val值 这个值于list对象中的model(或者Map)实体的field属性相比较 看是否已经存在
	 * 
	 * @param list
	 *            list集合保存的是model实体(或者Map)
	 * @param field
	 *            model实体的某字段
	 * @param val
	 *            值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean exists(List list, String field, Object val) {

		Method m = null;
		Object result = null;
		Map map = null;
		for (Object o : list) {
			try {
				if (o instanceof Map) {
					map = (Map) o;
					result = map.get(field);
				} else {
					if (!field.startsWith("get")) {
						field = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
					}
					m = o.getClass().getMethod(field);
					result = m.invoke(o);
				}
				if (result == null && val == null) {
					return true;
				}
				if (result == null || val == null) {
					continue;
				}
				if (val.toString().equals(result.toString())) {
					return true;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String getSql(String[] sUUid) {
		StringBuilder sb = new StringBuilder();
		for (String s : sUUid) {
			sb.append(",\"").append(s).append("\"");
		}
		return sb.substring(1);
	}

	/**
	 *  取字符串的前toCount个字符 　　
	 *  @param str 被处理字符串 　 　
	 *  @param toCount 截取长度
	 * 　 @param more 后缀字符串 ... 省略　 　　 * @return String 　　
	 */
	public static String subString(String str, int toCount) throws Exception {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = str.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes("GBK");
			reInt += b.length;
			reStr += tempChar[kk];
		}
		// if (toCount == reInt || (toCount == reInt - 1))
		// reStr += more;
		return reStr;
	}

	public static String subString(String str, int toCount, boolean isMore) throws Exception {
		if (isMore) {
			return subString(str, toCount) + "...";
		} else {
			return subString(str, toCount);
		}

	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}
	
	public static void main(String[] args) {
		// System.out.println(unicode2String("\\u61\\u61\\u61\\ud83d\\ude00"));
	}
	
	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 
	    return string.toString();
	}
	
	// public static String subString(String markContent, int toCount, int s) {
	//
	//
	// return null;
	// }
}
