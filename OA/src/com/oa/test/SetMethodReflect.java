package com.oa.test;  

import java.lang.reflect.Method;  
import java.util.Hashtable;  
import java.util.regex.Pattern;  

/** 
 *  
 * @desc 通过反射来动态调用get 和 set 方法 
 * @date 2010-10-14 
 * @Version 1.0 
 */  
public class SetMethodReflect {  

    private Class cls;  
    /** 
     * 传过来的对象 
     */  
    private Object obj;  

    /** 
     * 存放get方法 
     */  
    private Hashtable<String, Method> getMethods = null;  
    /** 
     * 存放set方法 
     */  
    private Hashtable<String, Method> setMethods = null;  

    /** 
     * 定义构造方法 -- 一般来说是个pojo 
     *  
     * @param o 目标对象 
     */  
    public SetMethodReflect(Object o) {  
        obj = o;  
        initMethods();  
    }  

    /** 
     *  
     * @desc 初始化 
     */  
    public void initMethods() {  
        getMethods = new Hashtable<String, Method>();  
        setMethods = new Hashtable<String, Method>();  
        cls = obj.getClass();  
        Method[] methods = cls.getMethods();  
        // 定义正则表达式，从方法中过滤出getter / setter 函数.  
        String gs = "get(\\w+)";  
        Pattern getM = Pattern.compile(gs);  
        String ss = "set(\\w+)";  
        Pattern setM = Pattern.compile(ss);  
        // 把方法中的"set" 或者 "get" 去掉  
        String rapl = "$1";  
        String param;  
        for (int i = 0; i < methods.length; ++i) {  
            Method m = methods[i];  
            String methodName = m.getName();  
            if (Pattern.matches(gs, methodName)) {  
                param = getM.matcher(methodName).replaceAll(rapl).toLowerCase();  
                getMethods.put(param, m);  
            } else if (Pattern.matches(ss, methodName)) {  
                param = setM.matcher(methodName).replaceAll(rapl).toLowerCase();  
                setMethods.put(param, m);  
            } else {  
                // System.out.println(methodName + " 不是getter,setter方法！");  
            }  
        }  
    }  

    /** 
     *  
     * @desc 调用set方法 
     */  
    public boolean setMethodValue(String property, boolean value) {  
        Method m = setMethods.get(property.toLowerCase());  
        if (m != null) {  
            try {  
                // 调用目标类的setter函数  
                m.invoke(obj, value);  
                return true;  
            } catch (Exception ex) {  
                System.out.println("invoke getter on " + property + " error: "  
                        + ex.toString());  
                return false;  
            }  
        }  
        return false;  
    }  

    /** 
     *  
     * @desc 调用set方法 
     */  
    public boolean setMethodValue(String property, String value) {  
        Method m = setMethods.get(property.toLowerCase());  
        if (m != null) {  
            try {  
                /** 
                 * 调用obj类的setter函数 
                 */  
                m.invoke(obj, value);  
                return true;  
            } catch (Exception ex) {  
                System.out.println("invoke getter on " + property + " error: "  
                        + ex.toString());  
                return false;  
            }  
        }  
        return false;  
    }  

    // 测试方法  
    public static void main(String args[]) {  
        TestReflect ah = new TestReflect();  
        SetMethodReflect smr = new SetMethodReflect(ah);  
        smr.setMethodValue("a", false);  
        smr.setMethodValue("b", true);  
        smr.setMethodValue("c", true);  

        System.out.println(ah.isA());  
        System.out.println(ah.isB());  
        System.out.println(ah.isC());  
    }  
}  


// 一个model  
class TestReflect {  
    boolean a;  
    boolean b;  
    boolean c;  
    public boolean isA() {  
        return a;  
    }  
    public void setA(boolean a) {  
        this.a = a;  
    }  
    public boolean isB() {  
        return b;  
    }  
    public void setB(boolean b) {  
        this.b = b;  
    }  
    public boolean isC() {  
        return c;  
    }  
    public void setC(boolean c) {  
        this.c = c;  
    }  
}  