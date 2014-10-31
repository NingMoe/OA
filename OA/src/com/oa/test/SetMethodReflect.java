package com.oa.test;  

import java.lang.reflect.Method;  
import java.util.Hashtable;  
import java.util.regex.Pattern;  

/** 
 *  
 * @desc ͨ����������̬����get �� set ���� 
 * @date 2010-10-14 
 * @Version 1.0 
 */  
public class SetMethodReflect {  

    private Class cls;  
    /** 
     * �������Ķ��� 
     */  
    private Object obj;  

    /** 
     * ���get���� 
     */  
    private Hashtable<String, Method> getMethods = null;  
    /** 
     * ���set���� 
     */  
    private Hashtable<String, Method> setMethods = null;  

    /** 
     * ���幹�췽�� -- һ����˵�Ǹ�pojo 
     *  
     * @param o Ŀ����� 
     */  
    public SetMethodReflect(Object o) {  
        obj = o;  
        initMethods();  
    }  

    /** 
     *  
     * @desc ��ʼ�� 
     */  
    public void initMethods() {  
        getMethods = new Hashtable<String, Method>();  
        setMethods = new Hashtable<String, Method>();  
        cls = obj.getClass();  
        Method[] methods = cls.getMethods();  
        // ����������ʽ���ӷ����й��˳�getter / setter ����.  
        String gs = "get(\\w+)";  
        Pattern getM = Pattern.compile(gs);  
        String ss = "set(\\w+)";  
        Pattern setM = Pattern.compile(ss);  
        // �ѷ����е�"set" ���� "get" ȥ��  
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
                // System.out.println(methodName + " ����getter,setter������");  
            }  
        }  
    }  

    /** 
     *  
     * @desc ����set���� 
     */  
    public boolean setMethodValue(String property, boolean value) {  
        Method m = setMethods.get(property.toLowerCase());  
        if (m != null) {  
            try {  
                // ����Ŀ�����setter����  
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
     * @desc ����set���� 
     */  
    public boolean setMethodValue(String property, String value) {  
        Method m = setMethods.get(property.toLowerCase());  
        if (m != null) {  
            try {  
                /** 
                 * ����obj���setter���� 
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

    // ���Է���  
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


// һ��model  
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