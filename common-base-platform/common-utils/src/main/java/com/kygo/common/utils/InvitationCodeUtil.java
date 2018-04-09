package com.kygo.common.utils;

/**
 * 生成邀请码的工具类
 */
public class InvitationCodeUtil {

    /**
     * 生成邀请码
     * 格式:字母+字母+4位数字,如 UK2627
     * @return
     */
    public static String generateInvitationCode(){
        char[] f=new char[24];
        int j=0;
        char firstChar;
        char secondChar;
        int randomNum=0;
        int num1,num2,num3,num4;
        String targetStr,finalStr;

        for(int i=65;i<91;i++){
            if(!(i==73||i==76||i==79))
            {
                f[j]=(char)i;
                j++;
            }
        }
        randomNum =(int) (Math.random()*22);
        firstChar = f[randomNum];
        randomNum =(int) (Math.random()*22);
        secondChar = f[randomNum];
        while(true){
            num1 = getRandomNum();
            num2 = getRandomNum();
            num3 = getRandomNum();
            num4 = getRandomNum();

            //测试是否为AABB类型
            if(num1 == num2 && num3 == num4) {
                continue;
            }
            //测试是否为AAAA类型
            if(num1 == num2 && num2 == num3 && num3 == 4) {
                continue;
            }
            //测试是否为ABBB类型
            if(num2 == num3 && num3 == num4) {
                continue;
            }
            //测试是否为AAAB类型
            if(num1 == num2 && num2 == num3) {
                continue;
            }

            //测试是否为ABCD类型
            if(num1+1 == num2 && num2 + 1 == num3 && num3 + 1 == num4) {
                continue;
            }
            //如果不是上面类型，则是目的数字，退出循环
            break;

        }
        targetStr = Integer.toString(num1)+Integer.toString(num2)+Integer.toString(num3)+Integer.toString(num4);
        //拼接前两位随机英文
        finalStr = String.valueOf(firstChar)+String.valueOf(secondChar)+targetStr;
        return finalStr;
    }

    private static int getRandomNum(){
        return (int)(Math.random()*10);
    }
}
